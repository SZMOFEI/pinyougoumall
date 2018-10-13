package com.pinyougou.shop.controller;

import com.pingyougou.common.utils.FastDFSClient;
import entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mofei
 * @date 2018/10/13 21:02
 */
@RestController
public class UploadController {
    @Value("${FILE_SERVER_URL}")
    private String config_path;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {

            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            String fileId = fastDFSClient.uploadFile(file.getBytes(), extName);
            String url = config_path + fileId;
            return new Result(true, url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传失败");
        }
    }
}
