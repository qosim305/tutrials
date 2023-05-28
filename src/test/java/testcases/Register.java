package testcases;

import java.sql.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utilities.Utils;

public class Register extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		LoadPropertiesFile();
		driver = LaunchingApplicationURL("browser");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyregisteringwithmandatoryfields() {
		driver.findElement(By.id("input-firstname")).sendKeys("kaz");
		driver.findElement(By.id("input-lastname")).sendKeys("Aweda");
		driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualsuccess = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertEquals(actualsuccess, "Your Account Has Been Created!", "Account not created");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByprovidingAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("kaz");
		driver.findElement(By.id("input-lastname")).sendKeys("Aweda");
		driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualsuccess = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();

		Assert.assertEquals(actualsuccess, "Your Account Has Been Created!", "Account success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingemailAccount() {
		driver.findElement(By.id("input-firstname")).sendKeys("kaz");
		driver.findElement(By.id("input-lastname")).sendKeys("Aweda");
		driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualerror = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText();
		Assert.assertTrue(actualerror.contains("Warning: E-Mail Address is already registered!"),
				"Warning message is not displayed");

	}

	@Test(priority = 4)
	public void VerifyRegisteringAccountWithoutFillingAnyDetails() {
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualprivacy = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText();
		Assert.assertTrue(actualprivacy.contains("Warning: You must agree to the Privacy Policy!"),
				"privacy policy warning is displayed due unfilled fields");

		String actualFirstNameError = driver.findElement(By.xpath("//*[@id=\"account\"]/div[2]/div/div")).getText();
		Assert.assertEquals(actualFirstNameError, "First Name must be between 1 and 32 characters!",
				"Error message is not displayed");

		String actualLastNameError = driver.findElement(By.xpath("//*[@id=\"account\"]/div[3]/div/div")).getText();
		Assert.assertEquals(actualLastNameError, "Last Name must be between 1 and 32 characters!",
				"Error message is not displayed");

		String actualEmailError = driver.findElement(By.xpath("//*[@id=\"account\"]/div[4]/div/div")).getText();
		Assert.assertEquals(actualEmailError, "E-Mail Address does not appear to be valid!",
				"Error message is not displayed");

		String actualTelError = driver.findElement(By.xpath("//*[@id=\"account\"]/div[5]/div/div")).getText();
		Assert.assertEquals(actualTelError, "Telephone must be between 3 and 32 characters!",
				"Error message is not displayed");
	}
	
	



}
