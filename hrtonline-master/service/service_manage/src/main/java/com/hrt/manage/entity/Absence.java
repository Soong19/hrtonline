package com.hrt.manage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Leave对象", description = "请假处理")
public class Absence implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "版本号")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "车队id")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "队伍")
    private String userTeam;

    @ApiModelProperty(value = "组别")
    private String userGroup;

    @ApiModelProperty(value = "请假原因")
    private String absenceReason;

    @ApiModelProperty(value = "请假区间")
    private String absencePeriod;

    @ApiModelProperty(value = "请假队别")
    private String absenceTeam;

    @ApiModelProperty(value = "状态")
    private String absenceState;

    @ApiModelProperty(value = "不批准原因")
    private String rejectReason;
}
