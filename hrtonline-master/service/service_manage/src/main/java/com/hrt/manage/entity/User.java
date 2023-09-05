package com.hrt.manage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流水号，用idworker生成")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "版本号")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "0表示未删除，1表示删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "队内id")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String userPassword;

    @ApiModelProperty(value = "队别")
    private String userTeam;

    @ApiModelProperty(value = "组别")
    private String userGroup;

    @ApiModelProperty(value = "学号")
    private Long studentId;

    @ApiModelProperty(value = "学院")
    private String academy;

    @ApiModelProperty(value = "手机号")
    private Long telphone;

    @ApiModelProperty(value = "QQ号")
    private Long qqNum;

    @ApiModelProperty(value = "微信号")
    private String wechatNum;

    @ApiModelProperty(value = "入队时间")
    private Date enterDate;

    @ApiModelProperty(value = "银行")
    private String bank;

    @ApiModelProperty(value = "银行卡号")
    private Long bankNum;

    @ApiModelProperty(value = "队员权限识别号")
    private String userStatus;

    @ApiModelProperty(value = "头像链接")
    private String avatar;

    @ApiModelProperty(value = "权限")
    private String userRole;


}
