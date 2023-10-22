package com.cloud.music.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.music.common.utils.R;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.core.model.PageParam;
import com.cloud.music.user.form.LoginBody;
import com.cloud.music.user.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cgf
 * @since 2023-10-14
 */
@RestController
@RequestMapping("/api/user")
public class CloudUserController {

    @Autowired
    private CloudUserService userService;


    @PostMapping("/login")
    public R<?> login(@Validated @RequestBody LoginBody body) {
        Map<String, Object> r = userService.login(body);
        // 获取登录token
        return R.ok(r);
    }

    @GetMapping("/list")
//    @SentinelResource(value="userList")
    public R<IPage<CloudUser>> list(PageParam param) {
        IPage<CloudUser> list = userService.userList(param);
        R<IPage<CloudUser>> page = new R<>();
        page.setData(list);
        return page;
    }


    @GetMapping("/getById/{id}")
    public R<CloudUser> getById(@PathVariable("id") String id) {
        return R.ok(userService.getById(id));
    }

    @PostMapping("/create")
    public R<String> create(@Validated @RequestBody CloudUser user) {
        userService.create(user);
        return R.ok();
    }
}

