package com.crm.comcast.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * 
 * 
 * @author Pratheeksha
 *
 */

public class OrganizationInfo {

	public OrganizationInfo(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement OrgText;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement industryText;
	
	@FindBy(xpath="//span[@id='dtlview_Type']")
	private WebElement typeText;

	
	public WebElement getOrgText() {
		return OrgText;
	}

	public WebElement getIndustryText() {
		return industryText;
	}

	public WebElement getTypeText() {
		return typeText;
	}

	
	
	
	
	
}
