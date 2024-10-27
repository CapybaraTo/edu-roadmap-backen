package com.roam.sys.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserProgressRequest implements Serializable {
    private String userId;

    private String resourceType;

    private String resourceId;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserStatsRequest{" +
                "userId=" + userId +
                ", resourceType=" + resourceType +
                ", resourceId=" + resourceId +
                "}";
    }

}
