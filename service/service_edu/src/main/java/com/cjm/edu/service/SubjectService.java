package com.cjm.edu.service;

import com.cjm.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author CJM
 * @since 2020-09-13
 */
public interface SubjectService extends IService<Subject> {
    //添加课程分类
    void saveSubject(MultipartFile file,SubjectService subjectService);
}
