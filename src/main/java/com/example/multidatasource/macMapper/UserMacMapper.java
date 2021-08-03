package com.example.multidatasource.macMapper;

import com.example.multidatasource.bean.UserMac;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMacMapper {
    int  insertUserMac(@Param("userMac") UserMac userMac);
    void updateUserMac(@Param("userMac") UserMac userMac);
}
