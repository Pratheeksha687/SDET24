package practice;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseClass;

import junit.framework.Assert;

@Listeners(com.crm.comcast.genericutility.ListnerImplementaion.class)
public class ListnerTest extends BaseClass{
		@Test
		public void ListnerTest()
		{
			System.out.println("test script failed");
			Assert.fail();
		}
		
	}


