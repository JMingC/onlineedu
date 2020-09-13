package com.cjm.edu.controller;


import com.cjm.commonutils.Result;
import com.cjm.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author CJM
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //添加课程分类（excel导入）
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        //上传过来的excel文件

        subjectService.saveSubject(file,subjectService);

        return Result.ok();
    }
}

