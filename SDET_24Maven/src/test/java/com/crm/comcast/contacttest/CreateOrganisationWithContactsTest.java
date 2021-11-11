package com.crm.comcast.contacttest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;

public class CreateOrganisationWithContactsTest {

	@Test
	public void CreateOrgContact() throws IOException, InterruptedException
	{
		//step1 create object for 
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		
		//step
		Random random= new Random();
		int randomNum=random.nextInt(100);
		System.out.println(randomNum);
		
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
	
		String orgName=eLib.getData("sheet2",1,2)+ "_" +randomNum;
		String lastName=eLib.getData("sheet2",1,4)+ "_" +randomNum;
		
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
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Contacts"))));
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		
		//switch control to child window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())
		{
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String title = driver.getTitle();
			if(title.contains("Accounts&action"))
					{
				      break;
				      
					}
		}
		driver.findElement(By.name("search_text")).sendKeys(orgName);
	      driver.findElement(By.name("search")).click();
	      driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
		
	      
		//switch control to parent window
	      Iterator<String> it1=set.iterator();
		while(it1.hasNext())
		{
			String windowId1 = it1.next();
			
			driver.switchTo().window(windowId1);
			String title = driver.getTitle();
			if(title.contains("Administrator - Contacts"))
					{
				
				      break;
					}
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		
		//verify
		WebElement contactName = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String actContactName=contactName.getText();
		if(actContactName.contains(lastName))
		{
			System.out.println("Contact has been created==>pass");
		}
		else
		{
			System.out.println("Contact has not been created==>fail");
			
		}
		Actions act=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
		
		}

	}
