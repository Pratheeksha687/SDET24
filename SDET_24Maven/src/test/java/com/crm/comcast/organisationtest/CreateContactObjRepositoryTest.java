package com.crm.comcast.organisationtest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtility;
import com.crm.comcast.genericutility.WebDriverUtility;
import com.crm.comcast.objectrepository.ContactInfo;
import com.crm.comcast.objectrepository.Contacts;
import com.crm.comcast.objectrepository.CreateNewContact;
import com.crm.comcast.objectrepository.Home;
import com.crm.comcast.objectrepository.Login;
import com.crm.comcast.objectrepository.OrganizationInfo;

public class CreateContactObjRepositoryTest {
	@Test
	public void CreateContact() throws IOException
	{
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		WebDriver driver=null;
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		
		//test script specific data
		String lastName=eLib.getData("sheet2",1,4)+ "_" +jLib.getRandomNum();
		
		//launch browser
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
		
		//login to application
		Login lp=new Login(driver);
		lp.loginToApp();
		
		//navigate to contact
		Home hp=new Home(driver);
		hp.getContactLnk().click();
		
		//navigate to create contact
		Contacts cn=new Contacts(driver);
		cn.getCreateContact().click();
		
		//create new contact
		CreateNewContact cnc= new CreateNewContact(driver);
		cnc.createContact(lastName);
		
		//verify contact
		ContactInfo cf=new ContactInfo(driver);
		String actContactMsg=cf.getContactMsg().getText();
		if(actContactMsg.contains(lastName))
		{
			System.out.println(lastName+ " lastName is created successfully ==>PASS");
		}
		else
		{
			System.out.println(lastName+ " lastName is not created successfully ==>FAIL");
		}
		
		//step:logout
		hp.logout();
		
		//close the browser
		driver.close();
	}
}
