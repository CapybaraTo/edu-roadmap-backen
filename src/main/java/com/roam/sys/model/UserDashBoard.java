package com.roam.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

//用户统计数据
@Data
//@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserDashBoard对象", description="用户仪表盘数据")
public class UserDashBoard implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "知识点完成数")
    private Integer done;

    @ApiModelProperty(value = "路线图数")
    private Integer learning;
}
