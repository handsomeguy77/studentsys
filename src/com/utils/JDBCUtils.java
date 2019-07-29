package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils 
{
	
	
	private static ComboPooledDataSource ds= new ComboPooledDataSource();
	
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	
	// ��ȡthreadLocal
	public static ThreadLocal getThreadLocal()
	{
		return tl;
	} 
	
	// ��ȡc3p0���ӳ�
	public static DataSource getDataSource()
	{
		return ds;
	}
	
	// ��ȡ����
	public static Connection getConnection() throws SQLException
	{
		
		
		return ds.getConnection();
	}
	
	// �ͷ���Դ
	public static void CloseZy(Connection con,Statement st,ResultSet rs)
	{
		
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null)
		{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
