package com.cloud.music.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.common.utils.R;
import com.cloud.music.order.entity.CloudOrder;
import com.cloud.music.order.entity.param.OrderParam;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author cgf
 * @since 2023-10-19
 */
public interface CloudOrderService extends IService<CloudOrder> {

    R<String> create(OrderParam order);
}
