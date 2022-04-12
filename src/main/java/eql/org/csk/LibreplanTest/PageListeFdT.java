package eql.org.csk.LibreplanTest;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageListeFdT {


	//Bouton Nouvelle Feuille de temps
	@FindBy (xpath="//td[@class='z-button-cm' and .='Nouvelle feuille de temps']")
	private WebElement bouton_nouvelle_fdt ;
	
	//libellés des colonnes de la liste de FdT
	@FindBy (css =".clickable-rows .z-column-cnt")
	private List<WebElement> libelles_colonnes;

	//Select du filtre appliqué aux feuilles de temps
	@FindBy (xpath="//td[contains(.,'Filtrer la feuille de temps par')]/following-sibling::td[contains(.,'Modèle')]/following-sibling::td/select")
	private WebElement select_filtre ;

	//filtre par date "de"
	@FindBy (xpath="//td[contains(.,'de')]/following-sibling::td[following-sibling::td[contains(.,'à')]]/i/input")
	private WebElement filtre_date_de ;

	//filtre par date "à"
	@FindBy (xpath="//td[contains(.,'à')]/following-sibling::td[child::i[@class='z-datebox']]/i/input")
	private WebElement filtre_date_a ;

	//bouton appliquant les filtres
	@FindBy (xpath="//td[@class='z-button-cm' and .='Filtre']")
	private WebElement bouton_filtrer ;

	//select du canevas
	@FindBy (xpath="//td[contains(.,'Choisir un canevas')]/following-sibling::td/select")
	private WebElement select_canevas ;

	//filtre par date "à"
	@FindBy (css=".message_INFO")
	private WebElement message_info ;
	
	//liste des valeurs de la première FdT de la liste
	@FindBy (css =".z-rows .clickable-rows:first-of-type .z-label")
	private List<WebElement> premiere_ligne;

	//liste des valeurs de la dernière FdT de la liste
	@FindBy (css =".z-rows .clickable-rows:last-of-type .z-label")
	private List<WebElement> derniere_ligne;
	
	
	public PageNouvelleFdT nouvelleFdT(WebDriver driver) {
		bouton_nouvelle_fdt.click();
		return PageFactory.initElements(driver, PageNouvelleFdT.class);
	}
	
	
	public PageListeFdT clickNthColonne(WebDriver driver,int n) {
		libelles_colonnes.get(n).click();
		return PageFactory.initElements(driver, PageListeFdT.class);
	}
	
	
	
	public String getLibelleColonne(int n) {
		return libelles_colonnes.get(n).getText().toLowerCase().replace(" ", "");
	}
	
	public String getFiltreSelectionne() {
		Select filtres = new Select(select_filtre);
		return filtres.getFirstSelectedOption().getText().toLowerCase().replace(" ", "");
	}
	
	public boolean isFiltresDatesAffichés() {
		return filtre_date_a.isDisplayed() && filtre_date_de.isDisplayed();
	}
	
	public boolean isBoutonFiltreAffiche() {
		return bouton_filtrer.isDisplayed();
	}
	
	public String getCanevasSelectionne() {
		Select canevas = new Select(select_canevas);
		return canevas.getFirstSelectedOption().getText().toLowerCase().replace(" ", "");
	}
	
	public boolean isBoutonNouvelleFdTAffiche() {
		return bouton_nouvelle_fdt.isDisplayed();
	}
	
	public String getMessageInfo() {
		return message_info.getText().toLowerCase().replace(" ", "");
	}
	
	public String getNthValeurPremiereLigne(int n) {
		if(n<2){
			return LibrePlanUtils.conversion_date(premiere_ligne.get(n).getText());
		}
		return premiere_ligne.get(n).getText();
	}
	
	public String getNthValeurDerniereLigne(int n) {
		if(n<2){
			return LibrePlanUtils.conversion_date(derniere_ligne.get(n).getText());
		}
		return derniere_ligne.get(n).getText();
	}
	
	
	

}


