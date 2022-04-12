package eql.org.csk.LibreplanTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_CreateCritere {
	
	private String usedDriver = "webdriver.gecko.driver";
	private String locateDriver = "C:\\Workspace Git\\Autom_Projet 1\\CSK-LibrePlan\\src\\main\\resources\\driver\\geckodriver.exe";
	private WebDriver driver;
	private String libreplan = "http://localhost:8090/libreplan";
	
	public void init() {
		System.setProperty(usedDriver, locateDriver);
		driver = new FirefoxDriver();
		driver.get(libreplan);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@FindBy (xpath="//td[@class='z-button-cm' and text() = 'Annuler' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_annuler;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text() = 'Enregistrer' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_enregistrer;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text() = 'Sauver et continuer' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_sauver_et_continuer;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div/div[2]/div/div/div/div[3]/table/tbody[2]/tr[1]/td[2]/div/input")
	WebElement champ_nom;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div/div[2]/div/div/div/div[3]/table/tbody[2]/tr[2]/td[2]/div/i/input")
	WebElement champ_type;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div/div[2]/div/div/div/div[3]/table/tbody[2]/tr[2]/td[2]/div/i/i")
	WebElement select_type;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div/div[2]/div/div/div/div[3]/table/tbody[2]/tr[6]/td[2]/div/textarea")
	WebElement text_area;
	
	@FindBy (xpath="//td[.='Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2']")
	WebElement modify_page;
	
	@FindBy (xpath="//span[.='Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" enregistré']")
	WebElement conf_modification;
	
	@FindBy (xpath="//td[.='Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2']")
	WebElement title_modified_page;
	
	
	public Page_ListeCritere cancelCreation(WebDriver driver, String nom, String description) {
		champ_nom.sendKeys(nom);
		
		//((Select) select_type).selectByVisibleText("PARTICIPANT");
		
		text_area.sendKeys(description);
		bouton_annuler.click();
		return PageFactory.initElements(driver, Page_ListeCritere.class);
	}
	
	public Page_ListeCritere saveCreation(WebDriver driver, String nom, String description) {
		champ_nom.sendKeys(nom);
		text_area.sendKeys(description);
		bouton_enregistrer.click();
		return PageFactory.initElements(driver, Page_ListeCritere.class);
	}
	
	public void saveCreationAndNext(String nom, String description) {
		champ_nom.sendKeys(nom);
		text_area.sendKeys(description);
		bouton_sauver_et_continuer.click();
	}
	
	public Page_ListeCritere goBackToCritList(WebDriver driver) {
		bouton_annuler.click();
		return PageFactory.initElements(driver, Page_ListeCritere.class);
	}
}
