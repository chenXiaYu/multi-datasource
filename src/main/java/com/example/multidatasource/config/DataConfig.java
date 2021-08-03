package com.example.multidatasource.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

@Configuration
public class DataConfig {

    @Bean("macDatasource")
    public DataSource macDataSource(){
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName(MysqlXADataSource.class.getName());
        ds.setUniqueResourceName("macDataSource");
        Properties pros = new Properties();
        pros.setProperty("url","jdbc:mysql://localhost:3306/local-t?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
        pros.setProperty("password","root");
        pros.setProperty("user","root");
        ds.setXaProperties(pros);
        ds.setPoolSize(5);
        return  ds;
    }

    @Bean("linuxDatasource")
    public DataSource linuxDataSource(){
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName(MysqlXADataSource.class.getName());
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        ds.setUniqueResourceName("linuxDataSource");
        ds.setPoolSize(5);
        Properties pros = new Properties();
        pros.setProperty("url","jdbc:mysql://192.168.15.164:3306/linux-mysql?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
        pros.setProperty("password","123456");
        pros.setProperty("user","root");
        ds.setXaProperties(pros);
        return  ds;
    }


    @Bean
    public JtaTransactionManager transactionManager(){
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction ,userTransactionManager);
    }
}
