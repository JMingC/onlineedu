package com.cjm.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cjm.commonutils.exceptions.EduExcelReadException;
import com.cjm.edu.entity.Subject;
import com.cjm.edu.entity.excel.SubjectData;
import com.cjm.edu.service.SubjectService;

import java.util.Map;

/**
 * @author CJiaM
 * @createTime 2020/9/13 23:36
 */
public class SubjectExcelListen extends AnalysisEventListener<SubjectData> {

    // 因为这个类没有用bean相关注解注释，需要自己new，所以不能@Autowired注入其他对象
    // 不能实现数据库操作
    public SubjectService subjectService;

    public SubjectExcelListen(){

    }

    public SubjectExcelListen(SubjectService subjectService){
        this.subjectService = subjectService;
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new EduExcelReadException(20001,"文件数据为空");
        }

        //一行一行读取，每次读取有两个值，一个一级分类，一个二级分类
        //判断一级分类不重复
        Subject existOneSubject = existOneSubject(subjectService,subjectData.getOneSubjectName());
        if (existOneSubject == null){
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        //判断二级分类不重复
        String pid = existOneSubject.getId();

        Subject existTwoSubject = existTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
        if (existTwoSubject == null){
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级分类是否重复
    private Subject existOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    private Subject existTwoSubject(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
}
