package com.crm.comcast.organisationtest;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;

public class CreateOrganisationXml2 {
	@Test
	public void CreateOrg(XmlTest xml) throws InterruptedException, EncryptedDocumentException, IOException
	{
		//FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		
		Random random= new Random();
		int randomNum=random.nextInt(100);
		System.out.println(randomNum);
		
		String BROWSER=xml.getParameter("browser");
		String URL=xml.getParameter("url");
		String USERNAME=xml.getParameter("username");
		String PASSWORD=xml.getParameter("password");
		
	
		String orgName=eLib.getData("sheet1",1,2)+ "_" +randomNum;
		WebDriver driver=null;
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

	driver.findElement(By.xpath("//a[.='Organizations']")).click();
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(2000);
		WebElement ele2=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String val2=ele2.getText();
		if(val2.contains(orgName))
		{
			System.out.println(orgName+"==>pass==>verified");
		}
		else
		{
			System.out.println(orgName+"==>fail==>not verified");
		}
		driver.close();
		}
	}


