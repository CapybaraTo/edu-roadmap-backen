package com.video.controller;

import com.video.entity.VideoCourse;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/courses")
    public Page<VideoCourse> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false) String keyword) {
        return videoService.getCourses(page, size, keyword);
    }
} 