package com.myLagou.controller;

import com.myLagou.entity.Test;
import com.myLagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-23 16:57
 */

@RestController//组合注解，相当于@Controller + @ResponseBody
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    /*查询所有*/
    @RequestMapping("/findAll")
    public List<Test> findAll(){
        List<Test> allTest = testService.findAllTest();
        for (Test test : allTest) {
            System.out.println(test);
        }
        return allTest;
    }
}
