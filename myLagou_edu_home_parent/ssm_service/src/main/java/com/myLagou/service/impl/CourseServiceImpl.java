package com.myLagou.service.impl;

import com.myLagou.dao.CourseMapper;
import com.myLagou.entity.Course;
import com.myLagou.entity.CourseVO;
import com.myLagou.entity.Teacher;
import com.myLagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-23 22:43
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> courseByCondition = courseMapper.findCourseByCondition(courseVO);
        return courseByCondition;
    }

    /*新增课程以及讲师信息*/
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        //把courseVO中与course属性名相同的封装到course
        BeanUtils.copyProperties(course, courseVO);
        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);

        //获取新插入课程的id值
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        System.out.println(course);
        System.out.println(teacher);

        //保存课程信息
        courseMapper.saveTeacher(teacher);
    }

    /*回显课程信息(根据Id查询对应的课程信息以及关联的讲师信息*/
    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseById = courseMapper.findCourseById(id);

        return courseById;
    }

    /*更新课程以及讲师信息*/
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //将前端传递过来的课程信息进行封装
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);
        //补全课程信息
        Date date = new Date();
        course.setUpdateTime(date);

        //保存更新的课程信息
        courseMapper.updateCourse(course);

        ////将前端传递过来的课讲师信息进行封装
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全信息
        teacher.setCourseId(course.getId());//courseVO中没有任何一个属性可以与Teacher中的courseId对应，所以需要手动补全
        teacher.setUpdateTime(date);

        //保存更新的讲师信息
        courseMapper.updateTeacher(teacher);
    }

    /*课程状态管理*/
    @Override
    public void updateCourseStatus(int courseId, int status) {
        //1、封装数据
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //2、调用dao层
        courseMapper.updateCourseStatus(course);
    }

}
