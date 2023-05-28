package pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homepage {

	@FindBy(xpath="//span[text()=\"My Account\"]")
	WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	WebElement loginoption;
	
}
