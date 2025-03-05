package com.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("video_course")
public class VideoCourse {
    @TableId(type = IdType.INPUT)
    private String courseId;
    private String title;
    private String description;
    private String category;
    private String difficultyLevel;
    private String instructorId;
    private Integer lessonsCount;
    private String coverImage;
    private BigDecimal rating;
    private Integer totalRatings;
    private Boolean isHot;
    private Boolean isNew;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 