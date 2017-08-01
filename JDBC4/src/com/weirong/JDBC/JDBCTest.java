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
	 * ʹ�����ݿ����ӳ�
	 * 1. ����jar��
	 * 2. �������ݿ����ӳ�
	 * 3. Ϊ����Դָ������
	 * 4. ������Դ��ȡ����
	 */
	@Test
	public void testDBCP() throws SQLException {
		//1. ����DBCP ����Դʵ��
		BasicDataSource dataSource = new BasicDataSource();
		
		//2. Ϊ����Դʵ��ָ�����������
		dataSource.setUsername("root");
		dataSource.setPassword("bottle19920314");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mysql");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		//ָ�����ݿ����ӳ��г�ʼ�����ӵĸ���
		dataSource.setInitialSize(10);
		
		//ָ�������������� ͬһʱ�������ݿ������������
		dataSource.setMaxIdle(50);
		
		//ָ�����ݿ����ӳ��б�������ٵĿ������ӵ�����
		dataSource.setMinIdle(5);
		
		//�ȴ����ݿ����ӳط������ӵ��ʱ�䣬 ��λΪ���룬 ����ʱ�佫�׳��쳣
		dataSource.setMaxWaitMillis(1000 * 5);
		
		
		
		
		//3. ������Դ�л�ȡ���ݿ�����
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
