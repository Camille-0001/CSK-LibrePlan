package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_CreateParticipant extends LPxPath{
	
	//champ code
	//@FindBy (xpath="//input[@class='z-textbox z-textbox-disd z-textbox-text-disd']")
	//WebElement champ_code;
	
	//champ de saisie de code
	//permet de vérifier également que la case "Générer le code" est cochée par défaut
	@FindBy (xpath="//input[@style='width:350px;' and 'disabled']")
	WebElement disabled_champ_code;
	
	//case générer le code
	@FindBy (xpath="//label[text()='Générer le code']/preceding-sibling::input")
	WebElement generate_code;
	
	//champ prenom
	@FindBy (xpath="//span[@class='z-label' and text() = 'Prénom']/parent::div/parent::td/following-sibling::td/div/input")
	WebElement champ_prenom;
	
	//champ nom
	@FindBy (xpath="/html/body/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/fieldset[1]/div/div/div[3]/table/tbody[2]/tr[4]/td[2]/div/input")
	WebElement champ_nom;
	
	//bouton id
	@FindBy (xpath="//span[@class='z-label' and text() = 'ID']/parent::div/parent::td/following-sibling::td/div/input")
	WebElement champ_id;
	
	//liste déroulante "Type" 
	//permet également de vérifier que "Ressource normale" est sélectionnée par défaut dans la liste déroulante
	@FindBy (xpath="//select[@style='width:200px;' and @selectedindex='1']")
	WebElement select_type;
	
	@FindBy (xpath="//label[.='Non lié']/preceding-sibling::input")
	WebElement radio_non_lie;
	
	@FindBy (xpath="//label[.='Utilisateur existant']/preceding-sibling::input")
	WebElement radio_utilisateur_existant;
	
	@FindBy (xpath="//label[.='Créer un nouvel utilisateur']/preceding-sibling::input")
	WebElement radio_creer_un_nouvel_utilisateur;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/fieldset[2]/div/div[2]/div[2]/div/div/div[3]/table/tbody[2]/tr[1]/td[2]/div/input")
	WebElement nom_utilisateur;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/fieldset[2]/div/div[2]/div[2]/div/div/div[3]/table/tbody[2]/tr[2]/td[2]/div/input")
	WebElement mot_de_passe;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/fieldset[2]/div/div[2]/div[2]/div/div/div[3]/table/tbody[2]/tr[3]/td[2]/div/input")
	WebElement confirmation_mot_de_passe;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/fieldset[2]/div/div[2]/div[2]/div/div/div[3]/table/tbody[2]/tr[4]/td[2]/div/input")
	WebElement champ_email;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text() = 'Enregistrer' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_enregistrer;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text() = 'Sauver et continuer' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_sauver_et_continuer;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text() = 'Annuler' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_annuler;
	
	@FindBy (xpath="//span[.='DonnÃ©es personnelles']")
	WebElement onglet_donneesPerso;
	
		
	public void fillFirstForm(WebDriver driver, String prenom, String nom, String id) throws InterruptedException {
		champ_prenom.clear();
		champ_prenom.sendKeys(prenom);
		champ_nom.clear();
		champ_nom.sendKeys(nom);
		champ_id.clear();
		champ_id.sendKeys(id);
		radio_creer_un_nouvel_utilisateur.click();
	}
	
	public Page_ListeParticipant fillSecondForm(WebDriver driver, String username, String password, String conf_pw, String email) throws InterruptedException {	
		nom_utilisateur.sendKeys(username);
		mot_de_passe.sendKeys(password);
		confirmation_mot_de_passe.sendKeys(conf_pw);
		champ_email.sendKeys(email);
		bouton_enregistrer.click();
		return PageFactory.initElements(driver, Page_ListeParticipant.class);
	}
	
	public boolean elementsPresents() {
        boolean presence;
        if (onglet_donneesPerso.isDisplayed()
        && disabled_champ_code.isDisplayed()
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