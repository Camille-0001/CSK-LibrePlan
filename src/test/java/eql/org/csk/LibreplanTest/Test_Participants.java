package eql.org.csk.LibreplanTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class Test_Participants {
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
	public void Test_GRE001() throws InterruptedException {
		PageConnexion page_connexion = PageFactory.initElements(driver, PageConnexion.class);

		//AccÃ¨s Ã  la page d'accueil temporaire
		PagePlanificationProjets page_planifProjets = page_connexion.signIn(driver);
		//ASSERTION : VÃ©rification que l'on se trouve bien sur la page d'acceuil en confirmant que l'onglet "Calendrier" est affichÃ©.
		page_planifProjets.elementsPresents();
		
		// AccÃ¨s Ã  la page prÃ©sentant la liste des participants
		Page_ListeParticipant page_listePart = page_planifProjets.goToListePartPage(driver);
		//ASSERTION : VÃ©rification de la prÃ©sence des Ã©lÃ©ments attendus sur la page reprÃ©sentant la liste des participants
		page_listePart.elementsPresents();
		
		// AccÃ¨s Ã  la page de crÃ©ation d'un participant
		Page_CreateParticipant page_createPart = page_listePart.goToCreatePartPage(driver);
		//ASSERTION : VÃ©rification de la prÃ©sence des Ã©lÃ©ments attendus sur la page de crÃ©ation
		//assertTrue(page_createPart.elementsPresents());
		
		//Remplissage du formulaire de crÃ©ation d'un participant + enregistrement
		page_listePart = page_createPart.fillForm(driver);
		
		/*
		//ASSERTION : VÃ©rification de l'affichage du formulaire de crÃ©ation d'un nouvel utilisateur et des Ã©lÃ©ments associÃ©s
		assertTrue(page_createPart.nom_utilisateur.isDisplayed());
	    assertTrue(page_createPart.mot_de_passe.isDisplayed());
	    assertTrue(page_createPart.confirmation_mot_de_passe.isDisplayed());
	    assertTrue(page_createPart.email.isDisplayed());
	    */
	    
		//ASSERTION : VÃ©rification de l'affichage du message "Participant enregistrÃ©"
		String message_affiche = page_listePart.message_info.getText();
		String message_attendu = "Participant enregistré";
		assertEquals(message_affiche, message_attendu);
		
		//Utilisation de la fonction filtre
		page_listePart.useFilter();
		//ASSERTION : VÃ©rifier que Jean DU est affichÃ© dans le tableau des participants filtrÃ©
		
		page_listePart.useMoreOptions();
		// ASSERTION : VÃ©rifier qu'au clic sur le bouton "Plus d'options", de nouveaux Ã©lÃ©ments apparaissent
		assertTrue(page_listePart.first_date_field.isDisplayed());
		assertTrue(page_listePart.second_date_field.isDisplayed());
		assertTrue(page_listePart.liste_deroulante.isDisplayed());
		assertTrue(page_listePart.liste_deroulante_1.isDisplayed());
		assertTrue(page_listePart.liste_deroulante_2.isDisplayed());
		assertTrue(page_listePart.liste_deroulante_3.isDisplayed());
		
		page_listePart.checkListPages();

	}	
	
	}
