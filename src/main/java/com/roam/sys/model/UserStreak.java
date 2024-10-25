package com.roam.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户统计数据
@Data
@ApiModel(value="UserStreak对象", description="用户学习天数")
public class UserStreak implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "初次登录时间")
    private String firstLoginTime;

    @ApiModelProperty(value = "最新学习时间")
    private String latestLearningTime;

    @ApiModelProperty(value = "学习天数")
    private Integer streak;

}
