package eql.org.csk.LibreplanTest;


import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagePlanificationProjets extends LPxPath{
	
	//onglet calendrier
	@FindBy (xpath="//button[contains(text(),'Calendrier')]")
	WebElement bouton_calendar;
	
	//bouton ressources
	@FindBy (xpath=MENU_RESSOURCES)
	WebElement menu_ressources;
	
	//bouton participants
	@FindBy (xpath="//a[@href='/libreplan/resources/worker/worker.zul']")
	WebElement bouton_participants;
	
	//bouton submit
	@FindBy (xpath="//span[@class='planner-icon z-button']//td[@class='z-button-cm']/img")
	WebElement bouton_creer_projet;
	
	//champ_name
	@FindBy (xpath = "//span[contains(text(),'Nom')]/parent::div/parent::td/following-sibling::td/div/input")
	WebElement ch_nom_projet;
	
	//champ modèle
	@FindBy (xpath = "//span[contains(text(),'Modèle')]/parent::div/parent::td/following-sibling::td//input")
	WebElement ch_modele_projet;
	
	//case generer code
	@FindBy (xpath = "//label[text()='Générer le code']/preceding-sibling::input")
	WebElement case_code_projet;
	
	//champ generer code
	@FindBy (xpath = "//span[contains(text(),'Code')]/parent::div/parent::td/following-sibling::td//input[contains(@class,'z-textbox')]")
	WebElement ch_code_projet;
	
	//champ date debut
	@FindBy (xpath = "//span[contains(text(),'Date de début')]/parent::div/parent::td/following-sibling::td//input[contains(@class, 'z-datebox-inp')]")
	WebElement ch_debut_projet;
	
	//champ date debut
	@FindBy (xpath = "//span[contains(text(),'Echéance')]/parent::div/parent::td/following-sibling::td//input[contains(@class, 'z-datebox-inp')]")
	WebElement ch_echeance_projet;
	
	//champ Calendrier
	@FindBy (xpath = "//span[contains(text(),'Calendrier')]/parent::div/parent::td/following-sibling::td//input[contains(@class, 'z-combobox-inp')]")
	WebElement ch_calendrier_projet;
	
	//bouton accepter
	@FindBy (xpath = "//td[@class='z-button-cm' and text() = 'Accepter' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_accepter;
	
	//bouton annuler
	@FindBy (xpath = "//td[@class='z-button-cm' and text() = 'Annuler' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_annuler;
	
	//verifier presence champs formularie
	public boolean presenceElemFormProj() {
		bouton_creer_projet.click();
		ch_nom_projet.isDisplayed();
		ch_modele_projet.getText().isEmpty();
		return true;
	}
	
	//methodes pour calculer j+5 et j+15 et les convertir en String
	public static String jPlusCinq() {  
		DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	    Date dateAjd = new Date(); //ajd
	    Calendar calendrier = Calendar.getInstance(); // convertir date to calendrier
	    calendrier.setTime(dateAjd);
	         
	    calendrier.add(Calendar.DATE, 5); //ajd+5
	    Date ajdPlusCinqCal = (Date) calendrier.getTime(); //convertir calendrier en date        
	    String ajdPlusCinqStr = formatDate.format(ajdPlusCinqCal);//convertir date en String
		return ajdPlusCinqStr;
	}
	
	public static String jPlusQuinze() {  
		DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	    Date dateAjd = new Date(); //ajd
	    Calendar calendrier = Calendar.getInstance(); // convertir date to calendrier
	    calendrier.setTime(dateAjd);
	         
	    calendrier.add(Calendar.DATE, 15); //ajd+15
	    Date ajdPlusQuinzeCal = (Date) calendrier.getTime(); //convertir calendrier en date        
	    String ajdPlusQuinzeStr = formatDate.format(ajdPlusQuinzeCal);//convertir date en String
	    return ajdPlusQuinzeStr;
	}
		
	//méthode création projet	
	public void creationProjet(WebDriver driver) throws InterruptedException {
		
		ch_nom_projet.clear();
		ch_nom_projet.sendKeys("PROJET_TEST1");//remplir champ Nom
		if (case_code_projet.isSelected()) { //decocher la case code
			case_code_projet.click();
		}
		ch_code_projet.clear();
		ch_code_projet.sendKeys("PRJTEST001"); //remplir champ code
		ch_debut_projet.clear();
        ch_debut_projet.sendKeys(jPlusCinq()); 
        ch_echeance_projet.clear();
        ch_echeance_projet.sendKeys(jPlusQuinze());
        bouton_accepter.click();
	}
	
	
	//elements menu vertical 
	@FindBy (xpath = "//td[contains(text(),'Planification de projet')]")
	WebElement plan_projet;
	
	@FindBy (xpath = "//td[contains(text(),'Détail du projet')]")
	WebElement details_projet;
	
	@FindBy (xpath = "//td[contains(text(),'Chargement des ressources')]")
	WebElement chargement_ressources;
	
	@FindBy (xpath = "//td[contains(text(),'Allocation avancée')]")
	WebElement allocation_avancee;
	
	@FindBy (xpath = "//td[contains(text(),'Tableau de bord')]")
	WebElement tableau_de_bord;
	
	
	public boolean menuVerticalPresent() {
		plan_projet.isDisplayed();
		details_projet.isDisplayed();
		chargement_ressources.isDisplayed();
		allocation_avancee.isDisplayed();
		tableau_de_bord.isDisplayed();
		return true;
	}
	
	//elements menu horizontal
	@FindBy (xpath = "//span[contains(text(),'WBS')]")
	WebElement wbs_taches;
	
	@FindBy (xpath = "//span[contains(text(),'Données générales')]")
	WebElement donnees_generales;
	
	@FindBy (xpath = "//div[contains(@class,'z-tabs-header')]//span[contains(text(),'Coût') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement cout;
	
	@FindBy (xpath = "//span[contains(text(),'Avancement') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement avencement;
	
	@FindBy (xpath = "//span[contains(text(),'Libellés') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement libelles;
	
	@FindBy (xpath = "//span[contains(text(),'Exigence de critère') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement exigences_critere;
	
	@FindBy (xpath = "//span[contains(text(),'Matériels') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement materiels;
	
	@FindBy (xpath = "//span[contains(text(),'Formulaires qualité des tâches') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement form_qualite_tache;
	
	@FindBy (xpath = "//span[contains(text(),'Autorisation') and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement autorisation;
	
	public boolean menuHorizontalPresent() {
		String wbs_taches_class = "z-tab z-tab-seld";
		wbs_taches.getAttribute("class").equals(wbs_taches_class);
		wbs_taches.isDisplayed();
		donnees_generales.isDisplayed();
		cout.isDisplayed();
		avencement.isDisplayed();
		libelles.isDisplayed();
		exigences_critere.isDisplayed();
		materiels.isDisplayed();
		form_qualite_tache.isDisplayed();
		autorisation.isDisplayed();
		return true;
	}
	//elements menu enregistrer et annuler les modifications
	
	@FindBy (xpath="//span[contains(@title,'Enregistrer le projet')]")
	WebElement enregistrer_titre;
	
	@FindBy (xpath="//span[contains(@title,'Enregistrer le projet')]//img")
	WebElement enregistrer_image;
	
	@FindBy (xpath="//span[contains(@title,'Annuler')]")
	WebElement fleche_titre;
	
	@FindBy (xpath="//span[contains(@title,'Annuler')]//img")
	WebElement fleche_img;
	
	public boolean menuModifPresent() {
		enregistrer_titre.isDisplayed();
		enregistrer_image.isDisplayed();
		fleche_titre.isDisplayed();
		fleche_img.isDisplayed();
		return true;
	}
	//message "Les modifications non enregistrées seront perdues. Êtes-vous sûr ?"
	@FindBy (xpath="//div[contains(@class,'z-messagebox')]")
	WebElement message_sortie;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text()='OK']")
	WebElement bouton_ok;
	
	
	public boolean fenetreSortie() {
		fleche_img.click();
		String message_text_attendu = "Les modifications non enregistrées seront perdues. Êtes-vous sûr ?";
		message_sortie.getText().equals(message_text_attendu);
		bouton_ok.isEnabled();
		bouton_annuler.isEnabled();
		bouton_annuler.click();
		
		fleche_img.click();
		message_sortie.getText().equals(message_text_attendu);
		bouton_ok.isEnabled();
		bouton_annuler.isEnabled();
		bouton_ok.click();
		return true;
	}
	
	//menu Calendrier
	@FindBy (xpath="//button[contains(text(),'Calendrier')]/../..")
	WebElement menu_calendrier;
	
	//option Projets
	@FindBy (xpath="//a[contains(text(),' Projets') and not(ancestor::li[contains(@style,'display:none')])]")
	WebElement option_projets;
	
	public PageListeProjets afficherListeProjetsMenu(WebDriver driver) {
		Actions mouseover = new Actions(driver);
		mouseover.moveToElement(menu_calendrier).build().perform();
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(option_projets));
		option_projets.click();
		return PageFactory.initElements(driver, PageListeProjets.class);
	}
		
	public int nombreBarresAvencement(WebDriver driver) {
			int count = driver.findElements(By.xpath("//div[contains(@z.type,'ganttz.task.Task')]")).size();
			return count;
		}
				
		@FindBy (xpath = "//div[contains(@class,'timetracker_column_deadline')]")
		WebElement ligne_echeance;
		
		@FindBy (xpath = "//div[contains(@class,'timetracker_column_start')]")
		WebElement ligne_debut;
		
	public boolean lignesDebutEcheance() {
			ligne_debut.isDisplayed();
			ligne_echeance.isDisplayed();
			return true;
		
		}
	
	
	/* Partie rajoutée par Stéphane*/

	public Page_ListeParticipant goToListePartPage(WebDriver driver) throws InterruptedException {
		menu_ressources.click();
		if(!bouton_participants.isDisplayed()) {
			menu_ressources.click();
		}
		bouton_participants.click();
		return PageFactory.initElements(driver, Page_ListeParticipant.class);
	}
	
	public boolean elementsPresents() {
        boolean presence;
        if (bouton_calendar.isDisplayed()) {
            presence=true;
        }
        else {
            presence=false;
        }
        return presence;
    }
	
	
}
