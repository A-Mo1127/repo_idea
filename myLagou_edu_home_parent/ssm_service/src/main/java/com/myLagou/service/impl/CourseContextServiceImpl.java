package com.myLagou.service.impl;

import com.myLagou.dao.CourseContentMapper;
import com.myLagou.entity.Course;
import com.myLagou.entity.CourseLesson;
import com.myLagou.entity.CourseSection;
import com.myLagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-24 21:54
 */
@Service
public class CourseContextServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);

        return list;
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {
        Course lessonById = courseContentMapper.findCourseByCourseId(courseId);
        return lessonById;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        //1、补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseContentMapper.saveSection(courseSection);
    }

    /*更新章节信息*/
    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);

        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        //封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseSection.setId(id);

        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //1、补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.saveLesson(courseLesson);
    }
}
