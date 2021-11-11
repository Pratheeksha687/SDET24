package com.crm.comcast.organisationcontacttest;


import org.testng.annotations.Test;
import com.crm.comcast.genericutility.BaseClass;
import com.crm.comcast.objectrepository.CreateNewOrganization;
import com.crm.comcast.objectrepository.Home;
import com.crm.comcast.objectrepository.OrganizationInfo;
import com.crm.comcast.objectrepository.Organizations;

	public class CreateOrganisationWithIndustryAndTypeTest  extends BaseClass{
		@Test(groups="regressionTest")
		public void CreateOrganizationWithIndustryAndTypeTC2() throws Throwable
		{
			//test script specific data
			String orgName=eLib.getData("sheet1",1,2)+ "_" +jLib.getRandomNum();
			String industry=eLib.getData("sheet1",4,4);
			String type=eLib.getData("sheet1",4,5);
			
			//STEP 2:navigate to Org
	
			
			
			
			Home hp=new Home(driver);
			hp.getOrganizationLnk().click();
		
			//STEP 3:navigate to create org page
			Organizations op=new Organizations(driver);
			op.getCreateOrgImg().click();
		
			//STEP 4:create org
			CreateNewOrganization cnp=new CreateNewOrganization(driver);
			cnp.createOrg(orgName, industry, type);
		
		
			//verify organisation
			OrganizationInfo of=new OrganizationInfo(driver);
			String actMsg=of.getOrgText().getText();
			if(actMsg.contains(orgName))
			{
				System.out.println(orgName+ " org is created successfully ==>PASS");
			}
			else
			{
				System.out.println(orgName+ " org is not created successfully ==>FAIL");
			}
			
			//verify industry
			String actIndustryMsg=of.getIndustryText().getText();
			if(actIndustryMsg.contains(industry))
			{
				System.out.println(industry+ " industry is created successfully ==>PASS");
			}
			else
			{
				System.out.println(industry+ " industry is not created successfully ==>FAIL");
			}
			//verify type
			String actType=of.getTypeText().getText();
			if(actType.contains(type))
			{
				System.out.println(type+ " type is created successfully ==>PASS");
			}
			else
			{
				System.out.println(type+ " type is not created successfully ==>FAIL");
			}
		
		}

	}


