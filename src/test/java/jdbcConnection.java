import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;


public class jdbcConnection {

			@Test
		public  void TestDB() throws ClassNotFoundException, SQLException  {

	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/Employeeportal","root","root");  


Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("SELECT * FROM Employeeinfo");  

System.out.println("  *****************  Employeeinfo    *****************");




while(rs.next())  
{
	System.out.println(rs.getString(1));
	System.out.println(rs.getInt(2));
	System.out.println(rs.getString(3));
	System.out.println(rs.getInt(4));
}
}}


/*

@Test
public  void TestDB() throws ClassNotFoundException, SQLException  {

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employeeportal","root","");  


Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("SELECT * FROM Employeeinfo");  

System.out.println("  *****************  Employeeinfo    *****************");




while(rs.next())  
{
System.out.println(rs.getString(1));
System.out.println(rs.getInt(2));
System.out.println(rs.getString(3));
System.out.println(rs.getInt(4));
}
}}*/