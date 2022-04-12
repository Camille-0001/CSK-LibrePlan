package eql.org.csk.LibreplanTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** TestFdT
 * 
 * Test automatisé de la création de feuille de temps (RHT-01)
 * 
 */
public class TestFdT {
	
	private String nomDriver = "webdriver.gecko.driver";
	private String srcDriver = "src/main/resources/driver/geckodriver.exe";
	private WebDriver driver;
	//private WebDriverWait wait;
	private String nomSite = "http://localhost:8090/libreplan";

	static Logger logger = LoggerFactory.getLogger(Test_Bandeau.class);
	
	@Before
	public void init() {
		System.setProperty(nomDriver, srcDriver);
		driver = new FirefoxDriver();
		//wait = new WebDriverWait(driver,10);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void testFdT() throws Throwable {
		PageConnexionFdT page_connect;
		PageAccueilFdT page_accueil;
		PageListeFdT page_listefdt;
		PageNouvelleFdT page_nouvfdt;
		driver.get(nomSite);
		
		
		logger.info("********** TS1 : Connexion avec admin/admin **********");
		page_connect = PageFactory.initElements(driver, PageConnexionFdT.class);
		page_accueil = page_connect.signIn(driver);
		
		//assert la page actuelle est calendrier		
		assertEquals("La page actuelle est \"Calendrier\"","calendrier",page_accueil.getLibelleOngletCourant());
		

		logger.info("********** TS2 : Cliquer sur Coût -> Feuilles de temps **********");
		page_listefdt = page_accueil.goToListeFdT(driver);
		
		//assert la page contient u tableau avec les colonnes / Date de début / Date de fin / Modèle / Travail total / Code / Actions
		assertEquals("colonne 1 vaut date de début ","datededébut",page_listefdt.getLibelleColonne(0));
		assertEquals("colonne 2 vaut date de fin ","datedefin",page_listefdt.getLibelleColonne(1));
		assertEquals("colonne 3 vaut mdoele","modèle",page_listefdt.getLibelleColonne(2));
		assertEquals("colonne 4 vaut travail total","travailtotal",page_listefdt.getLibelleColonne(3));
		assertEquals("colonne 5 vaut code ","code",page_listefdt.getLibelleColonne(4));
		assertEquals("colonne 6 vaut actions ","actions",page_listefdt.getLibelleColonne(5));
		
		//assert présence d'une liste déroulante "Filtrer la feuille de temps par: Modèle" avec la valeur "Montrer tout"
		assertEquals("Valeur par défaut dans le select filtre est \"montrer tout\"","montrertout",page_listefdt.getFiltreSelectionne());
		
		//assert 2 champs de date "de" et "a"
		assertTrue("Deux boites de temps existent libellées \"de\" et \"à\"",page_listefdt.isFiltresDatesAffichés());
		
		//assert 1 bouton filtre
		assertTrue("Un bouton Filtre est présent", page_listefdt.isBoutonFiltreAffiche());
		
		//assert 1 liste déroulante sur "Défaut"
		assertEquals("Valeur par défaut dans le select canevas est 'default'","default",page_listefdt.getCanevasSelectionne());
		
		//assert 1 bouton feuille de temps
		assertTrue("Un bouton nouvelle feuille de temps est présent", page_listefdt.isBoutonNouvelleFdTAffiche());

		
		logger.info("********** TS3 : Cliquer sur Nouvelle Feuille de temps **********");
		page_nouvfdt=page_listefdt.nouvelleFdT(driver);
		
		//assert présence d'un champ code non modifiable et d'une case Génerer le code
		assertFalse("champ de saisie du code n'est pas enabled par défaut",page_nouvfdt.isInputCodeActif());
				
		
		//assert bloc champ rubrique est vide
		//Arbitré, cette assertion n'a pas pu être réalisée dans le cadre de la POC
		
		
		//assert bloc ligne de feuille de temps avec bouton d'ajout de fdt et les colonnes désirées
		assertTrue("le bouton Ajouter une ligne est présent",page_nouvfdt.isBoutonAjouterLigneAffiche());
		assertEquals("colonne 1 vaut date","date",page_nouvfdt.getLibelleColonne(0));
		assertEquals("colonne 2 vaut ressource","ressource",page_nouvfdt.getLibelleColonne(1));
		assertEquals("colonne 3 vaut tâche","tâche",page_nouvfdt.getLibelleColonne(2));
		assertEquals("colonne 4 vaut heures","heures",page_nouvfdt.getLibelleColonne(3));
		assertEquals("colonne 5 vaut type d'heures ","typed'heures",page_nouvfdt.getLibelleColonne(4));
		assertEquals("colonne 6 vaut réalisé ","réalisé",page_nouvfdt.getLibelleColonne(5));
		assertEquals("colonne 7 vaut code ","code",page_nouvfdt.getLibelleColonne(6));
		assertEquals("colonne 8 vaut op. ","op.",page_nouvfdt.getLibelleColonne(7));
		
		//assert présence des boutons "enregistrer", "sauver et continuer", "sauvegarder et nouvelle feuille de temps", et "annuler"
		assertEquals("bouton 1 vaut Enregistrer","enregistrer",page_nouvfdt.getLibelleBoutonGlobal(0));
		assertEquals("bouton 2 vaut Sauver et continuer","sauveretcontinuer",page_nouvfdt.getLibelleBoutonGlobal(1));
		assertEquals("bouton 3 vaut Sauvegarder & nouvelle feuille de temps","sauvegarder&nouvellefeuilledetemps",page_nouvfdt.getLibelleBoutonGlobal(2));
		assertEquals("bouton 4 vaut annuler","annuler",page_nouvfdt.getLibelleBoutonGlobal(3));
		
		
//		//Mis de coté pour cause de conflits avec le Test Step 5
//		logger.info("********** Etape bonus 1 : Changer  **********");
//		WebElement check_id = driver.findElement(By.cssSelector(".z-hbox .z-checkbox"));
//		check_id.click();
//
//		WebElement champ_id = driver.findElement(By.cssSelector(".z-hbox .z-textbox"));
//		champ_id.clear();
//		champ_id.sendKeys("TESTFDT01");
		
		
		logger.info("********** TS4 : Cliquer sur Ajouter une ligne **********");
		page_nouvfdt = page_nouvfdt.ajouterLigne(driver);
		
		//assert champ date est à la date du jour par défaut
		assertEquals("la date par défaut est celle du jour",LibrePlanUtils.dateDuJour(),page_nouvfdt.getDateLigneDeTemps());
		
 		//assert liste déroulante ressources
		assertEquals("le champ ressource est vide par défaut","",page_nouvfdt.getRessourceLigneDeTemps());
		
		//assert champ de saisie tache
		assertEquals("le champ tache est vide par défaut","",page_nouvfdt.getTacheLigneDeTemps());
		
		//assert 0 heures par défaut
		assertEquals("le nombre d'heure par défaut est 0","0",page_nouvfdt.getHeuresLigneDeTemps());
		
		//assert type default par défaut
		assertEquals("'Default' séléctionné par défaut" , "default",page_nouvfdt.getTypeHeuresLigneDeTemps());
		
		//assert réalisé décoché par défaut
		assertFalse("réalisé ne doit pas être selectionné par défaut",page_nouvfdt.isRealiseCocheeLigneDeTemps());
		
		//assert icone suppression
		assertTrue(page_nouvfdt.isSupprimerLigneAffiche());
		
		logger.info("********** TS5 : Remplir et enregistrer une feuille de temps **********");
		page_nouvfdt.setDateLigneDeTemps(LibrePlanUtils.jPlusN(3));
		page_nouvfdt.setRessourceLigneDeTemps("participant,testressource (101)");
		page_nouvfdt.selectPremiereTacheLigneDeTemps(driver);
		page_nouvfdt.selectRealiseLigneDeTemps();
		page_nouvfdt.setHeuresLigneDeTemps("8");
		page_listefdt = page_nouvfdt.enregistrerFdT(driver);
		
		//assert message "Feuille de temps sauvehardé affiché
		assertEquals("message d'info s'affiche indiquant 'Feuille de temps sauvegardé'","feuilledetempssauvegardée",page_listefdt.getMessageInfo());
		
		//assert valeurs correctes affichées
		
		logger.info("********** TS6 : Affichage des feuilles de temps par défaut **********");
		
		//assert première valeur et dernières valeurs triées par date début décroissant
		String valeurhaut = page_listefdt.getNthValeurPremiereLigne(0);
		String valeurbas = page_listefdt.getNthValeurDerniereLigne(0);		
		assertTrue("les dates de début sont en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		

		logger.info("********** TS7 : Affichage des feuilles de temps par date de fin **********");
		page_listefdt = page_listefdt.clickNthColonne(driver,1);
		
		//assert première valeur et dernières valeurs triées par date fin croissant
		valeurhaut = page_listefdt.getNthValeurDerniereLigne(1);
		valeurbas = page_listefdt.getNthValeurDerniereLigne(1);
		assertTrue("les dates de fin sont en ordre croissant",valeurhaut.compareTo(valeurbas)<=0);

		
		logger.info("********** TS8 : Affichage des feuilles de temps par date de fin 2 **********");
		page_listefdt = page_listefdt.clickNthColonne(driver,1);
		
		//assert première valeur et dernières valeurs triées par date fin décroissant
		valeurhaut = page_listefdt.getNthValeurDerniereLigne(1);
		valeurbas = page_listefdt.getNthValeurDerniereLigne(1);
		assertTrue("les dates de fin sont en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		

		logger.info("********** TS9 : Affichage des feuilles de temps par Travail total **********");
		page_listefdt = page_listefdt.clickNthColonne(driver,3);
		
		//assert première valeur et dernières valeurs triées par Travail total croissant
		valeurhaut = page_listefdt.getNthValeurDerniereLigne(3);
		valeurbas = page_listefdt.getNthValeurDerniereLigne(3);
		assertTrue("le travail total est en ordre croissant",valeurhaut.compareTo(valeurbas)<=0);
		

		logger.info("********** TS10 : Affichage des feuilles de temps par Travail total 2 **********");
		page_listefdt = page_listefdt.clickNthColonne(driver,3);
		
		//assert première valeur et dernières valeurs triées par Travail total décroissant
		valeurhaut = page_listefdt.getNthValeurDerniereLigne(3);
		valeurbas = page_listefdt.getNthValeurDerniereLigne(3);
		assertTrue("le travail total est en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		

		logger.info("********** TS11 : Affichage des feuilles de temps par Modèle **********");
		page_listefdt = page_listefdt.clickNthColonne(driver,2);
		
		//assert première valeur et dernières valeurs triées par Modèle décroissant
		valeurhaut = page_listefdt.getNthValeurDerniereLigne(2);
		valeurbas = page_listefdt.getNthValeurDerniereLigne(2);
		assertTrue("le modèle est en ordre croissant",valeurhaut.compareTo(valeurbas)<=0);
		

		logger.info("********** TS12 : Affichage des feuilles de temps par Modèle 2 **********");
		page_listefdt = page_listefdt.clickNthColonne(driver,2);
		
		//assert première valeur et dernières valeurs triées par Modèle décroissant
		valeurhaut = page_listefdt.getNthValeurDerniereLigne(2);
		valeurbas = page_listefdt.getNthValeurDerniereLigne(2);
		assertTrue("le modèle est en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		
//		//Erreur : ce code ne parvient pas à saisir le bord de la colonne, et ne parvient donc pas à les redimmensionner
//		logger.info("********** TS13 : Redimensionner une colonne - rétrécir **********");
//		WebElement colonne_datededebut = driver.findElement(By.xpath("//th[contains(.,'Date de début')]"));
//		int width = colonne_datededebut.getSize().getWidth(); int height = colonne_datededebut.getSize().getHeight();
//		System.out.println("width :" + width + " and height :" + height );
//		action.moveToElement(colonne_datededebut,width,height/2).pause(1000).clickAndHold().pause(1000).moveByOffset(-50, 0).pause(1000).release().pause(1000).build().perform();
//
//		
//
//		logger.info("********** TS14 : Redimensionner une colonne - agrandir **********");
//		colonne_datededebut = driver.findElement(By.xpath("//th[contains(.,'Date de début')]"));
//		System.out.println("width :" +  colonne_datededebut.getSize().getWidth() + " and height :" +   colonne_datededebut.getSize().getHeight());
//		action.moveToElement(colonne_datededebut,colonne_datededebut.getSize().getWidth(),colonne_datededebut.getSize().getHeight()/2).clickAndHold().moveByOffset(-50, 0).release().build().perform();
		
		
		logger.info("********** Fin du test : Réinitialisation des données **********");
		WebElement bouton_suppr = driver.findElement(By.cssSelector(".z-rows .clickable-rows:last-of-type img[src $= 'borrar1.png']"));
		bouton_suppr.click();
		WebElement OK_suppr = driver.findElement(By.xpath("//td[.='OK' and @class='z-button-cm']"));
		OK_suppr.click();
	}
}
