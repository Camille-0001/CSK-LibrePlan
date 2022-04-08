package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Page_Connexion {
	
		
	
	//champ username
	@FindBy (xpath="//input[@name='j_username']")
	WebElement champ_username;
	
	//champ password
	@FindBy (xpath="//input[@name='j_password']")
	WebElement champ_password;
	
	//bouton submit
	@FindBy (xpath="//input[@type='submit']")
	WebElement bouton_submit;
	
	public void signIn(WebDriver driver) {
		champ_username.clear();
		champ_username.sendKeys("admin");
		champ_password.clear();
		champ_password.sendKeys("admin");
		bouton_submit.click();
		bouton_submit.click();
		//return PageFactory.initElements(driver, PageAccueil.class);
	}
}
