package com.crm.maven.practice;

import org.testng.annotations.Test;

public class ReadDataTest {

	@Test
	public void readData()
	{
		String URL=System.getProperty("url");
		String BROWSER=System.getProperty("browser");
		System.out.println(URL);
		System.out.println(BROWSER);
	}
}
