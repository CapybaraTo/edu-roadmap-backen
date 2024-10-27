package com.roam.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
//@Data
//@ApiModel(value="UserStatsRequest对象", description="用户路线图进度请求数据")
public class UserStatsRequest implements Serializable {
    // Getters and Setters
//    @ApiModelProperty(value = "用户id")
    private String userId;

//    @ApiModelProperty(value = "用户名")
    private String username;

//    @ApiModelProperty(value = "资源类型")
    private String resourceType;

//    @ApiModelProperty(value = "资源名称")
//    private String resourceTitle;

//    @ApiModelProperty(value = "资源id")
    private String resourceId;

//    @ApiModelProperty(value = "知识点id")
    private String topicId;

//    @ApiModelProperty(value = "知识点名称")
//    private String topicTitle;

//    @ApiModelProperty(value = "知识点状态")
    private String progress;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

//    public String getResourceTitle() {
//        return resourceTitle;
//    }
//    public void setResourceTitle(String resourceTitle) {
//        this.resourceTitle = resourceTitle;
//    }

    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

//    public String getTopicTitle() {
//        return topicTitle;
//    }
//    public void setTopicTitle(String topicTitle) {
//        this.topicTitle = topicTitle;
//    }

    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "UserStatsRequest{" +
                "userId=" + userId +
                ", username=" + username +
                ", resourceType=" + resourceType +
//                ", resourceTitle=" + resourceTitle +
                ", resourceId=" + resourceId +
                ", topicId=" + topicId +
//                ", topicTitle=" + topicTitle +
                ", progress=" + progress +
                "}";
    }
}
