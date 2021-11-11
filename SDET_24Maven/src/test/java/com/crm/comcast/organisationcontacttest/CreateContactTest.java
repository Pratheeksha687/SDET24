package com.crm.comcast.organisationcontacttest;

import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseClass;
import com.crm.comcast.objectrepository.ContactInfo;
import com.crm.comcast.objectrepository.Contacts;
import com.crm.comcast.objectrepository.CreateNewContact;
import com.crm.comcast.objectrepository.Home;

public class CreateContactTest extends BaseClass {

		 @Test(groups="smokeTest")
	     public void createContact()throws Exception
	     {	
	 	  	//Initialize the variables
			 String lastName=eLib.getData("sheet2",1,4)+ "_" +jLib.getRandomNum();
	 
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
	     }
}