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
		//ASSERTION : Vérification de la présence des éléments attendus sur la page d'accueil de création d'un participant
		//assertTrue(page_createPart.elementsPresents());
		
		//Remplissage du 1er formulaire de création d'un participant
		page_createPart.fillFirstForm(driver, "Jean", "DU", "jdu");
		
		//ASSERTION : Vérification de l'affichage du formulaire de création d'un nouvel utilisateur et des éléments associés
		assertTrue(page_createPart.nom_utilisateur.isDisplayed());
	    assertTrue(page_createPart.mot_de_passe.isDisplayed());
	    assertTrue(page_createPart.confirmation_mot_de_passe.isDisplayed());
	    assertTrue(page_createPart.champ_email.isDisplayed());
	    
	    //Remplissage du 2ème formulaire de création d'un participant
		page_listePart = page_createPart.fillSecondForm(driver,"jdu", "$jdumdp1", "$jdumdp1", "jdu@test.fr");
		
		//ASSERTION : Vérification de l'affichage du message "Participant enregistré"
		String message_affiche = page_listePart.message_info.getText();
		String message_attendu = "Participant enregistré";
		assertEquals(message_affiche, message_attendu);
		
		//ASSERTION : le participant Jean DU qui vient d'être créé est présent dans le tableau et ses informations sont conformes à celles qui ont été saisies sur le formulaire de création.
		String nom_affiche = page_listePart.span_DU.getText();
		String nom_attendu = "DU";
		String prenom_affiche = page_listePart.span_Jean.getText();
		String prenom_attendu = "Jean";
		String id_affiche = page_listePart.span_jdu.getText();
		String id_attendu = "jdu";
		assertEquals(nom_attendu, nom_affiche);
		assertEquals(prenom_affiche, prenom_attendu);
		assertEquals(id_affiche, id_attendu);
		
		//Utilisation de la fonction filtre
		page_listePart.useFilter("Jean");
		//ASSERTION : Vérifier que "Jean DU" est affiché dans le tableau des participants filtré
		assertEquals(nom_affiche, nom_attendu);
		assertEquals(prenom_affiche, prenom_attendu);
		assertEquals(id_affiche, id_attendu);
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

		page_listePart.goToLastPage();
		Thread.sleep(2000);
		String last_page = page_listePart.page_number.getAttribute("value");
		String nombre_attendu_derniere_page = "2";
		assertEquals(last_page, nombre_attendu_derniere_page);
		
		page_listePart.goToFirstPage();
		String first_page = page_listePart.page_number.getAttribute("value");
		String nombre_attendu_premiere_page = "1";
		assertEquals(first_page, nombre_attendu_premiere_page);
		
		page_listePart.goToNextPage();
		String next_page = page_listePart.page_number.getAttribute("value");
		String nombre_attendu_page_suivante = "2";
		assertEquals(next_page, nombre_attendu_page_suivante);
		
		page_listePart.goToPrecedentPage();
		String precedent_page = page_listePart.page_number.getAttribute("value");
		String nombre_attendu_page_precedente = "1";
		assertEquals(precedent_page, nombre_attendu_page_precedente);
		
		//Déconnexion du compte admin puis reconnexion avec les identifiants de l'utilisateur nouvellement créé
		page_connexion = page_listePart.logout(driver);
		Page_TableauDeBord page_tableauDeBord = page_connexion.signInUser(driver, "jdu", "$jdumdp1");
		//ASSERTION : Vérifie que l'on se trouve bien sur la page Tableau de Bord
		assertTrue(page_tableauDeBord.elementsPresents());
		
		}
	}
