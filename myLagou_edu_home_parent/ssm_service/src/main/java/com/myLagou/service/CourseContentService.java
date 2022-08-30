package com.myLagou.service;

import com.myLagou.entity.Course;
import com.myLagou.entity.CourseLesson;
import com.myLagou.entity.CourseSection;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-24 21:45
 */
public interface CourseContentService {
    /*
        根据课程id查询章节信息和课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    /*
        根据章节 id 查询课时信息  进行回显
     */
    public Course findCourseByCourseId(Integer courseId);

    /*新增章节信息*/
    public void saveSection(CourseSection courseSection);

    /*更新章节信息*/
    public void updateSection(CourseSection courseSection);

    /*修改章节状态*/
    public void updateSectionStatus(Integer id, Integer status);

    /*新增课时信息*/
    public void saveLesson(CourseLesson courseLesson);
}
