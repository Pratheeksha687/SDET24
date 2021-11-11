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

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtility;
import com.crm.comcast.genericutility.WebDriverUtility;

public class CreateOrganisationWithIndustryAndTypeGenericTest {
	@Test
	public void CreateOrgIndustryAndType() throws InterruptedException, EncryptedDocumentException, IOException
	{
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		
		
		String orgName=eLib.getData("sheet1",4,2)+ "_" +jLib.getRandomNum();
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
		wLib.waitUntilPageLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

	driver.findElement(By.xpath("//a[.='Organizations']")).click();
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		WebElement ele=driver.findElement(By.xpath("//select[@name='industry']"));
		wLib.select(ele, industry);
		WebElement ele1=driver.findElement(By.xpath("//select[@name='accounttype']"));
		wLib.select(ele1, type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		WebElement ele2=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String actOrgName=ele2.getText();
		if(actOrgName.contains(orgName))
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

