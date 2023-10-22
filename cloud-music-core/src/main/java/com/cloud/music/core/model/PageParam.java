package com.cloud.music.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenguangfu
 * @date 2023/10/14 01:17
 * @apiNote 公共查询类
 */
@Data
@ApiModel(value = "公共查询类")
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable {

    private static final long serialVersionUID = 3312656330026564338L;

    /** 当前记录起始索引 */
    @ApiModelProperty(value = "分页页码")
    private Integer pageNum;

    /** 每页显示记录数 */
    @ApiModelProperty(value = "分页每页显示数量")
    private Integer pageSize;

}
