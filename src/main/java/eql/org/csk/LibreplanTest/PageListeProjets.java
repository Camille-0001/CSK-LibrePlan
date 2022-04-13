package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageListeProjets {

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[1]//span")
	WebElement case_nom;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[2]//span")
	WebElement case_code;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[3]//span")
	WebElement case_debut;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[4]//span")
	WebElement case_echeance;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[5]//span")
	WebElement case_client;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[6]//span")
	WebElement case_budget;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[6]//span")
	WebElement case_heures;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[6]//span")
	WebElement case_etat;

	@FindBy(xpath = "//table/tbody[contains(@class,'z-rows')]/tr['2']/td[6]//span")
	WebElement case_operations;

	@FindBy(xpath = "//td[contains(text(), 'Liste des projets')]")
	WebElement liste_projets;

	@FindBy(xpath = "//li[contains(@class,'seld')]//span[contains(text(),'WBS')]/ancestor::li")
	WebElement wbs_taches_selected;

	@FindBy(xpath = "//span[@title='Supprimer']")
	WebElement bouton_supprimer;

	@FindBy(xpath = "//span[contains(@title,'Annuler')]")
	WebElement fleche_titre;

	@FindBy(xpath = "//td[@class='z-button-cm' and text()='OK']")
	WebElement bouton_ok;

	// afficher les projets assert
	public boolean afficherProjet() {

		String nom_attendu = "PROJET_TEST1";
		String code_attendu = "PRJTEST001";
		String budget_attendu = "0 €";
		String heures_attendu = "0";

		case_nom.getText().equals(nom_attendu);
		case_code.getText().equals(code_attendu);
		case_client.getText().isEmpty();
		case_budget.getText().equals(budget_attendu);
		case_heures.getText().equals(heures_attendu);

		return true;
	}

	// confirmer le titre de la page et l'onglet actif
	public PageDetailsProjet afficherListeProjets(WebDriver driver) {
		String titre_page = "LibrePlan: Calendrier";
		liste_projets.click();
		driver.getTitle().equals(titre_page);
		case_nom.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(wbs_taches_selected));
		String class_onglet_actif = "z-tab z-tab-seld";
		wbs_taches_selected.getAttribute("class").equals(class_onglet_actif);
		return PageFactory.initElements(driver, PageDetailsProjet.class);
	}

	public void supprimerProjet() {
		fleche_titre.click();
		bouton_ok.click();
		liste_projets.click();
		bouton_supprimer.click();
		bouton_ok.click();
	}
}
