package com.cloud.music.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author cgf
 * @since 2023-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cloud_order")
@ApiModel(value="CloudOrder对象", description="订单表")
public class CloudOrder implements Serializable {


    private static final long serialVersionUID = -1304163222799827441L;
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "状态,0:待支付,1:已支付,2:待发货,3:已发货,4:已完成,5:已取消")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
