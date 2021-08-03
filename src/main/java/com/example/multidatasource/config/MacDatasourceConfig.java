package com.example.multidatasource.config;

import com.example.multidatasource.macMapper.UserMacMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.example.multidatasource.macMapper",sqlSessionFactoryRef = "macSqlSessionFactory")
public class MacDatasourceConfig {

    @Autowired
    @Qualifier("macDatasource")
    public DataSource dataSource;

    @Bean("macSqlSessionFactory")
    @Primary
    public SqlSessionFactory macSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/mapper-mac/UserMacMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate macSqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(macSqlSessionFactory());
        return sqlSessionTemplate;
    }
}
