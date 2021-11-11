package db_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class Script1 {
	public static void main(String[] args) throws InterruptedException, SQLException
	{
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://localhost:8084/");
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys("Flipkart");
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Santhosh");
		WebElement ele = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select s=new Select(ele);
		s.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click
		();
		driver.quit();
		Driver diverref=new Driver();
		DriverManager.registerDriver(diverref);
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement stat = con.createStatement();
		ResultSet set = stat.executeQuery("select * from project");
		while(set.next())
		{
			if(set.getString(2).equals("Santhosh"))
			{
				System.out.println("project created");
			}
			
		}
		
		 con.close();	
		 
		}
		
}

