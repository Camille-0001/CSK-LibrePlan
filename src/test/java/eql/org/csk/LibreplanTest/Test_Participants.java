package eql.org.csk.LibreplanTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Test_Participants {
	private String usedDriver = "webdriver.gecko.driver";
	private String locateDriver = "C:\\Workspace Git\\CSK-LibrePlan2\\CSK-LibrePlan\\src\\main\\resources\\driver\\geckodriver.exe";
	private WebDriver driver;
	private String libreplan = "http://localhost:8090/libreplan";
	
	@Before
	public void init() {
		System.setProperty(usedDriver, locateDriver);
		driver = new FirefoxDriver();
		driver.get(libreplan);
		driver.manage().window().maximize();
	}
	
	@Test
	public void Test_GRE001() {
		Page_Connexion page_connexion = PageFactory.initElements(driver, Page_Connexion.class);
		page_connexion.signIn(driver);
		
		// Instanciation page d'acceuil (nom temporaire pour la fonction 'liste_part_access')
		/*
		Page_Planification_Projets page_plan_proj = PageFactory.initElements(driver, Page_Planification_Projets.class);
		page_plan_proj.liste_part_access();
		*/
		
		// Accès à la page de création d'un participant (nom temporaire pour la fonction 'create_part_access'
		Page_ListeParticipant page_listePart = PageFactory.initElements(driver, Page_ListeParticipant.class);
		page_listePart.create_part_access(driver);
		
		Page_CreateParticipant page_createPart = PageFactory.initElements(driver, Page_CreateParticipant.class);
		//ASSERTION : Vérification de la présence des éléments attendus sur la page de création
		assertTrue(page_createPart.elementsPresents());
		
		//Remplissage du formulaire de création d'un participant + enregistrement
		page_listePart = page_createPart.fillForm(driver);
		
		//ASSERTION : Vérification de l'affichage du message "Participant enregistré"
		String message_affiche = page_listePart.message_info.getText();
		String message_attendu = "Participant enregistré";
		assertEquals(message_affiche, message_attendu);
		
		//span[.='test']
	}
		
	
	}
