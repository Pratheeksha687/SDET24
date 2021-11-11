package com.crm.comcast.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
static Driver driver;
static Connection connection;
static ResultSet result;


/**
 * This method will perform the mysql database connection
 */
public void connectDB()
{
	try{
		driver=new Driver();
		DriverManager.registerDriver(driver);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "root");
	} catch(SQLException e)
	{
		e.printStackTrace();

    }
	
}
/**
 * This method will close mysql database connection
 * @throws SQLException
 */
public void closeDB() throws SQLException
{
	connection.close();
}
public ResultSet executeQuery(String query)
{
	try{
		result=connection.createStatement().executeQuery(query);
		return result;
	} catch(SQLException e){
		e.printStackTrace();
	}
	return result;
	}
/**
 * This method will execute  the query based on query and it will verify the data
 * @param querry
 * @param columnName
 * @param expectedData
 * @return
 * @throws SQLException 
 */

public String executeQueryAndGetData(String querry, int columnName,String expectedData) throws SQLException
{
	boolean flag=false;
	result=connection.createStatement().executeQuery(querry);
	while(result.next()) {
		if(result.getString(columnName).equals(expectedData)) {
			flag=true;
			break;
		}
	}
	if(flag) {
		System.out.println(expectedData+"==>Data is verifed in the data base table");
		return expectedData;
		} else {
			System.out.println(expectedData+"==>Data is not verifed in the data base table");
			return expectedData;
	}
}
}
