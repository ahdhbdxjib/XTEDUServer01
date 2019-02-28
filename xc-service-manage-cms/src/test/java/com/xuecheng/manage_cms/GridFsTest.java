package com.xuecheng.manage_cms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {
    //读取容器中的bean
    @Autowired
    GridFsTemplate gridFsTemplate;
    //下载文件的ben
    @Autowired
    GridFSBucket gridFSBucket;
    //测试文件的存储
    @Test
    public void testRestStore() throws Exception {
        File file= new File("F:\\MyProject\\XTEDU\\Test\\index_banner.ftl");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId = gridFsTemplate.store(fileInputStream, "index_banner.ftl");
        System.out.println(objectId);

        //存储的文件的id 5c764c2677e8002d2059f192
    }

    //下载文件
    @Test
    public void testRestdownload() throws Exception {
        //查询
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5c764c2677e8002d2059f192")));
//打开下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
//获取下载流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        String content = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        System.out.println(content);

    }

}
