package com.hrt.manage.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
@Accessors(chain = true)
@ApiModel(value="Finance对象", description="财务处理")
public class Finance implements Serializable {

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

    @ApiModelProperty(value = "发票类型")
    private String invoiceType;

    @ApiModelProperty(value = "发票金额")
    private BigDecimal invoiceAmount;

    @ApiModelProperty(value = "购买物品类型")
    private String purchaseType;

    @ApiModelProperty(value = "购买物品描述")
    private String purchaseDesc;

    @ApiModelProperty(value = "报销类型")
    private String financeType;

    @ApiModelProperty(value = "预付类型")
    private String advanceType;

    @ApiModelProperty(value = "当前状态")
    private String financeState;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "文件存储")
    private String financeFile;


}
