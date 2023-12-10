package com.cloud.music.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.constant.CacheConstants;
import com.cloud.music.common.exception.BusinessException;
import com.cloud.music.common.utils.Convert;
import com.cloud.music.common.utils.IpUtils;
import com.cloud.music.common.utils.StringUtils;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.core.model.LoginUser;
import com.cloud.music.core.model.PageParam;
import com.cloud.music.core.security.SecurityUtils;
import com.cloud.music.core.security.service.TokenService;
import com.cloud.music.core.snowflake.SnowFlakeIdWorker;
import com.cloud.music.redis.service.RedisService;
import com.cloud.music.user.form.LoginBody;
import com.cloud.music.user.mapper.CloudUserMapper;
import com.cloud.music.user.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cgf
 * @since 2023-10-14
 */
@Service
public class CloudUserServiceImpl extends ServiceImpl<CloudUserMapper, CloudUser> implements CloudUserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SnowFlakeIdWorker worker;

    @Override
    public IPage<CloudUser> userList(PageParam param) {
        if (null == param) {
            param = new PageParam(1, 10);
        }
        if (null == param.getPageNum())
            param.setPageNum(1);
        if (null == param.getPageSize())
            param.setPageSize(10);
        LambdaQueryWrapper<CloudUser> wrapper = new LambdaQueryWrapper<>();
        Page<CloudUser> page = new Page<>(param.getPageNum(), param.getPageSize());
        return this.page(page, wrapper);
    }

    @Override
    public void create(CloudUser user) {
        //校验是否重复
        LambdaQueryWrapper<CloudUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CloudUser::getUserName, user.getUserName());
        if (this.count(wrapper) > 0)
            throw new BusinessException("User already exists");
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setId(worker.nextId());
        user.setStatus(0);
        this.save(user);
    }

    @Override
    public Map<String, Object> login(LoginBody body) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(body.getUserName(), body.getPassword())) {
            throw new BusinessException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (body.getPassword().length() < 6 || body.getPassword().length() > 20) {
            throw new BusinessException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (body.getUserName().length() < 2 || body.getUserName().length() > 20) {
            throw new BusinessException("用户名不在指定范围");
        }
        // IP黑名单校验
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            throw new BusinessException("很遗憾，访问IP已被列入系统黑名单");
        }
        LambdaQueryWrapper<CloudUser> wrapper = new LambdaQueryWrapper<CloudUser>();
        wrapper.eq(CloudUser::getUserName, body.getUserName());
        CloudUser user = this.getOne(wrapper);
        if (null == user)
            throw new BusinessException("用户不存在");

        if (!SecurityUtils.matchesPassword(body.getPassword(), user.getPassword()))
            throw new BusinessException("密码错误");
        LoginUser loginUser = new LoginUser(user.getId(), user.getUserName());
        return tokenService.createToken(loginUser);
    }
}
