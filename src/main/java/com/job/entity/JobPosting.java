package com.job.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobPosting {
    private String id;
    private String title;
    private String company;
    private String location;
    private String salary;
    private String experience;
    private String education;
    private String description;
    private LocalDateTime postDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<JobSkillRequirement> skills;
} 