package com.example.multidatasource.service;

import com.example.multidatasource.bean.UserLinux;
import com.example.multidatasource.bean.UserMac;
import com.example.multidatasource.linuxMapper.UserLinuxMapper;
import com.example.multidatasource.macMapper.UserMacMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Transactional
public class MixService {

    @Autowired
    private SqlSessionTemplate linuxSqlSessionTemplate;

    @Autowired
    private SqlSessionTemplate macSqlSessionTemplate;

    public  UserMacMapper userMacMapper;
    public  UserLinuxMapper userLinuxMapper;

    @PostConstruct
    public void init(){
        userMacMapper = macSqlSessionTemplate.getMapper(UserMacMapper.class);
        userLinuxMapper = linuxSqlSessionTemplate.getMapper(UserLinuxMapper.class);
    }

    public void insertUserMac(UserMac userMac){
        userMacMapper.insertUserMac(userMac);
    }

    public void  insertUserLinux(UserLinux userLinux){
        userLinuxMapper.insertUserLinux(userLinux);
    }

    public void insertBoth(UserMac userMac,UserLinux userLinux){
        userLinuxMapper.insertUserLinux(userLinux);
        userMacMapper.insertUserMac(userMac);
    }
}
