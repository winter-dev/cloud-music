package com.cloud.music.order.controller;


import com.cloud.music.common.utils.R;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.order.feigin.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author cgf
 * @since 2023-10-19
 */
@RestController
@RequestMapping("/api/order")
public class CloudOrderController {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("/{id}")
    public R<CloudUser> getOrder(@PathVariable String id) {
        return remoteUserService.getUserById(id);
    }
}

