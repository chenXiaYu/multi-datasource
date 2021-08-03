package com.example.multidatasource.linuxMapper;

import com.example.multidatasource.bean.UserLinux;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLinuxMapper {
    int insertUserLinux(@Param("userLinux") UserLinux userLinux);
    void updateUserLinux(@Param("userLinux") UserLinux userLinux);
}
