package com.cjm.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CJiaM
 * @createTime 2020/9/7 15:18
 */
@ApiModel(value = "teacher查询对象",description = "讲师查询d对象封装")
@Data
public class TeacherQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间",example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间",example = "2019-01-01 10:10:10")
    private String end;

}
