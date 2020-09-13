package com.cjm.commonutils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CJiaM
 * @createTime 2020/9/13 23:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduExcelReadException extends RuntimeException {
    private Integer code;
    private String msg;
}
