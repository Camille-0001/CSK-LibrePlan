package eql.org.csk.LibreplanTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	private WebDriverWait wait;
	private String nomSite = "http://localhost:8090/libreplan";

	static Logger logger = LoggerFactory.getLogger(Test_Bandeau.class);
	
	@Before
	public void init() {
		System.setProperty(nomDriver, srcDriver);
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,10);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void testFdT() throws Throwable {
		Actions action = new Actions(driver);
		driver.get(nomSite);
		
		logger.info("********** TS1 : Connexion avec admin/admin **********");
		WebElement utilisateur = driver.findElement(By.xpath("//input[@name='j_username']"));
		WebElement motdepasse = driver.findElement(By.xpath("//input[@name='j_password']"));
		WebElement seconnecter = driver.findElement(By.xpath("//input[@type='submit']"));
		utilisateur.clear();
		utilisateur.sendKeys("admin");
		motdepasse.clear();
		motdepasse.sendKeys("admin");
		seconnecter.click();
		
		//assert la page actuelle est calendrier		
		WebElement page_actuelle = driver.findElement(By.cssSelector(".current-section button"));
		assertEquals("La page actuelle est \"Calendrier\"","calendrier",page_actuelle.getText().toLowerCase().replace(" ",""));
		

		logger.info("********** TS2 : Cliquer sur Coût -> Feuilles de temps **********");
		WebElement menu_cout = driver.findElement(By.xpath(LPxPath.MENU_COUTS));
		menu_cout.click();
		menu_cout.click();
		WebElement sousmenu_fdt = driver.findElement(By.xpath(LPxPath.SOUSMENU_FEUILLEDETEMPS));
		sousmenu_fdt.click();
		
		//assert la page contient u tableau avec les colonnes / Date de début / Date de fin / Modèle / Travail total / Code / Actions
		List<WebElement> libelles_colonnes = driver.findElements(By.cssSelector(".clickable-rows .z-column-cnt"));
		assertEquals("colonne 1 vaut date de début ","datededébut",libelles_colonnes.get(0).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 2 vaut date de fin ","datedefin",libelles_colonnes.get(1).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 3 vaut mdoele","modèle",libelles_colonnes.get(2).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 4 vaut travail total","travailtotal",libelles_colonnes.get(3).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 5 vaut code ","code",libelles_colonnes.get(4).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 6 vaut actions ","actions",libelles_colonnes.get(5).getText().toLowerCase().replace(" ",""));
		
		//assert présence d'une liste déroulante "Filtrer la feuille de temps par: Modèle" avec la valeur "Montrer tout"
		WebElement we_select_filtre = driver.findElement(By.xpath("//td[contains(.,'Filtrer la feuille de temps par')]/following-sibling::td[contains(.,'Modèle')]/following-sibling::td/select"));
		Select select_filtre = new Select(we_select_filtre);
		assertEquals("Valeurr par défaut dans le select filtre est \"montrer tout\"","montrertout",select_filtre.getFirstSelectedOption().getText().toLowerCase().replace(" ",""));
		
		//assert 2 champs de date "de" et "a"
		WebElement dateboxes = driver.findElement(By.xpath("//td[contains(.,'de')]/following-sibling::td[child::i[@class='z-datebox']]/following-sibling::td[contains(.,'à')]/following-sibling::td[child::i[@class='z-datebox']]"));
		assertTrue("Deux boites de temps existent libellées \"de\" et \"à\"",dateboxes.isDisplayed());
		
		//assert 1 bouton filtre
		WebElement bouton_filtrer = driver.findElement(By.xpath("//td[@class='z-button-cm' and .='Filtre']"));
		assertTrue("Un bouton Filtre est présent", bouton_filtrer.isDisplayed());
		
		//assert 1 liste déroulante sur "Défaut"
		WebElement we_select_canevas = driver.findElement(By.xpath("//td[contains(.,'Choisir un canevas')]/following-sibling::td/select"));
		Select select_canevas = new Select(we_select_canevas);
		assertEquals("Valeur par défaut dans le select canevas est 'default'","default",select_canevas.getFirstSelectedOption().getText().toLowerCase().replace(" ",""));
		
		//assert 1 bouton feuille de temps
		WebElement bouton_nouvelle = driver.findElement(By.xpath("//td[@class='z-button-cm' and .='Nouvelle feuille de temps']"));
		assertTrue("Un bouton nouvelle feuille de temps est présent", bouton_nouvelle.isDisplayed());

		
		logger.info("********** TS3 : Cliquer sur Nouvelle Feuille de temps **********");
		WebElement nouvelle_fdt = driver.findElement(By.xpath("//span[contains(.,'Nouvelle feuille de temps')][not(ancestor::div[contains(@style,'display:none')])]"));
		nouvelle_fdt.click();
		
		//assert présence d'un champ code non modifiable et d'une case Génerer le code
		WebElement saisie_code = driver.findElement(By.xpath("//td[.='Code']/following-sibling::td[1][descendant::span[contains(@class,'z-checkbox') and .='Générer le code']]//input[contains(@class,'z-textbox')]"));
		assertTrue("champ de saisie du code n'est pas enabled par défaut",!saisie_code.isEnabled());
				
		
		//assert bloc champ rubrique est vide
		//Arbitré, cette assertion n'a pas pu être réalisée dans le cadre de la POC
		
		
		//assert bloc ligne de feuille de temps avec bouton d'ajout de fdt et les colonnes du TS2
		WebElement bouton_ajoute_ligne = driver.findElement(By.xpath("//fieldset//td[@class='z-button-cm' and .='Ajouter une ligne']"));
		List<WebElement> colonnes_lignes = driver.findElements(By.cssSelector(".listWorkReportLines .z-grid-header .z-columns .z-column-cnt"));
		assertTrue("",bouton_ajoute_ligne.isEnabled());

		assertEquals("colonne 1 vaut date","date",colonnes_lignes.get(0).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 2 vaut ressource","ressource",colonnes_lignes.get(1).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 3 vaut tâche","tâche",colonnes_lignes.get(2).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 4 vaut heures","heures",colonnes_lignes.get(3).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 5 vaut type d'heures ","typed'heures",colonnes_lignes.get(4).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 6 vaut réalisé ","réalisé",colonnes_lignes.get(5).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 7 vaut code ","code",colonnes_lignes.get(6).getText().toLowerCase().replace(" ",""));
		assertEquals("colonne 8 vaut op. ","op.",colonnes_lignes.get(7).getText().toLowerCase().replace(" ",""));
		
		//assert présence des boutons "enregistrer", "sauver et continuer", "sauvegarder et nouvelle feuille de temps", et "annuler"
		List<WebElement> boutons_globaux = driver.findElements(By.cssSelector(".global-action .z-button-cm"));
		
		assertEquals("bouton 1 vaut Enregistrer","enregistrer",boutons_globaux.get(0).getText().toLowerCase().replace(" ",""));
		assertEquals("bouton 2 vaut Sauver et continuer","sauveretcontinuer",boutons_globaux.get(1).getText().toLowerCase().replace(" ",""));
		assertEquals("bouton 3 vaut Sauvegarder & nouvelle feuille de temps","sauvegarder&nouvellefeuilledetemps",boutons_globaux.get(2).getText().toLowerCase().replace(" ",""));
		assertEquals("bouton 4 vaut annuler","annuler",boutons_globaux.get(3).getText().toLowerCase().replace(" ",""));
		
		
//		//Mis de coté pour cause de conflits avec le Test Step 5
//		logger.info("********** Etape bonus 1 : Changer  **********");
//		WebElement check_id = driver.findElement(By.cssSelector(".z-hbox .z-checkbox"));
//		check_id.click();
//
//		WebElement champ_id = driver.findElement(By.cssSelector(".z-hbox .z-textbox"));
//		champ_id.clear();
//		champ_id.sendKeys("TESTFDT01");
		
		
		logger.info("********** TS4 : Cliquer sur Ajouter une ligne **********");
		WebElement ajouter_ligne = driver.findElement(By.xpath("//span[contains(.,'Ajouter une ligne')][not(ancestor::div[contains(@style,'display:none')])]"));
		ajouter_ligne.click();
		
		//assert champ date
		WebElement champ_date = driver.findElement(By.cssSelector(".listWorkReportLines .z-datebox-inp"));
		String date = conversion_date(champ_date.getAttribute("value"));
		assertEquals("la date par défaut est celle du jour",dateDuJour(),date);
		
 		//assert liste déroulante ressources
		//Arbitré, cette assertion n'a pas pu être réalisée dans le cadre de la POC
		
		//assert champ de saisie tache
		//Arbitré, cette assertion n'a pas pu être réalisée dans le cadre de la POC
		
		//assert 0 heures par défaut
		WebElement champ_heure = driver.findElement(By.cssSelector(".listWorkReportLines .z-textbox:not(.z-textbox-disd)"));
		assertEquals("le nombre d'heure par défaut est 0","0",champ_heure.getAttribute("value"));
		
		//assert type default par défaut
		Select select_typeheure = new Select (driver.findElement(By.cssSelector(".listWorkReportLines select")));
		assertEquals("'Default' séléctionné par défaut" , "default",select_typeheure.getFirstSelectedOption().getText().toLowerCase().replace(" ",""));
		
		//assert réalisé décoché par défaut
		//Arbitré, cette assertion n'a pas pu être réalisée dans le cadre de la POC
		
		//assert icone suppression
		WebElement icone_suppression = driver.findElement(By.cssSelector(".listWorkReportLines img[src $='ico_borrar1.png']"));
		assertTrue(icone_suppression.isDisplayed());
		
		logger.info("********** TS5 : Remplir et enregistrer une feuille de temps **********");
		WebElement champ_calendrier = driver.findElement(By.cssSelector(".listWorkReportLines .z-datebox-inp"));
		champ_calendrier.clear();
		champ_calendrier.sendKeys("15/04/2022");
		WebElement champ_ressource = driver.findElement(By.cssSelector(".listWorkReportLines .z-combobox-inp"));
		champ_ressource.clear();
		champ_ressource.sendKeys("participant,testressource (101)");
		WebElement bouton_tache = driver.findElement(By.cssSelector(".listWorkReportLines .z-bandbox-btn"));
		bouton_tache.click();
		bouton_tache.click();
		WebElement premiere_tache = driver.findElements(By.cssSelector(".z-bandbox-shadow .z-listitem")).get(0);
		action.moveToElement(premiere_tache).click().build().perform();
		//premiere_tache.click();
		WebElement checkbox_realise = driver.findElement(By.cssSelector(".listWorkReportLines .z-checkbox input"));
		checkbox_realise.click();
		WebElement champ_horaire = driver.findElement(By.cssSelector(".listWorkReportLines .z-textbox:not(.z-textbox-disd)"));
		champ_horaire.clear();
		champ_horaire.sendKeys("8");
		WebElement enregistrer = driver.findElement(By.cssSelector(".save-button"));
		enregistrer.click();
		
		//assert message "Feuille de temps sauvehardé affiché
		WebElement message_info = driver.findElement(By.cssSelector(".message_INFO"));
		assertEquals("message d'info s'affiche indiquant 'Feuille de temps sauvegardé'","feuilledetempssauvegardée",message_info.getText().toLowerCase().replace(" ",""));
		
		//assert valeurs correctes affichées
		
		logger.info("********** TS6 : Affichage des feuilles de temps par défaut **********");
		
		//assert première valeur et dernières valeurs triées par date début décroissant
		List<WebElement> premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		List<WebElement> derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		String valeurhaut = conversion_date(premiere_ligne.get(0).getText());
		String valeurbas = conversion_date(derniere_ligne.get(0).getText());		
		assertTrue("les dates de début sont en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		

		logger.info("********** TS7 : Affichage des feuilles de temps par date de fin **********");
		WebElement colonne_datedefin = driver.findElement(By.xpath("//th[contains(.,'Date de fin')]"));
		colonne_datedefin.click();
		
		//assert première valeur et dernières valeurs triées par date fin croissant
		premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		valeurhaut = conversion_date(premiere_ligne.get(1).getText());
		valeurbas = conversion_date(derniere_ligne.get(1).getText());
		assertTrue("les dates de fin sont en ordre croissant",valeurhaut.compareTo(valeurbas)<=0);

		
		logger.info("********** TS8 : Affichage des feuilles de temps par date de fin 2 **********");
		colonne_datedefin = driver.findElement(By.xpath("//th[contains(.,'Date de fin')]"));
		colonne_datedefin.click();
		
		//assert première valeur et dernières valeurs triées par date fin décroissant
		premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		valeurhaut = conversion_date(premiere_ligne.get(1).getText());
		valeurbas = conversion_date(derniere_ligne.get(1).getText());
		assertTrue("les dates de fin sont en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		

		logger.info("********** TS9 : Affichage des feuilles de temps par Travail total **********");
		WebElement colonne_travailtotal = driver.findElement(By.xpath("//th[contains(.,'Travail total')]"));
		colonne_travailtotal.click();
		
		//assert première valeur et dernières valeurs triées par Travail total croissant
		premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		valeurhaut = premiere_ligne.get(3).getText();
		valeurbas = derniere_ligne.get(3).getText();
		assertTrue("le travail total est en ordre croissant",valeurhaut.compareTo(valeurbas)<=0);
		

		logger.info("********** TS10 : Affichage des feuilles de temps par Travail total 2 **********");
		colonne_travailtotal = driver.findElement(By.xpath("//th[contains(.,'Travail total')]"));
		colonne_travailtotal.click();
		
		//assert première valeur et dernières valeurs triées par Travail total décroissant
		premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		valeurhaut = premiere_ligne.get(3).getText();
		valeurbas = derniere_ligne.get(3).getText();
		assertTrue("le travail total est en ordre décroissant",valeurhaut.compareTo(valeurbas)>=0);
		

		logger.info("********** TS11 : Affichage des feuilles de temps par Modèle **********");
		WebElement colonne_modele = driver.findElement(By.xpath("//th[contains(.,'Modèle')]"));
		colonne_modele.click();
		
		//assert première valeur et dernières valeurs triées par Modèle décroissant
		premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		valeurhaut = premiere_ligne.get(2).getText();
		valeurbas = derniere_ligne.get(2).getText();
		assertTrue("le modèle est en ordre croissant",valeurhaut.compareTo(valeurbas)<=0);
		

		logger.info("********** TS12 : Affichage des feuilles de temps par Modèle 2 **********");
		colonne_modele = driver.findElement(By.xpath("//th[contains(.,'Modèle')]"));
		colonne_modele.click();
		
		//assert première valeur et dernières valeurs triées par Modèle décroissant
		premiere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:first-of-type .z-label"));
		derniere_ligne = driver.findElements(By.cssSelector(".z-rows .clickable-rows:last-of-type .z-label"));
		valeurhaut = premiere_ligne.get(2).getText();
		valeurbas = derniere_ligne.get(2).getText();
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
		Thread.sleep(2000);
	}
	
	private String conversion_date(String date_origine) {
		String[] elements_date = date_origine.split(" ");
		String date_convertie ="";
		if (elements_date[0].length()==1) {
			date_convertie = "0";
		}
		date_convertie =date_convertie + elements_date[0]+"/";
		switch(elements_date[1]) {
		case "janv." : date_convertie = date_convertie + "01";break;
		case "févr." : date_convertie = date_convertie + "02";break;
		case "mars" : date_convertie = date_convertie + "03";break;
		case "avr." : date_convertie = date_convertie + "04";break;
		case "mai" : date_convertie = date_convertie + "05";break;
		case "juin" : date_convertie = date_convertie + "06";break;
		case "juil." : date_convertie = date_convertie + "07";break;
		case "août" : date_convertie = date_convertie + "08";break;
		case "sept." : date_convertie = date_convertie + "09";break;
		case "oct." : date_convertie = date_convertie + "10";break;
		case "nov." : date_convertie = date_convertie + "11";break;
		case "déc." : date_convertie = date_convertie + "12S";break;
		}
		date_convertie = date_convertie +"/" + elements_date[2];
		return date_convertie;
	}
	
	private  String dateDuJour() {
        DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date dateAjd = new Date(); //ajd
        return formatDate.format(dateAjd);
       
    }
}
