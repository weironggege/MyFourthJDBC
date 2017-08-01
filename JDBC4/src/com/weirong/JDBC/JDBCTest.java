package com.weirong.JDBC;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Wrapper;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class JDBCTest {

	/*
	 * 使用数据库连接池
	 * 1. 加入jar包
	 * 2. 创建数据库连接池
	 * 3. 为数据源指定属性
	 * 4. 从数据源获取连接
	 */
	@Test
	public void testDBCP() throws SQLException {
		//1. 创建DBCP 数据源实例
		BasicDataSource dataSource = new BasicDataSource();
		
		//2. 为数据源实例指定必须的属性
		dataSource.setUsername("root");
		dataSource.setPassword("bottle19920314");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mysql");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		//指定数据库连接池中初始化连接的个数
		dataSource.setInitialSize(10);
		
		//指定最大的连接数： 同一时刻向数据库申请的连接数
		dataSource.setMaxIdle(50);
		
		//指定数据库连接池中保存的最少的空闲连接的数量
		dataSource.setMinIdle(5);
		
		//等待数据库连接池分配连接的最长时间， 单位为毫秒， 超过时间将抛出异常
		dataSource.setMaxWaitMillis(1000 * 5);
		
		
		
		
		//3. 从数据源中获取数据库连接
		Wrapper connection = dataSource.getConnection();
		System.out.println(connection.getClass());
		
	}

	@Test
	public void testC3P0() throws PropertyVetoException, SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/mysql");
		cpds.setUser("root");
		cpds.setPassword("bottle19920314");
		
		System.out.println(cpds.getConnection());
	}
	
	@Test
	public void testc3p0WithConfigFile() throws SQLException {
		DataSource dataSource = new ComboPooledDataSource("helloc3p0");
		
		System.out.println(dataSource.getConnection());
		
		ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource)dataSource;
		
		System.out.println(comboPooledDataSource.getMaxStatements());
	}
}
