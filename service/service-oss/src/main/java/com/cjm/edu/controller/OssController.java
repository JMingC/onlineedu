package com.cjm.edu.controller;

import com.cjm.commonutils.Result;
import com.cjm.commonutils.exceptions.AvatarUploadFileException;
import com.cjm.edu.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CJiaM
 * @createTime 2020/9/12 23:21
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像
    @PostMapping
    public Result uploadOssFile(MultipartFile file){
        String url = null;
        try {
            url = ossService.uploadOssFile(file);
            return Result.ok().data("url",url);
        } catch (AvatarUploadFileException e) {
            // TODO 错误日志记录
            return Result.error();
        }
    }
}
