package com.crm.comcast.objectrepository;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.FileUtility;
/**
 * 
 * @author Pratheeksha
 *
 */
public class Login {
//Rule1: Create a seperate java class for every page in a application
	WebDriver driver;
	FileUtility fLib=new FileUtility();
	public Login(WebDriver driver)
	{
		this.driver=driver;
		//Rule 3:Execute all the elements &initialize the element PageFactory.initElemens[initialization]
		PageFactory.initElements(driver, this);
	}
	//Rule 2:Identify all the elements using @findBy @findBys and @findAll (Declaration)
	@FindBy(name="user_name")
	private WebElement userNameEdt;
	
	@FindBy(name="user_password")
	private WebElement userPasswordEdt;
	
	@FindAll({@FindBy(id="submitButton"), @FindBy(xpath="//input[@id='submitButton']")})
	private WebElement loginBtn;
	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getUserPasswordEdt() {
		return userPasswordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
		//Rule 4:Declare all the elements as private & provide getters method, business method for (utilization)
	}
	//login to app
	public void loginToApp() throws IOException
	{
		
		driver.get(fLib.getPropertyKeyValue("url"));
		userNameEdt.sendKeys(fLib.getPropertyKeyValue("username"));
		userPasswordEdt.sendKeys(fLib.getPropertyKeyValue("password"));
		loginBtn.click();
	}
	//login to app overloaded method 
	public void loginToApp(String username, String password) throws IOException
	{
		driver.get(fLib.getPropertyKeyValue("url"));
		userNameEdt.sendKeys(username);
		userPasswordEdt.sendKeys(password);
		loginBtn.click();
	}
}
   
	
	