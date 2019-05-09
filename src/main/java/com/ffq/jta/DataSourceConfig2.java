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

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

import lombok.Data;

@Configuration
@MapperScan(basePackages = "com.ffq.credit", sqlSessionFactoryRef = "db2SqlSessionFactory")
@ConfigurationProperties(prefix = "spring.datasource.db2")
@Data
public class DataSourceConfig2 {
	private String url;
	private String username;
	private String password;
	
	@Bean(name = "db2Datasource")
	public DataSource dbDatasource() throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(url);
		mysqlXADataSource.setPassword(password);
		mysqlXADataSource.setUser(username);
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		atomikosDataSourceBean.setUniqueResourceName("db2Datasource");
		return atomikosDataSourceBean;
	}

	@Bean(name = "db2SqlSessionFactory")
	public SqlSessionFactory dbSqlSessionFactory(@Qualifier("db2Datasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "db2SqlSessionTemplate")
	public SqlSessionTemplate dbSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
