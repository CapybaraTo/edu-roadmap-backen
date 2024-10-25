package com.roam.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@TableName("user_activity")
public class UserActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer activityId;

    private Integer userId;

    private String username;

    private String resourceId;

    private String resourceTitle;

    private String topicId;

    private String topicTitle;

    private String status;

    private Date createdAt;

    public Integer getActivityId() {
        return activityId;
    }
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }
    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getStatus() {
        return status;
    }
    public void setStats(String stats) {
        this.status = stats;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt( Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "userId=" + userId +
                ", username=" + username +
                ", resourceId=" + resourceId +
                ", resourceTitle=" + resourceTitle +
                ", topicId=" + topicId +
                ", topicTitle=" + topicTitle +
                ", status=" + status +
                ", createdAt=" + createdAt +
                "}";
    }








}
