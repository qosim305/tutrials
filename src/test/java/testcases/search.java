package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class search extends Base{
	WebDriver driver;
	
	@BeforeMethod
public void setup() {
		LoadPropertiesFile();
	driver =  LaunchingApplicationURL("broswer");
}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void VrifySearchWithaValidProduct() {
		driver.findElement(By.name("search")).sendKeys("hp");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	@Test(priority=2)
	public void VerifySearchWithInvalidProducts(){
		
		driver.findElement(By.name("search")).sendKeys("honda");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		String searchErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]")).getText();
		Assert.assertEquals(searchErrorMessage, "There is no product that matches the search criteria.","i didnt get error message");

	}
	@Test(priority=3)
	public void VerifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search"));
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		String searchErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]")).getText();
		Assert.assertEquals(searchErrorMessage, "There is no product that matches the search criteria.","i didnt get error message");

		
	}
}

