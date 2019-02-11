package com.xuecheng.manage_cms.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class cmsPageRepositoryTest {
//读取容器中的bean
    @Autowired
    CmsPageRepository cmsPageRepository;
}
