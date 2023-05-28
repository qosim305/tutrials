package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utilities.Utils;

public class Login extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		LoadPropertiesFile();
		driver = LaunchingApplicationURL(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();

	}

	@Test(priority = 1)
	public void validlogin() {

		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		driver.quit();

	}

	@Test(priority = 2)
	public void invalidlogin() {

		
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidlodin"));
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualwarningmessage = driver.findElement(By.xpath("//div[contains(@class,\"alert-dismissible\")]"))
				.getText();
		// String expectedwarningmessage = " Warning: No match for E-Mail Address and/or
		// Password";

		Assert.assertTrue(actualwarningmessage.contains("Warning: No match for E-Mail Address and/or Password"),
				"warning not displayed");
		driver.quit();
	}

	@Test
	public void loginwithoutcredentials() {

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email"));
		driver.findElement(By.id("input-password"));
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		// String actual =
		// driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
		// String expected = "Warning: No match for E-Mail Address and/or Password";
		String error = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
		Assert.assertEquals(error, "Warning: No match for E-Mail Address and/or Password");

	}

	public void Teardown() {
		driver.quit();
	}

}
