package com.crm.comcast.genericutility;

import java.util.Random;
/**
 * 
 * @author Pratheeksha
 *
 */

public class JavaUtility {
	/**
	 * 
	 * @return
	 */

	public int getRandomNum()
	{
		Random random=new Random();
		int randomNum=random.nextInt(100);
		return randomNum;
	}
}
