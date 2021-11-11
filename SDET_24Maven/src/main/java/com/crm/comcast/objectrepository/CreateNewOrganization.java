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

public class CreateNewOrganization {
	
	WebDriverUtility wLib= new WebDriverUtility();
	public CreateNewOrganization(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	

	@FindBy(xpath="//select[@name='industry']")
	private WebElement industryDropDown;
	
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement typeDropDown;
	
	public void createOrg(String OrgName)
	{
		orgNameEdt.sendKeys(OrgName);
		saveBtn.click();
	}
	public void createOrg(String OrgName,String industry)
	{
		orgNameEdt.sendKeys(OrgName);
		wLib.select(industryDropDown, industry);
		saveBtn.click();
	}
	
	public void createOrg(String OrgName,String industry, String type)
	{
		orgNameEdt.sendKeys(OrgName);
		wLib.select(industryDropDown, industry);
		wLib.select(typeDropDown, type);
		saveBtn.click();
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
}
