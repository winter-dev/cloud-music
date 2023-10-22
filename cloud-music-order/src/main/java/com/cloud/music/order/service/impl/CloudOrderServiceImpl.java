package com.cloud.music.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.order.entity.CloudOrder;
import com.cloud.music.order.mapper.CloudOrderMapper;
import com.cloud.music.order.service.CloudOrderService;
import org.springframework.stereotype.Service;

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

}
