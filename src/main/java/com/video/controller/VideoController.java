package com.video.controller;

import com.video.entity.VideoCourse;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/courses")
    public ResponseEntity<Map<String, Object>> getCourses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) String search,
            Pageable pageable) {
        Page<VideoCourse> coursePage = videoService.getCourses(category, difficulty, search, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("content", coursePage.getContent());
        response.put("totalElements", coursePage.getTotalElements());
        response.put("totalPages", coursePage.getTotalPages());
        response.put("size", coursePage.getSize());
        response.put("number", coursePage.getNumber());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<VideoCourse> getCourseById(@PathVariable String courseId) {
        return ResponseEntity.ok(videoService.getCourseById(courseId));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Map<String, Object>>> getCategories() {
        return ResponseEntity.ok(videoService.getCategories());
    }

    @GetMapping("/courses/hot")
    public ResponseEntity<List<VideoCourse>> getHotCourses() {
        return ResponseEntity.ok(videoService.getHotCourses());
    }

    @GetMapping("/courses/new")
    public ResponseEntity<List<VideoCourse>> getNewCourses() {
        return ResponseEntity.ok(videoService.getNewCourses());
    }

    @PostMapping("/courses/{courseId}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable String courseId,
            @RequestParam String lessonId,
            @RequestParam Integer progress) {
        videoService.updateProgress(courseId, lessonId, progress);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/courses/{courseId}/comments")
    public ResponseEntity<?> addComment(
            @PathVariable String courseId,
            @RequestBody Map<String, Object> comment) {
        videoService.addComment(courseId, comment);
        return ResponseEntity.ok().build();
    }
} 