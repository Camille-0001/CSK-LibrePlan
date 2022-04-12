package eql.org.csk.LibreplanTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_TableauDeBord {
	@FindBy (xpath="//div[.='Mon tableau de bord' and @class='z-window-embedded-header']")
	WebElement tableau_de_bord;
	
	public boolean elementsPresents() {
        boolean presence;
        if (tableau_de_bord.isDisplayed()) {
            presence=true;
        }
        else {
            presence=false;
        }
        return presence;
    }
}
