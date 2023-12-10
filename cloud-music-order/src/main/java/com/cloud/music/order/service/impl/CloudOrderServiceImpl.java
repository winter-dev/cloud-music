package com.cloud.music.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.exception.BusinessException;
import com.cloud.music.common.utils.IdUtils;
import com.cloud.music.common.utils.R;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.core.security.service.TokenService;
import com.cloud.music.core.snowflake.SnowFlakeIdWorker;
import com.cloud.music.order.entity.CloudOrder;
import com.cloud.music.order.entity.param.OrderParam;
import com.cloud.music.order.feigin.RemoteUserService;
import com.cloud.music.order.mapper.CloudOrderMapper;
import com.cloud.music.order.service.CloudOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author cgf
 * @since 2023-10-19
 */
@Service
public class CloudOrderServiceImpl extends ServiceImpl<CloudOrderMapper, CloudOrder> implements CloudOrderService {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private SnowFlakeIdWorker idWorker;

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public R<String> create(OrderParam orderParam) {
        //校验用户状态
        String userId = tokenService.getLoginUser().getUserId();
        R<CloudUser> userR = remoteUserService.getUserById(userId);
        if (userR.getCode() != 200) {
            return R.fail(userR.getMsg());
        }
        CloudUser user = userR.getData();
        if (null == user)
            throw new BusinessException("用户不存在,请重新登录");
        if (user.getStatus() != 0)
            throw new BusinessException("用户已被禁用,请联系管理员");

        CloudOrder order = new CloudOrder();
        order.setId(idWorker.nextId());
        order.setStatus(0);
        order.setCreateTime(new Date());
        order.setOrderNo(IdUtils.fastSimpleUUID());
        order.setAmount(orderParam.getAmount());
        order.setUserId(userId);
        order.setRemark(orderParam.getRemark());
        return this.save(order) ? R.ok(order.getId()) : R.fail();
    }
}
