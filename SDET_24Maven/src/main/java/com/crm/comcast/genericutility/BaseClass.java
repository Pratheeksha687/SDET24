package com.crm.comcast.genericutility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.objectrepository.Home;
import com.crm.comcast.objectrepository.Login;

public class BaseClass {
	public WebDriver driver;
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public DatabaseUtility dLib=new DatabaseUtility();

	@BeforeSuite(groups={"smokeTest","regressionTest"})
	public void configBs()
	{
		System.out.println("=====connect to database======");
		dLib.connectDB();
		
	}
	@Parameters(value="BROWSER")
	@BeforeClass(groups={"smokeTest","regressionTest"})
	//public void configBc(String BROWSER) throws IOException
	public void configBc() throws IOException
	{
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		
		//launch the browser
		if(BROWSER.equals("chrome"))
	{
		driver=new ChromeDriver();
	}else if(BROWSER.equals("firefox"))
	{
		driver=new FirefoxDriver();
		
	}else if(BROWSER.equals("InternetExplorer"))
    {
		driver=new InternetExplorerDriver();
	}
		driver.get(URL);
		wLib.waitUntilPageLoad(driver);
	}
	
	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBm() throws IOException
	{
		Login lp=new Login(driver);
		lp.loginToApp();
	}
	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configAm()
	{
		Home hp=new Home(driver);
		hp.logout();
	}
	@AfterClass(groups={"smokeTest","regressionTest"})
	public void configAc()
	{
		driver.close();
		
	}
	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void configAs() throws SQLException
	{
		System.out.println("DB connection is close");
		dLib.closeDB();
	}
}
