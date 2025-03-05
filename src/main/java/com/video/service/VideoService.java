package com.video.service;

import com.video.entity.VideoCourse;
import org.springframework.data.domain.Page;

public interface VideoService {
    Page<VideoCourse> getCourses(int page, int size, String keyword);
} 