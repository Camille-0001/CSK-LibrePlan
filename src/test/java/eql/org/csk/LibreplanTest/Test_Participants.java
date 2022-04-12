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

		//Accès à la page d'accueil temporaire
		PagePlanificationProjets page_planifProjets = page_connexion.signIn(driver);
		//ASSERTION : Vérification que l'on se trouve bien sur la page d'acceuil en confirmant que l'onglet "Calendrier" est affiché.
		page_planifProjets.elementsPresents();
		
		// Accès à la page présentant la liste des participants
		Page_ListeParticipant page_listePart = page_planifProjets.goToListePartPage(driver);
		//ASSERTION : Vérification de la présence des éléments attendus sur la page représentant la liste des participants
		page_listePart.elementsPresents();
		
		// Accès à la page de création d'un participant
		Page_CreateParticipant page_createPart = page_listePart.goToCreatePartPage(driver);
		//ASSERTION : Vérification de la présence des éléments attendus sur la page de création
		//assertTrue(page_createPart.elementsPresents());
		
		//Remplissage du formulaire de création d'un participant + enregistrement
		page_listePart = page_createPart.fillForm(driver);
		
		/*
		//ASSERTION : Vérification de l'affichage du formulaire de création d'un nouvel utilisateur et des éléments associés
		assertTrue(page_createPart.nom_utilisateur.isDisplayed());
	    assertTrue(page_createPart.mot_de_passe.isDisplayed());
	    assertTrue(page_createPart.confirmation_mot_de_passe.isDisplayed());
	    assertTrue(page_createPart.email.isDisplayed());
	    */
	    
		//ASSERTION : Vérification de l'affichage du message "Participant enregistré"
		String message_affiche = page_listePart.message_info.getText();
		String message_attendu = "Participant enregistré";
		assertEquals(message_affiche, message_attendu);
		
		//Utilisation de la fonction filtre
		page_listePart.useFilter();
		//ASSERTION : Vérifier que "Jean DU" est affiché dans le tableau des participants filtré
		
		// 
		page_listePart.useMoreOptions();
		// ASSERTION : Vérifier qu'au clic sur le bouton "Plus d'options", de nouveaux éléments apparaissent
		assertTrue(page_listePart.first_date_field.isDisplayed());
		assertTrue(page_listePart.second_date_field.isDisplayed());
		assertTrue(page_listePart.liste_deroulante.isDisplayed());
		assertTrue(page_listePart.liste_deroulante_1.isDisplayed());
		assertTrue(page_listePart.liste_deroulante_2.isDisplayed());
		assertTrue(page_listePart.liste_deroulante_3.isDisplayed());
		
		//Vérification des boutons de navigation pour changer de page sur la liste des participants
		page_listePart.checkListPages();
		
		//Déconnexion du compte admin puis reconnexion avec les identifiants de l'utilisateur nouvellement créé
		page_connexion = page_listePart.logout(driver);
		page_connexion.signInUser(driver, "", "");
		
		}
	}
