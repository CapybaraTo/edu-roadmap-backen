package com.video.service;

import com.video.entity.VideoCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface VideoService {
    Page<VideoCourse> getCourses(String category, String difficulty, String search, Pageable pageable);
    VideoCourse getCourseById(String courseId);
    List<Map<String, Object>> getCategories();
    List<VideoCourse> getHotCourses();
    List<VideoCourse> getNewCourses();
    void updateProgress(String courseId, String lessonId, Integer progress);
    void addComment(String courseId, Map<String, Object> comment);
} 