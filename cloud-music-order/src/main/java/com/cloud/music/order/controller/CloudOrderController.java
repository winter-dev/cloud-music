package com.cloud.music.order.controller;


import com.cloud.music.common.utils.R;
import com.cloud.music.core.entity.CloudUser;
import com.cloud.music.order.entity.CloudOrder;
import com.cloud.music.order.entity.param.OrderParam;
import com.cloud.music.order.feigin.RemoteUserService;
import com.cloud.music.order.service.CloudOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CloudOrderService orderService;

    @PostMapping("/create")
    public R<String> create(@RequestBody OrderParam order) {
        return orderService.create(order);
    }

    @GetMapping("/{id}")
    public R<CloudOrder> getOrder(@PathVariable String id) {
        return R.ok(orderService.getById(id));
    }
}

