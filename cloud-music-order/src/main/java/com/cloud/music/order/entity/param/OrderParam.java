package com.cloud.music.order.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cgf
 * @since 2023-12-10
 **/
@Data
public class OrderParam implements Serializable {

    private static final long serialVersionUID = 6093759012261673100L;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "来源")
    private Integer from;

    @Size(max = 200, message = "备注不能超过200个汉字")
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "支付方式")
    private Integer payType;

    @ApiModelProperty(value = "配送方式 1=快递 ，2=门店自提")
    private Integer shippingType;

    @ApiModelProperty(value = "门店ID")
    private Integer storeId;

}
