package com.crm.comcast.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.WebDriverUtility;

public class CreateNewContact extends WebDriverUtility{
	WebDriver driver=null;
	public CreateNewContact(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement selectOrgBtn;
		
	@FindBy(xpath="//input[@class='crmButton small save']")
	private WebElement saveBtn;

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSelectOrgBtn() {
		return selectOrgBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createContact(String lastname)
	{
		lastNameEdt.sendKeys(lastname);
		saveBtn.click();
	}
	public void createContact(String lastname, String orgname) throws InterruptedException
	{
		lastNameEdt.sendKeys(lastname);
		selectOrgBtn.click();
		
		switchToWindow(driver,"Accounts&action");
		Organizations op=new Organizations(driver);
		
		
		op.getSearchEdt().sendKeys(orgname);
		op.getSearchBtn().click();
		
		int count=0;
		while(count<20){
			try{
				driver.findElement(By.xpath("//a[.='"+orgname+"']")).click();
				break;
			}
			catch(Exception e){
				count++;
				Thread.sleep(1000);
			}
		}
		switchToWindow(driver,"Contacts");
		saveBtn.click();
	}
	
}
