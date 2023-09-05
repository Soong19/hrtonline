package com.hrt.manage.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(value = "Finance查询对象", description = "财务查询对象封装")
@Data
public class FinanceQuery {

    private static final long serialVersionUID = 1L;

    //这里设置查询参数

    @ApiModelProperty(value = "队员id，精确查询")//可作为个人财务查询使用
    private Long userId;

    @ApiModelProperty(value = "队伍，精确查询")
    private String userTeam;

    @ApiModelProperty(value = "组别，精确查询")
    private String userGroup;

    @ApiModelProperty(value = "姓名，模糊查询")
    private String userName;

    @ApiModelProperty(value = "金额下限，区间查询")
    private BigDecimal amountBegin;

    @ApiModelProperty(value = "金额上限，区间查询")
    private BigDecimal amountEnd;

    @ApiModelProperty(value = "发票类型，精确查询")
    private String invoiceType;

    @ApiModelProperty(value = "购买物品类型，精确查询")
    private String purchaseType;

    @ApiModelProperty(value = "报销类型，精确查询")
    private String financeType;

    @ApiModelProperty(value = "垫付类型，精确查询")
    private String advanceType;

    @ApiModelProperty(value = "财务当前状态，精确查询")
    private String financeState;

    //这里是与创建时间比较
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
