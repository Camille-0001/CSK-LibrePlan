package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_ListeParticipant extends LPxPath{
	
	@FindBy(xpath = "//div[@class='message_INFO']/span") 
	WebElement message_info;
	
	@FindBy(xpath = "//td[contains(@class,'z-button-cm') and text() = 'Créer' and not(ancestor::div[contains(@style,'display:none')])]") 
	WebElement createPart_Button;
	
	
	public Page_CreateParticipant create_part_access(WebDriver driver) {
		createPart_Button.click();
		return PageFactory.initElements(driver, Page_CreateParticipant.class);
	}
	
	/*
	public boolean elementsPresents() {
        boolean presence;
        if (champ_code.isDisplayed()
        && generate_code.isDisplayed()) {
            presence=true;
        }
        else {
            presence=false;
        }
        return presence;
    }
    */
	
}
