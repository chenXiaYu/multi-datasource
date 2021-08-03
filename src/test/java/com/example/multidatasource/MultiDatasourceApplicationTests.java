package com.example.multidatasource;

import com.example.multidatasource.bean.UserLinux;
import com.example.multidatasource.bean.UserMac;
import com.example.multidatasource.linuxMapper.UserLinuxMapper;
import com.example.multidatasource.macMapper.UserMacMapper;
import com.example.multidatasource.service.MixService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MultiDatasourceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MixService mixService;


    @Test
    public void insertUserLinux(){
        UserLinux userLinux = new UserLinux();
        userLinux.setAge(998);
        userLinux.setName("chenxiayu");
        //
        UserMac userMac = new UserMac();
        userMac.setJobType("开发仔");
        userMac.setName("陈夏雨");
        userMac.setSex("男");
        //
        mixService.insertBoth(userMac,userLinux);
    }

    @Autowired
    @Qualifier("linuxSqlSessionTemplate")
    private SqlSessionTemplate linuxSqlSessionTemplate;
    @Test
    public void test2(){
        UserLinuxMapper mapper = linuxSqlSessionTemplate.getMapper(UserLinuxMapper.class);
        UserLinux userLinux = new UserLinux();
        userLinux.setAge(18);
        userLinux.setName("chenlong");
        userLinux.setId(1);
       // mapper.insertUserLinux(userLinux);
        mapper.updateUserLinux(userLinux);
    }

    @Autowired
    @Qualifier("macSqlSessionTemplate")
    public SqlSessionTemplate macSqlSessionTemplate;
    @Test
    public void Test3(){
        UserMacMapper mapper = macSqlSessionTemplate.getMapper(UserMacMapper.class);

        UserMac userMac = new UserMac();
        userMac.setJobType("老板");
        userMac.setName("陈夏雨");
        userMac.setSex("男");
        //userMac.setId(1);
        mapper.insertUserMac(userMac);
    }

}
