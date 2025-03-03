package com.job.controller;

import com.job.entity.JobPosting;
import com.job.mapper.JobPostingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobController {

    @Autowired
    private JobPostingMapper jobPostingMapper;

    @GetMapping
    public List<JobPosting> getAllJobs() {
        return jobPostingMapper.findAll();
    }

    @GetMapping("/search")
    public List<JobPosting> searchJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String skillName) {
        return jobPostingMapper.findByFilters(location, salaryRange, experience, skillName);
    }

    @GetMapping("/{id}")
    public JobPosting getJobById(@PathVariable String id) {
        return jobPostingMapper.findById(id);
    }
} 