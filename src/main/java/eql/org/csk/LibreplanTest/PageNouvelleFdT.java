package eql.org.csk.LibreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageNouvelleFdT {

	// Bouton enregistrer
	@FindBy(css = ".save-button")
	private WebElement bouton_enregistrer;

	// Bouton Ajouter une ligne
	@FindBy(xpath = "//fieldset//td[@class='z-button-cm' and .='Ajouter une ligne']")
	private WebElement bouton_ajouter_ligne;

	// input du code identifiant d'une FDT
	@FindBy(xpath = "//td[.='Code']/following-sibling::td[1][descendant::span[contains(@class,'z-checkbox') and .='Générer le code']]//input[contains(@class,'z-textbox')]")
	private WebElement input_code;

	// input de la date d'une ligne
	@FindBy(css = ".listWorkReportLines .z-datebox-inp")
	private WebElement champ_date;

	// Input du nombre d'heures d'une ligne
	@FindBy(css = ".listWorkReportLines .z-textbox:not(.z-textbox-disd)")
	private WebElement champ_heures;

	// Input de ressource d'une ligne
	@FindBy(css = ".listWorkReportLines .z-combobox-inp")
	private WebElement champ_ressources;

	// Input de tache d'une ligne
	@FindBy(css = ".listWorkReportLines .z-bandbox-inp")
	private WebElement champ_tache;

	// bouton ouvrant la liste des taches
	@FindBy(css = ".listWorkReportLines .z-bandbox-btn")
	private WebElement bouton_tache;

	// checkbox pour marquer un ligne de temsp réalisée
	@FindBy(css = ".listWorkReportLines .z-checkbox input")
	private WebElement checkb_realise;

	// Select du type d'heure de la ligne
	@FindBy(css = ".listWorkReportLines select")
	private WebElement select_typeheures;

	// Icone supprimer une ligne
	@FindBy(css = ".listWorkReportLines img[src $='ico_borrar1.png']")
	private WebElement icone_supprimer;
	
	

	// libellés des colonnes du formulaire d'ajout de ligne de temps
	@FindBy(css = ".listWorkReportLines .z-grid-header .z-columns .z-column-cnt")
	private List<WebElement> libelles_colonnes;

	// boutons en bas du formulaire
	@FindBy(css = ".global-action .z-button-cm")
	private List<WebElement> boutons_globaux;
	
	
	public PageListeFdT enregistrerFdT(WebDriver driver) {
		bouton_enregistrer.click();
		return PageFactory.initElements(driver, PageListeFdT.class);
	}
	
	public PageNouvelleFdT ajouterLigne(WebDriver driver) {
		bouton_ajouter_ligne.click();
		return PageFactory.initElements(driver, PageNouvelleFdT.class);
	}
	
	
	public void setDateLigneDeTemps(String date) {
		champ_date.clear();
		champ_date.sendKeys(date);
	}
	
	public void setHeuresLigneDeTemps(String heures) {
		champ_heures.clear();
		champ_heures.sendKeys(heures);
	}
	
	public void setRessourceLigneDeTemps(String ressource) {
		champ_ressources.clear();
		champ_ressources.sendKeys(ressource);
	}
	
	public void selectPremiereTacheLigneDeTemps(WebDriver driver) {
		bouton_tache.click();
		bouton_tache.click();
		WebElement premiere_tache = driver.findElements(By.cssSelector(".z-bandbox-shadow .z-listitem")).get(0);
		Actions action = new Actions(driver);
		action.moveToElement(premiere_tache).click().build().perform();
	}
	
	public void selectRealiseLigneDeTemps() {
		if(!checkb_realise.isSelected()) {
			checkb_realise.click();
		}
	}
	
	
	public String getDateLigneDeTemps() {
		return LibrePlanUtils.conversion_date(champ_date.getAttribute("value"));
	}
	
	public String getHeuresLigneDeTemps() {
		return champ_heures.getAttribute("value");
	}
	
	public String getRessourceLigneDeTemps() {
		return champ_ressources.getAttribute("value");
	}
	
	public String getTacheLigneDeTemps() {
		return champ_tache.getAttribute("value");
	}
	
	public String getTypeHeuresLigneDeTemps() {
		Select typeheures = new Select(select_typeheures);
		return typeheures.getFirstSelectedOption().getText().toLowerCase().replace(" ", "");
	}
	
	public boolean isRealiseCocheeLigneDeTemps() {
		return checkb_realise.isSelected();
	}

	public boolean isSupprimerLigneAffiche(){
		return icone_supprimer.isDisplayed();
	}
	
	public boolean isInputCodeActif() {
		return input_code.isEnabled();
	}

	public String getLibelleColonne(int n) {
		return libelles_colonnes.get(n).getText().toLowerCase().replace(" ", "");
	}

	public String getLibelleBoutonGlobal(int n) {
		return boutons_globaux.get(n).getText().toLowerCase().replace(" ", "");
	}

	public boolean isBoutonAjouterLigneAffiche() {
		return bouton_ajouter_ligne.isDisplayed();
	}
	
	
}
