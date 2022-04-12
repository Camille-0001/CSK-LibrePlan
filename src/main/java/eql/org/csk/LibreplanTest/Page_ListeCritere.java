package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_ListeCritere {
	
	@FindBy(xpath = "//td[contains(@class,'z-button-cm') and text() = 'Créer' and not(ancestor::div[contains(@style,'display:none')])]") 
	WebElement bouton_creer;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div/div[3]/table/tbody[2]/tr[2]/td[5]/div/table/tbody/tr/td/table/tbody/tr/td[1]/span/table/tbody/tr[2]/td[2]/img")
	WebElement bouton_modifier;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div/div[3]/table/tbody[2]/tr[3]/td[1]/div/span")
	WebElement nom_dans_la_liste;
	
	@FindBy (xpath="/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div/div[3]/table/tbody[2]/tr[3]/td[5]/div/table/tbody/tr/td/table/tbody/tr/td[3]/span/table/tbody/tr[2]/td[2]/img")
	WebElement bouton_supprimer;
	
	@FindBy (xpath="//span[.='Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?']")
	WebElement text_popUp_delete;
	
	@FindBy (xpath="/html/body/div[3]/div[3]/div/div/div/table[2]/tbody/tr/td/table/tbody/tr/td[1]/span/table/tbody/tr[2]/td[2]")
	WebElement ok_popUp_delete;
	
	@FindBy (xpath="/html/body/div[3]/div[3]/div/div/div/table[2]/tbody/tr/td/table/tbody/tr/td[3]/span/table/tbody/tr[2]/td[2]")
	WebElement cancel_popUp_delete;
	
	public Page_CreateCritere goToCreateCritPage(WebDriver driver) {
		bouton_creer.click();
		return PageFactory.initElements(driver, Page_CreateCritere.class);
	}
	
	public Page_CreateCritere modify_click_icon(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		bouton_modifier.click();
		return PageFactory.initElements(driver, Page_CreateCritere.class);
	}
	
	public Page_CreateCritere modify_click_name(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		nom_dans_la_liste.click();
		return PageFactory.initElements(driver, Page_CreateCritere.class);
	}
	
	public void delete_click_icon_then_cancel() throws InterruptedException {
		Thread.sleep(1000);
		bouton_supprimer.click();
		cancel_popUp_delete.click();
	}
	
	public void delete_click_icon_then_ok() throws InterruptedException {
		Thread.sleep(1000);
		bouton_supprimer.click();
		ok_popUp_delete.click();
	} 
	
}
