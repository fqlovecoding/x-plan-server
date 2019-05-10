package com.ffq.jta;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

import lombok.Data;

@Configuration
@MapperScan(basePackages = {"com.ffq.order","com.ffq.user","com.ffq.store"}, sqlSessionFactoryRef = "db1SqlSessionFactory")
@ConfigurationProperties(prefix = "spring.datasource.db1")
@Data
public class DataSourceConfig1 {
	private String url;
	private String username;
	private String password;
	
	@Primary
	@Bean(name = "db1Datasource")
	public DataSource dbDatasource() throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(url);
		mysqlXADataSource.setPassword(password);
		mysqlXADataSource.setUser(username);
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		atomikosDataSourceBean.setUniqueResourceName("db1Datasource");
		return atomikosDataSourceBean;
	}

	@Primary
	@Bean(name = "db1SqlSessionFactory")
	public SqlSessionFactory dbSqlSessionFactory(@Qualifier("db1Datasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Primary
	@Bean(name = "db1SqlSessionTemplate")
	public SqlSessionTemplate dbSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
