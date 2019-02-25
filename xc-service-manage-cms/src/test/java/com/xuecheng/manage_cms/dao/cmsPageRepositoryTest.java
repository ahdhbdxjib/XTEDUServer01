package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class cmsPageRepositoryTest {
//读取容器中的bean
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println("测试结果："+all);
    }
    /**
     * 测试分页查询
     */
    @Test
    public void testFindPage(){
        int page = 0;
        int size =10;
        //使用封装的分页查询
        Pageable pageable = new PageRequest(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    @Test
    public void testUpdata(){
        Optional<CmsPage> optional = cmsPageRepository.findById("5a754adf6abb500ad05688d9");
        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("首页");
            cmsPageRepository.save(cmsPage);
            System.out.println("测试成功！！");
        }
    }

    //测试自定义方法
    @Test
    public void testMyMethod(){
        CmsPage byPageName = cmsPageRepository.findByPageName("index.html");
        System.out.println(byPageName);

    }
    @Test
    public void testFindAllByExample(){
        int page = 0,size = 10;
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageAliase("课程");
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        需要将返回的对象赋值给匹配器

        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        for (CmsPage cmsPage1 : all){
            System.out.println(cmsPage1);
        }

    }
}
