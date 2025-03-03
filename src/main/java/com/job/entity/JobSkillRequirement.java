package com.job.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobSkillRequirement {
    private Integer id;
    private String jobId;
    private String skillName;
    private String skillLevel;
    private LocalDateTime createdAt;
} 