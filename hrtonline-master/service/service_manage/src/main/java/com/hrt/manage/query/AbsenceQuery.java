package com.hrt.manage.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@ApiModel(value = "User查询对象", description = "用户查询对象封装")
@Data
public class AbsenceQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    //这里设置查询参数

    @ApiModelProperty(value = "审批状态，精确查询")
    private String absenceState;

    @ApiModelProperty(value = "请假接收队伍，精确查询")
    private String absenceTeam;

    @ApiModelProperty(value = "队员id，精确查询")
    private Long userId;

    @ApiModelProperty(value = "队伍，精确查询")
    private String userTeam;

    @ApiModelProperty(value = "组别，精确查询")
    private String userGroup;

    @ApiModelProperty(value = "姓名，模糊查询")
    private String userName;

    //这里是与创建时间比较
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}