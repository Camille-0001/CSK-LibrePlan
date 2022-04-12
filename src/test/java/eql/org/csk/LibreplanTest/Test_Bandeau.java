package eql.org.csk.LibreplanTest;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test_Bandeau {
	
	private String nomDriver = "webdriver.gecko.driver";
	private String srcDriver = "src/main/resources/driver/geckodriver.exe";
	private WebDriver driver;
	private FluentWait<WebDriver> wait;
	private String nomSite = "http://localhost:8090/libreplan";

	static Logger logger = LoggerFactory.getLogger(Test_Bandeau.class);
	
	@Before
	public void init() {
		System.setProperty(nomDriver, srcDriver);
		driver = new FirefoxDriver();
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(3	)).pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void Test_Test() throws Throwable {	
		driver.get(nomSite);
		//connection :
		WebElement utilisateur = driver.findElement(By.xpath("//input[@name='j_username']"));
		WebElement motdepasse = driver.findElement(By.xpath("//input[@name='j_password']"));
		WebElement seconnecter = driver.findElement(By.xpath("//input[@type='submit']"));
		utilisateur.clear();
		utilisateur.sendKeys("admin");
		motdepasse.clear();
		motdepasse.sendKeys("admin");
		seconnecter.click();
		//Page_Connexion page_connexion = PageFactory.initElements(driver, Page_Connexion.class);
		PageGenerique bd = PageFactory.initElements(driver,PageGenerique.class);
		bd = bd.retourPageAccueil(driver,wait);
		bd = bd.RessourcesCalendriers(driver,wait);
		bd = bd.retourPageAccueil(driver,wait);
		bd = bd.RessourcesCriteres(driver,wait);
		bd = bd.retourPageAccueil(driver,wait);
		bd = bd.RessourcesParticipants(driver,wait);
		bd = bd.retourPageAccueil(driver,wait);
		bd = bd.CalendrierProjets(driver,wait);
		bd = bd.retourPageAccueil(driver,wait);
		bd = bd.CoutFeuilleDeTemps(driver,wait);
		bd = bd.retourPageAccueil(driver,wait);
		bd = bd.retourPageAccueil(driver,wait);
		Thread.sleep(2000);
	}
		
	
	}
