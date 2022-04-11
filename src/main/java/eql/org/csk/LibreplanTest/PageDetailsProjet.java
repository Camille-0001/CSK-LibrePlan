package eql.org.csk.LibreplanTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageDetailsProjet {
	//fil d'Ariane
	
	@FindBy (xpath = "//tr[contains(@class,'ruta')]/td[2]/strong")
	WebElement fil_debut;
	
	@FindBy (xpath = "//tr[contains(@class,'ruta')]//tbody/tr[1]/td[3]/span")
	WebElement fil_calendrier;
	
	@FindBy (xpath = "//tr[contains(@class,'ruta')]//tbody/tr[1]/td[7]/span")
	WebElement fil_plan_projet;
	
	@FindBy (xpath = "//tr[contains(@class,'ruta')]//tbody/tr[1]/td[11]/span")
	WebElement fil_nom_projet;
	
	public boolean filAriane() {
		
		String fil_entete_attendu = "DEBUT";
		String fil_cat_attendu = "Calendrier";
		String fil_subcat_attendu = "Détail du projet";
		String fil_nom_attendu = "PROJET_TEST1";
		
		fil_debut.getText().equals(fil_entete_attendu);
		fil_calendrier.getText().equals(fil_cat_attendu);
		fil_plan_projet.getText().equals(fil_subcat_attendu);
		fil_nom_projet.getText().equals(fil_nom_attendu);
		return true;
	}
	
	@FindBy (xpath = "//span[contains(text(),'Nouvelle tâche')]/..//following-sibling::td/input[contains(@class,'z-textbox')]")
	WebElement ch_nouvelle_tache;
	
	@FindBy (xpath = "//span[contains(text(),'Nouvelle tâche')]/..//following-sibling::td/input[contains(@class,'z-intbox')]")
	WebElement ch_heures;
	
	@FindBy (xpath = "//td[@class='z-button-cm' and text() = 'Ajouter' and not (ancestor::div[contains(@style,'display:none')])]")
	WebElement bouton_ajouter;
	
	
	public void ajouterTache(String nom, String heures) {
		
		ch_nouvelle_tache.clear();
		ch_nouvelle_tache.sendKeys(nom);
		ch_heures.clear();
		ch_heures.sendKeys(heures);
		bouton_ajouter.click();
			
	}
	

	
	@FindBy (xpath = "//tbody[2]/tr[1]/td[3]/div/input")
	WebElement ch_nom_tab1;
	
	@FindBy (xpath = "//tbody[2]/tr[2]/td[3]/div/input")
	WebElement ch_nom_tab2;
	
	@FindBy (xpath = "//tbody[2]/tr[3]/td[3]/div/input")
	WebElement ch_nom_tab3;
	
	@FindBy (xpath = "//tbody[2]/tr[4]/td[3]/div/input")
	WebElement ch_nom_tab4;
	
	@FindBy (xpath = "//tbody[contains(@class,'z-treechildren')]/tr[1][contains(@title,'Tache1-P1.  Avancement:0.')]")
	WebElement infobulle_tache1_1;
	
	@FindBy (xpath = "//tbody[contains(@class,'z-treechildren')]/tr[1]//span[contains(@title,'Déprogrammé')]")
	WebElement infobulle_tache1_2;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[2]//input")
	WebElement ch_code_tache1;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache2-P1.  Avancement:0.')]/td[2]//input")
	WebElement ch_code_tache2;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache3-P1.  Avancement:0.')]/td[2]//input")
	WebElement ch_code_tache3;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache4-P1.  Avancement:0.')]/td[2]//input")
	WebElement ch_code_tache4;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[4]//input")
	WebElement ch_heures_tache1;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[5]//input")
	WebElement ch_budget_tache1;
		
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[6]//input")
	WebElement ch_debut_tache1;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache2-P1.  Avancement:0.')]/td[6]//input")
	WebElement ch_debut_tache2;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache3-P1.  Avancement:0.')]/td[6]//input")
	WebElement ch_debut_tache3;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache4-P1.  Avancement:0.')]/td[6]//input")
	WebElement ch_debut_tache4;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[7]//input")
	WebElement ch_echeance_tache1;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache2-P1.  Avancement:0.')]/td[7]//input")
	WebElement ch_echeance_tache2;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache3-P1.  Avancement:0.')]/td[7]//input")
	WebElement ch_echeance_tache3;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache4-P1.  Avancement:0.')]/td[7]//input")
	WebElement ch_echeance_tache4;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[8]//span[contains(@title,'Modifier')]")
	WebElement icone_modifier;
	
	@FindBy (xpath = "//tr[contains(@title,'Tache1-P1.  Avancement:0.')]/td[8]//span[contains(@title,'Supprimer')]")
	WebElement icone_supprimer;
	
	@FindBy (xpath="//span[contains(@title,'Enregistrer le projet')]//img")
	WebElement enregistrer_image;
	
	@FindBy (xpath="//td[@class='z-button-cm' and text()='OK']")
	WebElement bouton_ok;
	
	@FindBy (xpath = "//td[contains(text(),'Planification de projet')]")
	WebElement plan_projet;
	
	@FindBy (xpath = "//span[contains(@title,'Descendre la tâche sélectionnée')]")
	WebElement fleche_desc;
	
	@FindBy (xpath = "//span[contains(@title,'Remonter la tâche sélectionnée')]")
	WebElement fleche_mont;
	
		
	public boolean presenceElemTache() {
		
		infobulle_tache1_1.isDisplayed();
		infobulle_tache1_2.isDisplayed();
		ch_code_tache1.getAttribute("value").isEmpty();
		ch_nom_tab1.getAttribute("value").contains("Tache1-P1");
		ch_heures_tache1.getAttribute("value").contains("5");
		ch_budget_tache1.getAttribute("value").contains("0 €");
		ch_debut_tache1.getAttribute("value").isEmpty();
		ch_echeance_tache1.getAttribute("value").isEmpty();
		icone_modifier.isDisplayed();
		icone_supprimer.isDisplayed();		
		return true;
	}
	
	
	
	public boolean ordreTaches(String t1, String t2, String t3, String t4) {
		
		ch_nom_tab1.getAttribute("value").contains(t1);
		ch_nom_tab2.getAttribute("value").contains(t2);
		ch_nom_tab3.getAttribute("value").contains(t2);
		ch_nom_tab4.getAttribute("value").contains(t3);
		
		return true;		
	}
	
	public void descTache() {
		ch_nom_tab1.click();
		fleche_desc.click();
	}
	
	public void montTache() {
		ch_nom_tab3.click();
		fleche_mont.click();
	}
	
	public static String getMoisAnnee(String jour) {
		DateFormat formatDate = new SimpleDateFormat("MM/yyyy");
	    Date dateAjd = new Date(); //ajd
	    Calendar calendrier = Calendar.getInstance(); // convertir date to calendrier
	    calendrier.setTime(dateAjd);
	    Date moisAnneeCal = (Date) calendrier.getTime(); //convertir calendrier en date        
	    String moisAnneStr = formatDate.format(moisAnneeCal);//convertir date en String
		String jjMMyyyy = jour+moisAnneStr;
		return jjMMyyyy;
		}
		
	
	public void remplirTaches() {
		ch_code_tache1.clear();
		ch_code_tache1.sendKeys("T1");
		ch_code_tache2.clear();
		ch_code_tache2.sendKeys("T2");
		ch_code_tache3.clear();
		ch_code_tache3.sendKeys("T3");
		ch_code_tache4.clear();
		ch_code_tache4.sendKeys("T4");
		ch_debut_tache1.clear();
		ch_debut_tache1.sendKeys(getMoisAnnee("05/"));
		ch_debut_tache2.clear();
		ch_debut_tache2.sendKeys(getMoisAnnee("08/"));
		ch_echeance_tache3.clear();
		ch_echeance_tache3.sendKeys(getMoisAnnee("03/"));
		ch_echeance_tache4.clear();
		ch_echeance_tache4.sendKeys(getMoisAnnee("05/"));
		enregistrer_image.click();
		bouton_ok.click();
		plan_projet.click();
	}
		
}
	
	