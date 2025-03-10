package com.roam.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@TableName("user_stats")
public class UserStats implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String username;

    private String resourceType;

    private String resourceId;

    private String resourceTitle;

    private String topicId;

    private String topicTitle;

    private String topicStats;

    private Date updatedAt;

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

    public String getTopicStats() {
        return topicStats;
    }
    public void setTopicStats(String topicStats) {
        this.topicStats = topicStats;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt( Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "userId=" + userId +
                ", username=" + username +
                ", resourceType=" + resourceType +
                ", resourceId=" + resourceId +
                ", resourceTitle=" + resourceTitle +
                ", topicId=" + topicId +
                ", topicTitle=" + topicTitle +
                ", topicStats=" + topicStats +
                ", updatedAt=" + updatedAt +
                "}";
    }




}
