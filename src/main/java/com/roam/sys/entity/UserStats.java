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

    private Integer userId;

    private String username;

    private String roadmapId;

    private String roadmapTitle;

    private String topicId;

    private String topicTitle;

    private String topicStats;

    private Date updatedAt;

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

    public String getRoadmapId() {
        return roadmapId;
    }
    public void setRoadmapId(String roadmapId) {
        this.roadmapId = roadmapId;
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
                ", roadmapId=" + roadmapId +
                ", roadmapTitle=" + roadmapTitle +
                ", topicId=" + topicId +
                ", topicTitle=" + topicTitle +
                ", topicStats=" + topicStats +
                ", updatedAt=" + updatedAt +
                "}";
    }




}
