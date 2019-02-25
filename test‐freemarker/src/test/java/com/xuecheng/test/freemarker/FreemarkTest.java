package com.xuecheng.test.freemarker;

import com.xuecheng.test.freemarker.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemarkTest {

    @Test
    public void testGenerateHTML() throws IOException, TemplateException {
        //定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

        String classpath = this.getClass().getResource("/").getPath();
        //定义模板1
//        configuration.setDirectoryForTemplateLoading(new File("F:\\MyProject\\XTEDU\\XTEDUServer01\\test‐freemarker\\src\\test\\resources\\templates\\"));
        configuration.setDirectoryForTemplateLoading(new File(classpath+"templates"));
        //定义数据模型
        Template template = configuration.getTemplate("test1.ftl");
        //获取map数据
        Map map = getMap();
        //静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(content);

    }
    //通过字符串获得模板
    @Test
    public void testGenerateHTMLByString() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        String stringTemplates = "" +
                "<html>\n" +
                "    <head></head>\n" +
                "    <body>\n" +
                "    名称：${name}\n" +
                "    </body>\n" +
                "</html>";
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",stringTemplates);
        configuration.setTemplateLoader(stringTemplateLoader);
        //获取模板
        Template template = configuration.getTemplate("template");

        //获取数据
        Map map = getMap();

        //页面静态化

        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(content);

    }

    private Map getMap() {
        Map map = new HashMap();
        map.put("name", "java程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        return map;
    }
}

