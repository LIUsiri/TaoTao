package com.taotao.test.fastdfs;

import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * class_name: FastDfsTest
 * package: com.taotao.test.fastdfs
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/24
 * creat_time: 17:27
 **/


public class FastDfsTest {


    @Test
    public void test() throws Exception {
//        第一步：创建配置文件fastdfs.conf ，并设置内容为：track_server=192.168.25.133:22122

//        第二步：加载全局的配置文件
        ClientGlobal.init("F:\\IDEAwks04\\taotao_parent\\taotao_manager_web\\src\\main\\resources\\resource\\fastdfs.conf");
//        第三步：创建trackerClient对象
        TrackerClient trackerClient = new TrackerClient();
//        第四步：创建trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();

//        第五步：创建storageServer对象设置为null即可
        StorageServer storageServer =null;
//        第六步：创建storageClient 的对象
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
//        第七步：使用storageClient对象上传图片
        String[] strings = storageClient.upload_file("F:\\image\\123.png", "png", null);
        for (String string : strings) {
            System.out.println(string);
        }



    }
}
