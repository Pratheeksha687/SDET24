package practice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.FileUtility;

public class VtigerWebTableTest {
	
	
	@Test
	public void vTiger() throws IOException
	{
		FileUtility fLib=new FileUtility();
		WebDriver driver=null;
	//get test data
	String BROWSER=fLib.getPropertyKeyValue("browser");
	String URL=fLib.getPropertyKeyValue("url");
	String USERNAME=fLib.getPropertyKeyValue("username");
	String PASSWORD=fLib.getPropertyKeyValue("password");
	
	//launch the browser


	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver=new ChromeDriver();
	}else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
		
		
		
	}else if(BROWSER.equalsIgnoreCase("InternetExplorer"))

	{
		driver=new InternetExplorerDriver();
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	//navigate to URL
	driver.get(URL);
	
	//login to application
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	//navigate to organisation link
   driver.findElement(By.xpath("//a[.='Organizations']")).click();

   //select table 1st column and click on all check box
List<WebElement> list = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input"));
	for(int i=1;i<list.size();i++)
	{
		list.get(i).click();
	}

	//deselect last column
	//list.get(list.size()-1).click();
	
	//print orgnisation name
	/*List<WebElement> orgNameList = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
	for(WebElement ele:orgNameList)
	{
		String text=ele.getText();
		System.out.println(text);
	}*/
	
	//select 5th column
	  WebElement element = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[7]/td[1]/input"));
	  element.click();
}
}
	
