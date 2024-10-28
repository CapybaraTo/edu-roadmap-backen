package com.roam.sys.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserQuestionRequest implements Serializable {
    private String userId;

    private String groupId;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "UserQuestionRequest{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                "}";
    }

}
