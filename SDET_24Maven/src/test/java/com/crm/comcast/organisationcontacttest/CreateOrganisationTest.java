package com.crm.comcast.organisationcontacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseClass;
import com.crm.comcast.objectrepository.CreateNewOrganization;
import com.crm.comcast.objectrepository.Home;
import com.crm.comcast.objectrepository.Login;
import com.crm.comcast.objectrepository.OrganizationInfo;
import com.crm.comcast.objectrepository.Organizations;

public class CreateOrganisationTest extends BaseClass {
	@Test(groups="smokeTest")
	public void CreateOrganisation() throws EncryptedDocumentException, IOException{
		
		//test script specific data		
			String orgName=eLib.getData("sheet1",1,2)+ "_" +jLib.getRandomNum();
		
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
			
	}
}