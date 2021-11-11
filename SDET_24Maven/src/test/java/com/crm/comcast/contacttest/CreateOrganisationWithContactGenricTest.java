package com.crm.comcast.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtility;
import com.crm.comcast.genericutility.WebDriverUtility;

public class CreateOrganisationWithContactGenricTest {
	@Test
	public void CreateOrgContactsGeneric() throws IOException, InterruptedException
	{
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		
		String orgName=eLib.getData("sheet2",1,2)+ "_" +jLib.getRandomNum();
		String lastName=eLib.getData("sheet2",1,4)+ "_" +jLib.getRandomNum();
		
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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(3000);
		WebElement ele=driver.findElement(By.linkText("Contacts"));
		
		wLib.waitForElementVisibility(driver,ele);
		
		ele.click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		wLib.switchToWindow(driver, "Accounts&action");
		driver.findElement(By.name("search_text")).sendKeys(orgName);
	    driver.findElement(By.name("search")).click();
	    driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
	    
	    wLib.switchToWindow(driver, "Administrator - Contacts");
	    Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
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
		
		wLib.mouseOver(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
		
	}

}
