package com.myLagou.dao;

import com.myLagou.entity.Course;
import com.myLagou.entity.CourseVO;
import com.myLagou.entity.Teacher;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-23 22:26
 */
public interface CourseMapper {

    /*根据多条件查询课程列表*/
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*新增课程信息*/
    public void saveCourse(Course course);

    /*新增讲师信息*/
    public void saveTeacher(Teacher teacher);

    /*回显课程信息(根据Id查询对应的课程信息以及关联的讲师信息*/
    public CourseVO findCourseById(Integer id);

    /*更新课程信息*/
    public void updateCourse(Course course);

    /*更新讲师信息*/
    public void updateTeacher(Teacher teacher);

    /*课程状态管理*/
    public void updateCourseStatus(Course course);


}
