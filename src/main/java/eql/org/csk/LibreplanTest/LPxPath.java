package eql.org.csk.LibreplanTest;
/*
 * Classe donnant accès aux xpaths fréquemment utilisés dans Libreplan
 * 
 * 
 * 
 */
public abstract class LPxPath {
	
	/* 
	 * Xpaths liés à des boutons fréquemment utilisés
	 */
	
	// * Bouton Créer présent dans plusieurs formulaires
	public static final String BOUTON_CREER = "//td[contains(@class,'z-button-cm') and text() = 'Créer' and not(ancestor::div[contains(@style,'display:none')])]";

	// * Bouton Entrer
	public static final String BOUTON_ENTRER = "//td[@class='z-button-cm' and text() = 'Entrer' and not (ancestor::div[contains(@style,'display:none')])]";
	
	// * Bouton Enregistrer
	public static final String BOUTON_ENREG = "//td[@class='z-button-cm' and text() = 'Enregistrer' and not (ancestor::div[contains(@style,'display:none')])]";
	
	// * Bouton "Sauver et Continuer"
	public static final String BOUTON_SAUV_ET_CONT = "//td[@class='z-button-cm' and text() = 'Sauver et continuer' and not (ancestor::div[contains(@style,'display:none')])]";
	
	// * Bouton Annuler
	public static final String BOUTON_ANNULER = "//td[@class='z-button-cm' and text() = 'Annuler' and not (ancestor::div[contains(@style,'display:none')])]";
	
	/* 
	 * Xpaths liés au bandeau de naviagation
	 * Ces liens ne devraient pas être utilisés, l'accès à ces boutons devrait se faire via l'extension de la classe BandeauVertical
	 */
	
	// * Bouton Ressources dans le bandeau horizontal, déroule la liste des options de ressources

	public static final String MENU_RESSOURCES = "//button[contains(text(),'Ressources')]";
	
	// * Bouton Participants dans le sousmenu ressources du bandeau horizontal

	public static final String SOUSMENU_PARTICIPANTS = "//a[contains(@class,'z-menu-item-cnt') and text() = ' Participants']";

	// * Bouton Calendriers dans le sousmenu ressources du bandeau horizontal

	public static final String SOUSMENU_CALENDRIERS = "//a[contains(@class,'z-menu-item-cnt') and text() = ' Calendriers']";

	// * Bouton Criteres dans le sousmenu ressources du bandeau horizontal
	
	public static final String SOUSMENU_CRITERES = "//a[contains(@class,'z-menu-item-cnt') and text() = ' Critère ']";

	
	// * Bouton Coût dans le bandeau horizontal, déroule la liste des options de coûts
	public static final String MENU_COUTS = "//button[contains(text(),'Coût')]";

	// * Bouton Criteres dans le sousmenu ressources du bandeau horizontal
	
	public static final String SOUSMENU_FEUILLEDETEMPS = "//a[contains(@class,'z-menu-item-cnt') and text() = ' Feuille de temps']";
	
	

	
}
