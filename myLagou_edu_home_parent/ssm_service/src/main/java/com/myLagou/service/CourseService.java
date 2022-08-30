package com.myLagou.service;

import com.myLagou.entity.Course;
import com.myLagou.entity.CourseVO;
import com.myLagou.entity.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-23 22:42
 */
public interface CourseService {

    /*根据多条件查询课程列表*/
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*新增课程以及讲师信息*/
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*回显课程信息(根据Id查询对应的课程信息以及关联的讲师信息*/
    public CourseVO findCourseById(Integer id);

    /*更新课程以及讲师信息*/
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*课程状态管理*/
    public void updateCourseStatus(int courseId, int status);


}
