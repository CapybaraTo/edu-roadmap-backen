package com.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.video.entity.VideoCourse;
import com.video.repository.VideoCourseRepository;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoCourseRepository videoCourseRepository;

    @Override
    public org.springframework.data.domain.Page<VideoCourse> getCourses(int page, int size, String keyword) {
        // 创建MyBatis-Plus分页对象，注意页码从1开始
        Page<VideoCourse> pageParam = new Page<>(page + 1, size);
        
        // 创建查询条件
        LambdaQueryWrapper<VideoCourse> queryWrapper = new LambdaQueryWrapper<>();
        
        // 如果有关键词，添加模糊搜索条件
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(VideoCourse::getTitle, keyword)
                    .or()
                    .like(VideoCourse::getDescription, keyword);
        }
        
        // 只查询已发布的课程
        queryWrapper.eq(VideoCourse::getStatus, "published");
        
        // 添加排序条件：优先展示热门课程，其次是新课程，最后按创建时间倒序
        queryWrapper.orderByDesc(VideoCourse::getIsHot)
                   .orderByDesc(VideoCourse::getIsNew)
                   .orderByDesc(VideoCourse::getCreatedAt);
        
        // 执行查询
        Page<VideoCourse> result = videoCourseRepository.selectPage(pageParam, queryWrapper);
        
        // 转换为Spring Data的Page对象
        return new PageImpl<>(
            result.getRecords(),
            PageRequest.of(page, size),
            result.getTotal()
        );
    }
} 