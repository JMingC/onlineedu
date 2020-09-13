package com.cjm.edu.service;

import com.cjm.commonutils.exceptions.AvatarUploadFileException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CJiaM
 * @createTime 2020/9/12 23:21
 */
public interface OssService {
    String uploadOssFile(MultipartFile file) throws AvatarUploadFileException;
}
