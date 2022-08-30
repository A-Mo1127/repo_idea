package com.myLagou.controller;

import com.myLagou.entity.Course;
import com.myLagou.entity.CourseLesson;
import com.myLagou.entity.CourseSection;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhy
 * @create 2022-08-24 21:59
 */
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    /***课程内容展示***/
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true,200,"章节及课时内容查询成功", list);
        return result;
    }

    /*回显章节对应的课程信息*/
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "回显章节对应的课程信息成功", course);
        return result;
    }


    /*新增和更新修改章节信息*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        //判断请求参数是否携带章节id
        if (courseSection.getId() == null){//新增
            courseContentService.saveSection(courseSection);

            ResponseResult result = new ResponseResult(true, 200, "新增章节成功", null);
            return result;
        }else {//更新修改
            courseContentService.updateSection(courseSection);

            ResponseResult result = new ResponseResult(true, 200, "更新修改章节成功", null);
            return result;
        }
    }


    /*修改章节状态*/
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status){
        courseContentService.updateSectionStatus(id,status);

        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改章节信息成功", map);
        return result;
    }


    /*新增课时信息*/
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){

        courseContentService.saveLesson(courseLesson);
        ResponseResult result = new ResponseResult(true, 200, "新增课时成功", null);

        return result;
    }

}
