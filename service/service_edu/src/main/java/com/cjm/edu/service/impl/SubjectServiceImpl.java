package com.cjm.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.cjm.edu.entity.Subject;
import com.cjm.edu.entity.excel.SubjectData;
import com.cjm.edu.listener.SubjectExcelListen;
import com.cjm.edu.mapper.SubjectMapper;
import com.cjm.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author CJM
 * @since 2020-09-13
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file,SubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListen(subjectService)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
