package com.hrt.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hrt.commonutils.R;
import com.hrt.oss.client.UserClient;
import com.hrt.oss.service.OssService;
import com.hrt.oss.utils.ConstantPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OssServiceImpl implements OssService {
    @Autowired
    UserClient userClient;

    @Override
    public String uploadFileToOss(MultipartFile multipartFile,
                                  Long userId,
                                  String fileType) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        R r = userClient.getPrimaryInfo(userId);
        String userName = r.getData().get("userName").toString();
        String userTeam = r.getData().get("userTeam").toString();
        String userGroup = r.getData().get("userGroup").toString();

        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String time = df.format(new Date());

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            //获取文件实际名称
            String originalFilename = multipartFile.getOriginalFilename();
            //对名称做更改,指定路径
            String fileNameForUrl =userTeam + "/" + userGroup + "/"  + userName+"/"
                    +fileType+"/"+ time + originalFilename;
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。
            // Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileNameForUrl, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //url命名规则。解决重名问题
            String url = "https://" + bucketName + "." + endpoint + "/" +  fileNameForUrl;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String uploadFileToOss(String fileName) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);

            //获取文件实际名称
            //对文件名称做出修改，定路径,目录
            String fileNameForUrl = "数据导出"+"/" + fileName;
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。
            // Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileNameForUrl, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //url命名规则。解决重名问题
            String url = "https://" + bucketName + "." + endpoint + "/" + fileNameForUrl;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
