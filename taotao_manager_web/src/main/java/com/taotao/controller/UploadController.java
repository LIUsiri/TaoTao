package com.taotao.controller;

import com.taotao.common.utils.FastDFSClient;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * class_name: UploadController
 * package: com.taotao.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/24
 * creat_time: 20:26
 **/

@Controller
public class UploadController {

    @Value("${TAOTAO_IMAGE_SERVER_URL}")
    private String TAOTAO_IMAGE_SERVER_URL;

    @RequestMapping(value="/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String upload(MultipartFile uploadFile){

        try {
            //1.获取文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //2.创建一个FastDfs的客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
            //3.执行上传操作
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //4.拼接返回的url和ip地址,拼装成完整的url
            String url=TAOTAO_IMAGE_SERVER_URL+path;
            //5.返回map
            Map<String, Object> map = new HashMap<>();
            map.put("error",0);
            map.put("url",url);
            return JsonUtils.objectToJson(map);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("error",0);
            map.put("url","图片上传失败");
            return JsonUtils.objectToJson(map);
        }
    }

}
