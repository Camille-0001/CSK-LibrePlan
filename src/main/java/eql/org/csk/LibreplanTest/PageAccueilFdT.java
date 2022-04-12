package eql.org.csk.LibreplanTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAccueilFdT {

	//onglet de la page courante
	@FindBy (css =".current-section button")
	private WebElement onglet_courant;

	//bouton du menu coût
	@FindBy (xpath=LPxPath.MENU_COUTS)
	private WebElement bouton_cout;

	//bouton feuille de temps dans le menu coût
	@FindBy (xpath=LPxPath.SOUSMENU_FEUILLEDETEMPS)
	private WebElement bouton_fdt;
	
	public String getLibelleOngletCourant() {
		return onglet_courant.getText().toLowerCase().replace(" ","");
	}
	
	public PageListeFdT goToListeFdT(WebDriver driver) {
		bouton_cout.click();
		if(!bouton_fdt.isDisplayed()) {
			bouton_cout.click();
		}
		bouton_fdt.click();
		return PageFactory.initElements(driver, PageListeFdT.class);
	}
}
