package com.xuecheng.test.freemaeker;

import freemarker.template.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkTest {

    @Test
    public void testGenerateHTML(){
        //定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

    }
}
