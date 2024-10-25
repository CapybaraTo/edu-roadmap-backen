package com.roam.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value="UserActivityStream对象", description="用户活动事件流数据")
public class UserActivityStream implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "活动ID")
    private String activityId;

    @ApiModelProperty(value = "活动类型")
    private String resourceType;

    @ApiModelProperty(value = "路线图ID")
    private String resourceId;

    @ApiModelProperty(value = "路线图名称")
    private String resourceTitle;

    @ApiModelProperty(value = "学习状态")
    private String status;

    @ApiModelProperty(value = "知识点列表")
    private String topics;

    @ApiModelProperty(value = "学习时间")
    private String updatedAt;

}
