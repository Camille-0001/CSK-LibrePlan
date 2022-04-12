package eql.org.csk.LibreplanTest;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Test_Criteres {
	private String usedDriver = "webdriver.gecko.driver";
	private String locateDriver = "C:\\Workspace Git\\Autom_Projet 1\\CSK-LibrePlan\\src\\main\\resources\\driver\\geckodriver.exe";
	private WebDriver driver;
	private String libreplan = "http://localhost:8090/libreplan";
	
	@Before
	public void init() {
		System.setProperty(usedDriver, locateDriver);
		driver = new FirefoxDriver();
		driver.get(libreplan);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void Test_CRI001() throws InterruptedException {
		PageConnexion page_connexion = PageFactory.initElements(driver, PageConnexion.class);
		
		//Accès à la page d'accueil
		PagePlanificationProjets page_planifProjets = page_connexion.signIn(driver);
		//ASSERTION : Vérification que l'on se trouve bien sur la page d'acceuil en confirmant que l'onglet "Calendrier" est affiché.
		page_planifProjets.elementsPresents();
		
		// Accès à la page présentant la liste des participants
		Page_ListeCritere page_listeCrit = page_planifProjets.goToListeCritPage(driver);
		Page_CreateCritere page_createCrit = page_listeCrit.goToCreateCritPage(driver);
		//page_listeCrit = page_createCrit.cancelCreation(driver, "Test bouton [Enregistrer]", "Test bouton [Enregistrer]");
		page_listeCrit = page_createCrit.saveCreation(driver, "Critère - Test bouton [Enregistrer]", "Critère - Test bouton [Enregistrer]");
		page_createCrit = page_listeCrit.goToCreateCritPage(driver);
		page_createCrit.saveCreationAndNext("Critère - Test bouton [Sauver et continuer]", "Critère - Test bouton [Sauver et continuer]");
		page_listeCrit = page_createCrit.goBackToCritList(driver);
		page_createCrit = page_listeCrit.modify_click_icon(driver);
		page_listeCrit = page_createCrit.cancelCreation(driver, "Critère - Test bouton [Sauver et continuer] 2", "");
		page_createCrit = page_listeCrit.modify_click_name(driver);
		page_listeCrit = page_createCrit.goBackToCritList(driver);
		
		page_listeCrit.delete_click_icon_then_cancel();
		page_listeCrit.delete_click_icon_then_ok();
		}
	}
