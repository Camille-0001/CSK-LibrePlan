package eql.org.csk.LibreplanTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BandeauHorizontal {

	// Logo et lien vers la paeg d'accueil
	@FindBy(css = ".logo-area img[src $= 'logo.png']")
	private WebElement logo_libreplan;

	// Menu principal : calendrier
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//button[contains(text(),'Calendrier')]")
	private WebElement menu_calendrier;

	// Menu principal : calendrier Sous menu : Projets
	@FindBy(xpath = "//a[contains(@class,'z-menu-item-cnt') and text() = ' Projets']")
	private WebElement sousmenu_projets;

	// Menu principal : ressources
	@FindBy(xpath = LPxPath.MENU_RESSOURCES)
	private WebElement menu_ressources;

	// Menu principal : ressources Sous menu : Participants
	@FindBy(xpath = LPxPath.SOUSMENU_PARTICIPANTS)
	private WebElement sousmenu_participants;

	// Menu principal : ressources Sous menu : Calendriers
	@FindBy(xpath = LPxPath.SOUSMENU_CALENDRIERS)
	private WebElement sousmenu_calendriers;

	// Menu principal : ressources Sous menu : Critère
	@FindBy(xpath = LPxPath.SOUSMENU_CRITERES)
	private WebElement sousmenu_critere;

	// Menu principal : cout
	@FindBy(xpath = LPxPath.MENU_COUTS)
	private WebElement menu_cout;

	// Menu principal : cout Sous menu : Feuille de temps
	@FindBy(xpath = LPxPath.SOUSMENU_FEUILLEDETEMPS)
	private WebElement sousmenu_feuille_de_temps;

	// Menu principal : configuration
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//button[contains(text(),'Configuration')]")
	private WebElement menu_configuration;

	// Menu principal : communications
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//button[contains(text(),'Communications')]")
	private WebElement menu_communications;

	// Menu principal : rapports
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//button[contains(text(),'Rapports')]")
	private WebElement menu_rapports;

	// Menu principal : zone personnelle
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//button[contains(text(),'Zone personnelle')]")
	private WebElement menu_zone_personnelle;

	public PageGenerique CalendrierProjets(WebDriver driver, FluentWait<WebDriver> wait) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-modal-mask"), 0));
		wait.until(ExpectedConditions.visibilityOf(menu_calendrier));
		menu_calendrier.click();
		if (!sousmenu_projets.isDisplayed()) {
			menu_calendrier.click();
		}
		wait.until(ExpectedConditions.visibilityOf(sousmenu_projets));
		sousmenu_projets.click();
		return PageFactory.initElements(driver, PageGenerique.class);
	}

	public PageGenerique RessourcesParticipants(WebDriver driver, FluentWait<WebDriver> wait) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-modal-mask"), 0));
		wait.until(ExpectedConditions.visibilityOf(menu_ressources));
		menu_ressources.click();
		if (!sousmenu_participants.isDisplayed()) {
			menu_ressources.click();
		}
		wait.until(ExpectedConditions.visibilityOf(sousmenu_participants));
		sousmenu_participants.click();
		return PageFactory.initElements(driver, PageGenerique.class);
	}

	public PageGenerique RessourcesCalendriers(WebDriver driver, FluentWait<WebDriver> wait) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-modal-mask"), 0));
		wait.until(ExpectedConditions.visibilityOf(menu_ressources));
		menu_ressources.click();
		if (!sousmenu_calendriers.isDisplayed()) {
			menu_ressources.click();
		}
		wait.until(ExpectedConditions.visibilityOf(sousmenu_calendriers));
		sousmenu_calendriers.click();
		return PageFactory.initElements(driver, PageGenerique.class);
	}

	public PageGenerique RessourcesCriteres(WebDriver driver, FluentWait<WebDriver> wait) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-modal-mask"), 0));
		wait.until(ExpectedConditions.visibilityOf(menu_ressources));
		menu_ressources.click();
		if (!sousmenu_critere.isDisplayed()) {
			menu_ressources.click();
		}
		wait.until(ExpectedConditions.visibilityOf(sousmenu_critere));
		sousmenu_critere.click();
		return PageFactory.initElements(driver, PageGenerique.class);
	}

	public PageGenerique CoutFeuilleDeTemps(WebDriver driver, FluentWait<WebDriver> wait) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".z-modal-mask"), 0));
		wait.until(ExpectedConditions.visibilityOf(menu_cout));
		menu_cout.click();
		if (!sousmenu_feuille_de_temps.isDisplayed()) {
			menu_cout.click();
		}
		wait.until(ExpectedConditions.visibilityOf(sousmenu_feuille_de_temps));
		sousmenu_feuille_de_temps.click();
		return PageFactory.initElements(driver, PageGenerique.class);
	}

	public PageGenerique retourPageAccueil(WebDriver driver, FluentWait<WebDriver> wait) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".z-modal-mask")));
		wait.until(ExpectedConditions.visibilityOf(logo_libreplan));
		logo_libreplan.click();
		return PageFactory.initElements(driver, PageGenerique.class);
	}

}
