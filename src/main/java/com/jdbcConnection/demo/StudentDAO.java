package com.jdbcConnection.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class StudentDAO {

	private String driver;
	private String url;
	private String userName;
	private String password;
	
	Connection con;
	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@PostConstruct
	public void init() throws ClassNotFoundException, SQLException
	{
		System.out.println("inside the custom init method");
		studentDBConnection();
	}
	
	
	public void studentDBConnection() throws ClassNotFoundException, SQLException
	{
		//loan driver
				Class.forName(driver);
				
				//get a connection
				con=DriverManager.getConnection(url, userName, password);
	}

	public void selectAllRows() throws ClassNotFoundException, SQLException
	{
		
		
		//execute query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM college.student;");
		while(rs.next())
		{
			int roll_No=rs.getInt(1);
			String name=rs.getString(2);
			int age=rs.getInt(3);
			
			
			System.out.println(roll_No+ "" +name+ "" +age);
		}
		
	}
	
	public void deleteStudentRecord(int roll_No) throws ClassNotFoundException, SQLException
	{
		//loan driver
				Class.forName(driver);
				
				//get a connection
				Connection con=DriverManager.getConnection(url, userName, password);
				
				Statement stmt=con.createStatement();
				stmt.execute("delete from college.student where roll_No = " +roll_No);
				System.out.println("Record deleted with the id" +roll_No);
	}
	@PreDestroy
	public void closeConnection() throws SQLException
	{
		System.out.println("destroy the connection");
		con.close();
	}
}
