package com.myLagou.controller;

import com.myLagou.entity.Course;
import com.myLagou.entity.CourseVO;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhy
 * @create 2022-08-23 22:46
 */
@RestController//组合注解   相当于@Controller + @ResponseBody
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    /*@RequestBody CourseVO courseVO：将前端发送过来的json串按照其key：value对应的封装到courseVO对象的属性中*/
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        //调用service层
        List<Course> list = courseService.findCourseByCondition(courseVO);
        //将查询到的结果依据前端要球的格式封装到responseResult中再返回
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    /*课程图片上传*/
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {//参数名要求与所提交的请求参数的key保持一致

        //1、判断接收到的参数file是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }
        //file不为空再进行其他操作
        //2、获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        //再截取到webapps层即可
        String ssm = realPath.substring(0, realPath.indexOf("ssm"));

        //3、获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //4、生成新的文件名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + substring;

        //5、文件上传
        String uploadPath = ssm + "upload\\";//文件上传的位置
        File filePath = new File(uploadPath, newFileName);

        //6、对上传路径进行判断
        if (!filePath.getParentFile().exists()){//不存在
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        //7、图片真正的上传
        file.transferTo(filePath);

        //8、将文件名和文件路径进行返回，并响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true,200,"图片上传成功", map);
        return responseResult;
    }

    /*保存新增课程信息及讲师信息 */
    /*更新课程以及讲师信息*/
    //新增课程信息要与修改课程信息要写在同一个方法中
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId() == null){//新增操作
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "新增课程成功", null);
            return result;
        }else {//更新操作
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "更新课程成功", null);
            return result;
        }
    }

    /*回显课程信息(根据Id查询对应的课程信息以及关联的讲师信息*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true,200,"根据Id查找成功",courseVO);
        return responseResult;
    }

    /*课程状态管理*/
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status){
        //调用service层
        courseService.updateCourseStatus(id,status);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改课程状态完成", map);
        return result;

    }



}
