package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	 WebDriver driver;
	 public Properties prop;
	 public Properties dataprop;
	
	public void LoadPropertiesFile() {
		
		 prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\configfile\\config.properties");
		
		dataprop=new Properties();
		File datapropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\Testdata\\testdata.properties");
		
		try {
		FileInputStream datafis = new FileInputStream(datapropFile);
		dataprop.load(datafis);
		}catch(Throwable e) {
		e.printStackTrace();
		}
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public WebDriver LaunchingApplicationURL(String BrowserName) {
		

		//String browserName = "edge";
		
		if(BrowserName.equals("browser")) {
			
			driver = new ChromeDriver();}
		
		else if(BrowserName.equals("edge")) {
			driver = new EdgeDriver();}
		
		else if(BrowserName.equals("firefox")) {
			
			driver = new FirefoxDriver();}
		
		
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(prop.getProperty("url"));
		
return driver;
	}
}

