package com.cjm.edu.controller;

import com.cjm.commonutils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author CJiaM
 * @createTime 2020/9/10 0:26
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @PostMapping("login")
    public Result login(){
        System.out.println("login");
        return Result.ok().data("token","admin");
    }

    @GetMapping("info")
    public Result info(){
        System.out.println("info");
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2456468987,3284231714&fm=26&gp=0.jpg");
    }
}
