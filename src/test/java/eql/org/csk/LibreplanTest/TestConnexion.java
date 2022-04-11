package eql.org.csk.LibreplanTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestConnexion {
	private String nomDriver = "webdriver.gecko.driver";
	private String srcDriver = "src/main/resources/driver/geckodriver.exe";
	private WebDriver driver;
	private String nomSite = "http://localhost:8090/libreplan";
	
	@Before
	public void init() {
		System.setProperty(nomDriver, srcDriver);
		driver = new FirefoxDriver();
		driver.get(nomSite);
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void Test_Conn() throws InterruptedException {
		PageConnexion page_connexion = PageFactory.initElements(driver, PageConnexion.class);
		PagePlanificationProjets page_planification_projets = page_connexion.signIn(driver);
		assertTrue(page_planification_projets.presenceElemFormProj());
		page_planification_projets.creationProjet(driver);
		assertTrue(page_planification_projets.menuVerticalPresent());
		assertTrue(page_planification_projets.menuHorizontalPresent());
		assertTrue(page_planification_projets.menuModifPresent());
		assertTrue(page_planification_projets.fenetreSortie());
		PageListeProjets page_liste_projets = page_planification_projets.afficherListeProjetsMenu(driver);
		assertTrue(page_liste_projets.afficherProjet());
		PageDetailsProjet page_details_projet = page_liste_projets.afficherListeProjets(driver);
		page_details_projet.ajouterTache("Tache1-P1", "5");
		page_details_projet.presenceElemTache();
		page_details_projet.ajouterTache("Tache2-P1", "10");
		page_details_projet.ajouterTache("Tache3-P1", "20");
		page_details_projet.ajouterTache("Tache4-P1", "8");
		page_details_projet.ordreTaches("Tache1-P1", "Tache2-P1", "Tache3-P1", "Tache4-P1");
		page_details_projet.descTache();
		page_details_projet.ordreTaches("Tache2-P1", "Tache1-P1", "Tache3-P1", "Tache4-P1");
		page_details_projet.montTache();
		page_details_projet.ordreTaches("Tache2-P1", "Tache3-P1", "Tache1-P1", "Tache4-P1");
		page_details_projet.remplirTaches();
		assertEquals(page_planification_projets.nombreBarresAvencement(driver),4);
		assertTrue(page_planification_projets.lignesDebutEcheance());
		page_liste_projets.supprimerProjet();
	}
	}
		
	
