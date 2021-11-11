package practice;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClearTripTest {
	@Test
	public void bookTicket()
	{
		Date dateObj=new Date();
		String date=dateObj.toString();
		System.out.println(date);
		String[] today=date.split(" ");

		String day=today[0];
		String month=today[1];
		String actDate=today[2];
		String year=today[5];
		
		String travelDate=day+" "+month+" "+actDate+" "+year;
		//String travelDate=day+", "+month+" "+actDate;
		
		System.out.println(travelDate);
		
		//launch the browser
		 WebDriver driver=new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 
		 //navigate to the application
		 driver.get("https://www.cleartrip.com/");
		 driver.manage().window().maximize();
		 
		 //enter from
		WebElement src= driver.findElement(By.xpath("//h4[.='From']/..//input"));
		src.sendKeys("Bangalore");
		//driver.findElement(By.xpath("//p[.='Bangalore, IN - Kempegowda International Airport (BLR)']")).click();
		
		//enter to
		WebElement dst=driver.findElement(By.xpath("//h4[.='To']/..//input"));
		dst.sendKeys("Mumbai");

		driver.findElement(By.xpath("//h4[.='Depart on']/../../following-sibling::div//button")).click();
		driver.findElement(By.xpath("//div[@aria-label='"+travelDate+"']")).click();
		System.out.println("flight is booked");
	}

}
