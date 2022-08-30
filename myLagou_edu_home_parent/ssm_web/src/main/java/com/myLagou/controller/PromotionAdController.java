package com.myLagou.controller;

import com.github.pagehelper.PageInfo;
import com.myLagou.entity.PromotionAd;
import com.myLagou.entity.PromotionAdVo;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhy
 * @create 2022-08-25 17:04
 */
@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /*分页查询广告信息*/
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){

        PageInfo<PromotionAd> promotionAdPageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);
        ResponseResult result = new ResponseResult(true, 200, "分页查询广告信息成功", promotionAdPageInfo);

        return result;
    }


    /*新增和更新修改广告*/
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId() == null){//新增
            promotionAdService.savePromotionAd(promotionAd);

            ResponseResult result = new ResponseResult(true, 200, "新增广告成功", null);
            return result;
        }else {//更新修改
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "修改广告成功", null);
            return result;
        }
    }

    /*根据id查询promotionAd进行回显*/
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);

        ResponseResult result = new ResponseResult(true, 200, "根据id查询promotionAd成功", promotionAdById);
        return result;

    }



    /*广告图片上传*/
    @RequestMapping("/PromotionAdUpload")
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


    /*广告的上下线状态修改*/
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id, int status){
        promotionAdService.updatePromotionAdStatus(id,status);

        ResponseResult result = new ResponseResult(true, 200, "广告的上下线状态修改成功", null);
        return result;
    }


}
