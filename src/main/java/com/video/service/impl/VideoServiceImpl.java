package com.video.service.impl;

import com.video.entity.VideoCourse;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<VideoCourse> getCourses(String category, String difficulty, String search, Pageable pageable) {
        List<Object> params = new ArrayList<>();
        StringBuilder conditions = new StringBuilder();

        if (StringUtils.hasText(category)) {
            if (conditions.length() > 0) {
                conditions.append(" AND ");
            }
            conditions.append("category = ?");
            params.add(category);
        }

        if (StringUtils.hasText(difficulty)) {
            if (conditions.length() > 0) {
                conditions.append(" AND ");
            }
            conditions.append("difficulty_level = ?");
            params.add(difficulty);
        }

        if (StringUtils.hasText(search)) {
            if (conditions.length() > 0) {
                conditions.append(" AND ");
            }
            conditions.append("(title LIKE ? OR description LIKE ?)");
            String searchPattern = "%" + search + "%";
            params.add(searchPattern);
            params.add(searchPattern);
        }

        String whereClause = conditions.length() > 0 ? " WHERE " + conditions.toString() : "";

        // Count total records
        String countSql = "SELECT COUNT(*) FROM video_course" + whereClause;
        Integer total = jdbcTemplate.queryForObject(countSql, params.toArray(), Integer.class);
        if (total == null) {
            total = 0;
        }

        // Build the main query
        String sql = "SELECT * FROM video_course" + whereClause + 
                    " ORDER BY created_at DESC LIMIT ? OFFSET ?";
        params.add(pageable.getPageSize());
        params.add(pageable.getOffset());

        List<VideoCourse> courses = jdbcTemplate.query(sql, params.toArray(), (rs, rowNum) -> {
            VideoCourse course = new VideoCourse();
            course.setCourseId(rs.getString("course_id"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategory(rs.getString("category"));
            course.setDifficultyLevel(rs.getString("difficulty_level"));
            course.setInstructorId(rs.getString("instructor_id"));
            course.setLessonsCount(rs.getInt("lessons_count"));
            course.setCoverImage(rs.getString("cover_image"));
            course.setRating(rs.getBigDecimal("rating"));
            course.setTotalRatings(rs.getInt("total_ratings"));
            course.setIsHot(rs.getBoolean("is_hot"));
            course.setIsNew(rs.getBoolean("is_new"));
            course.setPrice(rs.getBigDecimal("price"));
            course.setStatus(rs.getString("status"));
            course.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            course.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return course;
        });

        return new PageImpl<>(courses, pageable, total);
    }

    @Override
    public VideoCourse getCourseById(String courseId) {
        String sql = "SELECT * FROM video_course WHERE course_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, (rs, rowNum) -> {
            VideoCourse course = new VideoCourse();
            course.setCourseId(rs.getString("course_id"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategory(rs.getString("category"));
            course.setDifficultyLevel(rs.getString("difficulty_level"));
            course.setInstructorId(rs.getString("instructor_id"));
            course.setLessonsCount(rs.getInt("lessons_count"));
            course.setCoverImage(rs.getString("cover_image"));
            course.setRating(rs.getBigDecimal("rating"));
            course.setTotalRatings(rs.getInt("total_ratings"));
            course.setIsHot(rs.getBoolean("is_hot"));
            course.setIsNew(rs.getBoolean("is_new"));
            course.setPrice(rs.getBigDecimal("price"));
            course.setStatus(rs.getString("status"));
            course.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            course.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return course;
        });
    }

    @Override
    public List<Map<String, Object>> getCategories() {
        String sql = "SELECT * FROM video_category ORDER BY sort_order";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> category = new HashMap<>();
            category.put("id", rs.getString("category_id"));
            category.put("name", rs.getString("name"));
            category.put("description", rs.getString("description"));
            category.put("icon", rs.getString("icon"));
            return category;
        });
    }

    @Override
    public List<VideoCourse> getHotCourses() {
        String sql = "SELECT * FROM video_course WHERE is_hot = 1 ORDER BY rating DESC LIMIT 10";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            VideoCourse course = new VideoCourse();
            course.setCourseId(rs.getString("course_id"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategory(rs.getString("category"));
            course.setDifficultyLevel(rs.getString("difficulty_level"));
            course.setInstructorId(rs.getString("instructor_id"));
            course.setLessonsCount(rs.getInt("lessons_count"));
            course.setCoverImage(rs.getString("cover_image"));
            course.setRating(rs.getBigDecimal("rating"));
            course.setTotalRatings(rs.getInt("total_ratings"));
            course.setIsHot(rs.getBoolean("is_hot"));
            course.setIsNew(rs.getBoolean("is_new"));
            course.setPrice(rs.getBigDecimal("price"));
            course.setStatus(rs.getString("status"));
            course.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            course.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return course;
        });
    }

    @Override
    public List<VideoCourse> getNewCourses() {
        String sql = "SELECT * FROM video_course WHERE is_new = 1 ORDER BY created_at DESC LIMIT 10";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            VideoCourse course = new VideoCourse();
            course.setCourseId(rs.getString("course_id"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategory(rs.getString("category"));
            course.setDifficultyLevel(rs.getString("difficulty_level"));
            course.setInstructorId(rs.getString("instructor_id"));
            course.setLessonsCount(rs.getInt("lessons_count"));
            course.setCoverImage(rs.getString("cover_image"));
            course.setRating(rs.getBigDecimal("rating"));
            course.setTotalRatings(rs.getInt("total_ratings"));
            course.setIsHot(rs.getBoolean("is_hot"));
            course.setIsNew(rs.getBoolean("is_new"));
            course.setPrice(rs.getBigDecimal("price"));
            course.setStatus(rs.getString("status"));
            course.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            course.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return course;
        });
    }

    @Override
    public void updateProgress(String courseId, String lessonId, Integer progress) {
        String sql = "INSERT INTO user_video_progress (user_id, lesson_id, progress) VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE progress = ?, last_watched_at = CURRENT_TIMESTAMP";
        // TODO: Get actual user_id from security context
        int userId = 1; // Temporary placeholder
        jdbcTemplate.update(sql, userId, lessonId, progress, progress);
    }

    @Override
    public void addComment(String courseId, Map<String, Object> comment) {
        String sql = "INSERT INTO video_comment (comment_id, course_id, user_id, content, rating) VALUES (?, ?, ?, ?, ?)";
        // TODO: Get actual user_id from security context
        int userId = 1; // Temporary placeholder
        String commentId = java.util.UUID.randomUUID().toString();
        jdbcTemplate.update(sql, 
            commentId,
            courseId,
            userId,
            comment.get("content"),
            comment.get("rating")
        );

        // Update course rating
        updateCourseRating(courseId);
    }

    private void updateCourseRating(String courseId) {
        String sql = "UPDATE video_course SET rating = (" +
                    "SELECT AVG(rating) FROM video_comment WHERE course_id = ?), " +
                    "total_ratings = (SELECT COUNT(*) FROM video_comment WHERE course_id = ?) " +
                    "WHERE course_id = ?";
        jdbcTemplate.update(sql, courseId, courseId, courseId);
    }
} 