package com.cloud.music.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.core.model.PageParam;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.user.form.LoginBody;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author cgf
 * @since 2023-10-14
 */
public interface CloudUserService extends IService<CloudUser> {

    IPage<CloudUser> userList(PageParam param);

    void create(CloudUser user);

    Map<String, Object> login(LoginBody body);
}
