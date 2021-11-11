package com.crm.comcast.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Pratheeksha
 *
 */

public class FileUtility {
/**
 * used to get the data from properties file based on key
 * @param key
 * @return the value of the key in the form of String
 * @throws IOException
 */
	public String getPropertyKeyValue(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./data/commonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String value=pObj.getProperty(key);
		return value;
	}

}
