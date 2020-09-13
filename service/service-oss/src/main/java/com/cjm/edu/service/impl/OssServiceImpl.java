package com.cjm.edu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.cjm.commonutils.exceptions.AvatarUploadFileException;
import com.cjm.edu.service.OssService;
import com.cjm.edu.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author CJiaM
 * @createTime 2020/9/12 23:21
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadOssFile(MultipartFile file) throws AvatarUploadFileException {
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        try {
            // 获取文件流
            InputStream inputStream = file.getInputStream();


            String uuid = UUID.randomUUID().toString().replace("-","");
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            String filename = dataPath + "/" + uuid + file.getOriginalFilename();

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename , inputStream);

            // 上传文件。
            ossClient.putObject(putObjectRequest);

            // 关闭OSSClient。
            ossClient.shutdown();
            // 拼接返回的url
            String url = "https://" + bucketName + "." + endPoint + "/" + filename;

            return url;
        } catch (IOException e) {
            throw new AvatarUploadFileException("头像文件上传异常");
        }

    }
}
