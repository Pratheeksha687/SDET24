package com.crm.comcast.cmdprompt;

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

public class CreateOrgCmdTest {
	@Test
	public void CreateOrgTest() throws EncryptedDocumentException, IOException
	{
		FileUtility fLib=new FileUtility();
		Random random= new Random();
		int randomNum=random.nextInt(100);
		System.out.println(randomNum);
		String BROWSER=System.getProperty("browser");
		String URL=System.getProperty("url");
		String USERNAME=System.getProperty("username");
		String PASSWORD=System.getProperty("password");
		
		ExcelUtility eLib=new ExcelUtility();
		//String orgName=eLib.getData("sheet1",1,2);
		
		String orgName=eLib.getData("sheet1",4,2)+ "_" +randomNum;
		String industry=eLib.getData("sheet1",4,4);
		String type=eLib.getData("sheet1",4,5);
	
		
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
		WebElement ele=driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel=new Select(ele);
		sel.selectByVisibleText(industry);
		WebElement ele1=driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select sel1=new Select(ele1);
		sel1.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
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

