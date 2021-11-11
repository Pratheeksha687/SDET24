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
import com.crm.comcast.objectrepository.CreateNewOrganization;
import com.crm.comcast.objectrepository.Home;
import com.crm.comcast.objectrepository.Login;
import com.crm.comcast.objectrepository.OrganizationInfo;
import com.crm.comcast.objectrepository.Organizations;
/**
 * 
 * @author Pratheeksha
 *
 */
public class CreateOrganizationObjRepositoryTest {
	@Test
	public void CreateOrg() throws IOException
	{
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		WebDriver driver=null;
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		
		//test script specific data		
		String orgName=eLib.getData("sheet1",1,2)+ "_" +jLib.getRandomNum();
		
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

	//login to app
	wLib.waitUntilPageLoad(driver);
	Login lp=new Login(driver);
	lp.loginToApp();
	
	//navigate to Org
	Home hp=new Home(driver);
	hp.getOrganizationLnk().click();
	
	//navigate to create org page
	Organizations op=new Organizations(driver);
	op.getCreateOrgImg().click();
	
	//create org
	CreateNewOrganization cnp=new CreateNewOrganization(driver);
	cnp.createOrg(orgName);
	
	//verify organisation
	OrganizationInfo of=new OrganizationInfo(driver);
	String actMsg=of.getOrgText().getText();
	if(actMsg.contains(orgName))
	{
		System.out.println("org is created successfully ==>PASS");
	}
	else
	{
		System.out.println("org is not created successfully ==>FAIL");
	}
	
	//step:logout
	hp.logout();
	
	//close the browser
	driver.close();
	
	
	
}
}
