package com.myLagou.controller;

import com.github.pagehelper.PageInfo;
import com.myLagou.entity.ResponseResult;
import com.myLagou.entity.Role;
import com.myLagou.entity.User;
import com.myLagou.entity.UserVo;
import com.myLagou.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhy
 * @create 2022-08-25 21:59
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /*用户分页多条件组合查询*/
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        ResponseResult result = new ResponseResult(true, 200, "用户分页多条件组合查询成功", pageInfo);
        return result;
    }


    /*修改用户状态*/
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status){
        userService.updateUserStatus(id,status);

        ResponseResult result = new ResponseResult(true, 200, "修改用户状态成功", null);
        return result;

    }

    /*用户登录     根据用户名（也就是电话号码）查询具体的用户信息    */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User login = userService.login(user);

        //对获取的user对象进行判断是否为空
        if (login != null){//获取的user对象不为空
            //保存用户id以及access_token到session中
            //获取session对象
            HttpSession session = request.getSession();

            //生成一个随机数作为access_token的值，以保证access_token不重复
            String access_token_value = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token_value);//把access_token存入session
            //把id存入session
            session.setAttribute("id", login.getId());

            //将查询出来的结果响应给前端
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token_value);
            map.put("user_id",login.getId());
            //将查询出来的user对象也保存到map中
            map.put("user",login);

            ResponseResult result = new ResponseResult(true, 1, "登陆成功", map);
            return result;
        }else {
            //获取的user对象为空
            ResponseResult result = new ResponseResult(true, 400, "登陆失败，用户名密码错误！！！", null);
            return result;
        }
    }


    /*分配角色回显功能*/
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200, "分配角色回显成功", roleList);
    }

    /*分配角色*/
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"分配角色成功", null);
    }

    /*动态的获取用户权限*/
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //1、获取请求头中的token
        String authorization = request.getHeader("Authorization");
        //2、获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        //3、判断token是否一致
        if (authorization.equals(access_token)){
            // 相等
            //获取之前存到session中的userId
            Integer userId = (Integer) session.getAttribute("id");
            //调用service，根据获取到的userid进行关联查询菜单信息
            ResponseResult userPermission = userService.getUserPermission(userId);


            return userPermission;

        }else {
            //不相等
            return new ResponseResult(false,400, "获取菜单信息失败失败",null);
        }
    }


}
