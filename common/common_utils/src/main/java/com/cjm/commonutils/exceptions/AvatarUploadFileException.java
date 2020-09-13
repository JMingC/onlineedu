package com.cjm.commonutils.exceptions;

import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * @author CJiaM
 * @createTime 2020/9/12 23:33
 */
@NoArgsConstructor
public class AvatarUploadFileException extends IOException {
    public AvatarUploadFileException(String msg){
        super(msg);
    }
}
