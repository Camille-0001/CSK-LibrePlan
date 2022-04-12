package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_ListeParticipant extends LPxPath{
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td/div/span")
	//div[@class='message_INFO']/span
	WebElement message_info;
	
	//bouton déconnexion
	@FindBy (xpath="//a[.='[Déconnexion]']")
	WebElement bouton_deconnexion;
	
	@FindBy(xpath = "//td[contains(@class,'z-button-cm') and text() = 'Créer' and not(ancestor::div[contains(@style,'display:none')])]") 
	WebElement createPart_Button;
	
	@FindBy(xpath = "//div[.='Surnom']")
	WebElement colonne_surnom;
	
	@FindBy(xpath = "//div[.='Prénom']")
	WebElement colonne_prenom;
	
	@FindBy(xpath = "//div[.='ID']")
	WebElement colonne_id;
	
	@FindBy(xpath = "//div[.='Code']")
	WebElement colonne_code;
	
	@FindBy(xpath = "//div[.='En file']")
	WebElement colonne_enFile;
	
	@FindBy(xpath = "//div[.='Opérations']")
	WebElement colonne_operations;
	
	@FindBy(xpath = "//input[@class='z-bandbox-inp' and @style='width: 262px;']")
	WebElement filter_field;
	
	@FindBy(xpath = "//input[@class='z-bandbox-inp' and @style='width: 262px;']/following-sibling::i")
	WebElement filter_lglass;
	
	@FindBy(xpath = "//input[@class='z-textbox' and @style='width:200px;']")
	WebElement personnal_details;
	
	@FindBy(xpath = "//td[contains(text(), 'options')]")
	WebElement button_moreOptions;
	
	@FindBy(xpath = "//td[contains(text(), 'Filtre')]")
	WebElement button_filter;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[2]/table/tbody/tr/td/table/tbody/tr/td[9]/div/div[3]/table/tbody/tr/td/table/tbody/tr/td[3]/i/input")
	WebElement first_date_field;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[2]/table/tbody/tr/td/table/tbody/tr/td[9]/div/div[3]/table/tbody/tr/td/table/tbody/tr/td[7]/i/input")
	WebElement second_date_field;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[2]/table/tbody/tr/td/table/tbody/tr/td[9]/div/div[3]/table/tbody/tr/td/table/tbody/tr/td[11]/select")
	WebElement liste_deroulante;
	
	@FindBy(xpath = "//option[.='Tous']")
	WebElement liste_deroulante_1;
	
	@FindBy(xpath = "//option[.='Ressource en file']")
	WebElement liste_deroulante_2;
	
	@FindBy(xpath = "//option[.='Ressource normale']")
	WebElement liste_deroulante_3;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[4]/div[4]/div/table/tbody/tr/td[8]/table/tbody/tr/td[2]/em/button")
	WebElement next_page;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[4]/div[4]/div/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button")
	WebElement precedent_page;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[4]/div[4]/div/table/tbody/tr/td[9]/table/tbody/tr/td[2]/em/button")
	WebElement last_page;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div[4]/div[4]/div/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button")
	WebElement first_page;
	
	public Page_CreateParticipant goToCreatePartPage(WebDriver driver) {
		createPart_Button.click();
		return PageFactory.initElements(driver, Page_CreateParticipant.class);
	}
	
	public void useFilter() {
		personnal_details.sendKeys("Jean");
		button_filter.click();		
	}
	
	public void useMoreOptions() {
		button_moreOptions.click();
	}
	
	public void checkListPages() {
		personnal_details.clear();
		button_filter.click();
		last_page.click();
		first_page.click();
		next_page.click();
		precedent_page.click();
	}
	
	public PageConnexion logout(WebDriver driver) {
		bouton_deconnexion.click();
		return PageFactory.initElements(driver, PageConnexion.class);
	}
	
	public boolean elementsPresents() {
        boolean presence;
        if (colonne_surnom.isDisplayed()
        && colonne_prenom.isDisplayed()
        && colonne_id.isDisplayed()
        && colonne_code.isDisplayed()
        && colonne_enFile.isDisplayed()
        && colonne_operations.isDisplayed()
        && filter_field.isDisplayed()
        && filter_lglass.isDisplayed()
        && personnal_details.isDisplayed()
        && button_moreOptions.isDisplayed()
        && button_filter.isDisplayed()) {
            presence=true;
        }
        else {
            presence=false;
        }
        return presence;
    }
	
}
