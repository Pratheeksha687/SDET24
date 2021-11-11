package com.crm.comcast.organisationtest;

import org.testng.Reporter;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Create_OrgTest {
	@Test(retryAnalyzer= com.crm.comcast.genericutility.RetryAnalyzer.class)
	public void create_org() {
		Reporter.log("this is  org", true);
		Assert.fail();
	}
}
