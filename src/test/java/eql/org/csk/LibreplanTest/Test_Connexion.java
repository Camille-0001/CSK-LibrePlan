package eql.org.csk.LibreplanTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Test_Connexion {
	private String nomDriver = "webdriver.gecko.driver";
	private String srcDriver = "C:\\Users\\Formation\\eclipse-workspace\\wordpress_project\\src\\main\\resources\\driver\\geckodriver.exe";
	private WebDriver driver;
	private String nomSite = "http://localhost:8080/libreplan";
	
	@Before
	public void init() {
		System.setProperty(nomDriver, srcDriver);
		driver = new FirefoxDriver();
		driver.get(nomSite);
		driver.manage().window().maximize();
	}
	
	@Test
	public void Test_Conn() {
		Page_Connexion page_connexion = PageFactory.initElements(driver, Page_Connexion.class);
		page_connexion.signIn(driver);
	}
		
	
	}
