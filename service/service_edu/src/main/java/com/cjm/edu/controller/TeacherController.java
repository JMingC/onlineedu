package com.cjm.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjm.commonutils.Result;
import com.cjm.edu.entity.Teacher;
import com.cjm.edu.entity.vo.TeacherQuery;
import com.cjm.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("items",list);
    }

    @DeleteMapping("{id}")
    public Result removeById(@PathVariable String id){
        if(teacherService.removeById(id)){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,@PathVariable long limit){
        System.out.println(current + " " + limit);
        Page<Teacher> page = new Page<>(current,limit);

        int i = 10/0;
        teacherService.page(page,null);
        System.out.println("page:"  + page.toString());
        long total = page.getTotal();
        System.out.println("total:" + total);
        List<Teacher> list = page.getRecords();
        return Result.ok().data("total",total).data("rows",list);
    }

    //添加讲师
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    //根据讲师id查询
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return Result.ok().data("teacher",teacher);
    }

    //讲师修改
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if (flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @GetMapping("pageTeacherCondition/{current}/{id}")
    public Result pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                       @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<Teacher> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件值是否为null
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("begin",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("end",end);
        }

        //执行
        teacherService.page(page);
        long total = page.getTotal();
        List<Teacher> list = page.getRecords();

        return Result.ok().data("total",total).data("rows",list);
    }
}

