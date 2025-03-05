package com.video.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.VideoCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VideoCourseRepository extends BaseMapper<VideoCourse> {
} 