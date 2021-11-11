package practice;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MakeMyTripTest {	
	
	@Test
	public void bookTicket() throws InterruptedException
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
		System.out.println(travelDate);
		
		//launch the browser
		 WebDriver driver=new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 
		 //navigate to the application
		 driver.get("https://www.makemytrip.com/");
		 driver.manage().window().maximize();
		 
		 //enter source 
		 driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		WebElement src= driver.findElement(By.xpath("//span[.='From']"));
		src.click();
		driver.findElement(By.xpath("//div[.='BOM']")).click();
		
		//select destination
		WebElement dst=driver.findElement(By.xpath("//input[@data-cy='toCity']"));
		dst.sendKeys("DEL");
		driver.findElement(By.xpath("//div[.='DEL']")).click();
		
		//select departure date
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		driver.findElement(By.xpath("//div[@aria-label='"+travelDate+"']")).click();
		
		System.out.println("flight booked");
		 
		 
		
	}

}
