package com.crm.comcast.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.WebDriverUtility;
/**
 * 
 * @author Pratheeksha
 *
 */
public class Home {
	WebDriverUtility wLib=new WebDriverUtility();
	WebDriver driver; //global driver variable
	public Home(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="Organizations")
	private WebElement organizationLnk;
		
	@FindBy(linkText="Products")
	private WebElement productLnk;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminstratorImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLnk;
	
	public WebElement getProductLnk() {
		return productLnk;
	}
	public WebElement getContactLnk() {
		return contactLnk;
	}
	public void setProductLnk(WebElement productLnk) {
		this.productLnk = productLnk;
	}

	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}
	
	public WebElement getAdminstratorImg() {
		return adminstratorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	public void logout()
	{
		wLib.mouseOver(driver, adminstratorImg);
		signOutLnk.click();
	}
	
	

}
