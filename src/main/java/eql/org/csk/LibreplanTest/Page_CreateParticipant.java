package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_CreateParticipant extends LPxPath{
	
	//champ code
	@FindBy (xpath="//input[@class='z-textbox z-textbox-disd z-textbox-text-disd']")
	WebElement champ_code;
	
	@FindBy (xpath="//label[text()='Générer le code']/preceding-sibling::input")
	WebElement generate_code;
	
	//champ prenom
	@FindBy (xpath="//span[@class='z-label' and text() = 'Prénom']/parent::div/parent::td/following-sibling::td/div/input")
	WebElement champ_prenom;
	
	//champ nom
	@FindBy (xpath="//span[@class='z-label' and text() = 'Nom']/parent::div/parent::td/following-sibling::td/div/input[@style='width:500px;']")
	WebElement champ_nom;
	
	//bouton id
	@FindBy (xpath="//span[@class='z-label' and text() = 'ID']/parent::div/parent::td/following-sibling::td/div/input")
	WebElement champ_id;
	
	@FindBy (xpath="//select[@style='width:200px;']")
	WebElement select_type;
	
	@FindBy (xpath="//label[.='Non lié']/preceding-sibling::input")
	WebElement radio_non_lie;
	
	@FindBy (xpath="//label[.='Utilisateur existant']/preceding-sibling::input")
	WebElement radio_utilisateur_existant;
	
	@FindBy (xpath="//label[.='Créer un nouvel utilisateur']/preceding-sibling::input")
	WebElement radio_creer_un_nouvel_utilisateur;
	
	@FindBy (xpath="//label[.='Créer un nouvel utilisateur']/preceding-sibling::input")
	WebElement bouton_enregistrer;
	
	@FindBy (xpath="//label[.='Créer un nouvel utilisateur']/preceding-sibling::input")
	WebElement bouton_sauver_et_continuer;
	
	@FindBy (xpath="//label[.='Créer un nouvel utilisateur']/preceding-sibling::input")
	WebElement bouton_annuler;
	
	//bouton menu_ressources
	/*
	 @FindBy (xpath=MENU_RESSOURCES)
	WebElement menu_ressources;
	*/
	
	//lien participants
	/*
	@FindBy (xpath=MENU_RESSOURCES+"/following::a[position()=1]")
	WebElement lien_createPartForm;
	*/
	
	
	// Fonction permettant d'accéder à la liste des participants depuis la page d'acceuil
	/*
	public Page_CreateParticipant goToCreatePartForm() {
		menu_ressources.click();
		lien_createPartForm.click();
		return PageFactory.initElements(driver.Page_CreateParticipant.class);
	}
	*/
		
	public Page_ListeParticipant fillForm(WebDriver driver) {
		champ_prenom.clear();
		champ_prenom.sendKeys("patate");
		champ_nom.clear();
		champ_nom.sendKeys("Mr");
		champ_id.clear();
		champ_id.sendKeys("mpa");
		bouton_enregistrer.click();
		return PageFactory.initElements(driver, Page_ListeParticipant.class);
	}
	
	public boolean elementsPresents() {
        boolean presence;
        if (champ_code.isDisplayed()
        && generate_code.isDisplayed()
        && champ_prenom.isDisplayed()
        && champ_nom.isDisplayed()
        && champ_id.isDisplayed()
        && select_type.isDisplayed()
        && radio_non_lie.isDisplayed()
        && radio_utilisateur_existant.isDisplayed()
        && radio_creer_un_nouvel_utilisateur.isDisplayed()
        && bouton_enregistrer.isDisplayed()
        && bouton_sauver_et_continuer.isDisplayed()
        && bouton_annuler.isDisplayed()) {
            presence=true;
        }
        else {
            presence=false;
        }
        return presence;
    }
}