package eql.org.csk.LibreplanTest;

public abstract class LPxPath {
	
	// * Bouton Créer présent dans plusieurs formulaires
	public static final String BOUTON_CREER = "//td[contains(@class,'z-button-cm') and text() = 'Créer' and not(ancestor::div[contains(@style,'display:none')])]";

	
	
	// * Bouton Ressources dans le bandeau horizontal, déroule la liste des options de ressources

	public static final String MENU_RESSOURCES = "//button[contains(text(),'Ressources')]";

	
	/*
	 * Bouton Coût dans le bandeau horizontal, déroule la liste des options de coûts
	 */
	public static final String MENU_COUTS = "//button[contains(text(),'Coût')]";
}
