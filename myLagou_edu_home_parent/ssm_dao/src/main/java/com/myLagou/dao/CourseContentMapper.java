package com.myLagou.dao;

import com.myLagou.entity.Course;
import com.myLagou.entity.CourseLesson;
import com.myLagou.entity.CourseSection;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-24 21:24
 */
public interface CourseContentMapper {
    /*根据课程id查询关联的章节信息以及课时信息*/
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        根据章节 id 查询课时信息  进行回显
     */
    public Course findCourseByCourseId(Integer courseId);

    /*新增章节信息*/
    public void saveSection(CourseSection courseSection);

    /*更新修改章节信息*/
    public void updateSection(CourseSection courseSection);

    /*修改章节状态*/
    public void updateSectionStatus(CourseSection courseSection);

    /*新增课时信息*/
    public void saveLesson(CourseLesson courseLesson);

}
