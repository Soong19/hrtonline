package com.hrt.oss.controller;


import com.hrt.commonutils.R;
import com.hrt.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/oss/ossfile")
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping("/uploadOssFile/finance")
    public R uploadFinanceFile(
             MultipartFile multipartFile,
             HttpServletRequest httpServletRequest
    ){
        String userId=httpServletRequest.getParameter("userId");
        String url=ossService.uploadFileToOss(multipartFile,Long.parseLong(userId),"财务文件");
        return R.ok().data("url",url);
    }


    @PostMapping("/uploadOssFile/avatar")
    public R uploadAvatar(
            MultipartFile multipartFile,
            HttpServletRequest httpServletRequest
    ){
        String userId=httpServletRequest.getParameter("userId");
        String url=ossService.uploadFileToOss(multipartFile,Long.parseLong(userId),"头像");
        return R.ok().data("url",url);
    }


    @GetMapping("/uploadOssFile/fileName")
    public R uploadOssFile(
           @RequestParam("fileName") String fileName
    ){
        String url=ossService.uploadFileToOss(fileName);
        return R.ok().data("url",url);
    }


}
