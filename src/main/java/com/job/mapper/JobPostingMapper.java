package com.job.mapper;

import com.job.entity.JobPosting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface JobPostingMapper {
    List<JobPosting> findAll();
    
    List<JobPosting> findByFilters(
        @Param("location") String location,
        @Param("salaryRange") String salaryRange,
        @Param("experience") String experience,
        @Param("skillName") String skillName
    );
    
    JobPosting findById(@Param("id") String id);
} 