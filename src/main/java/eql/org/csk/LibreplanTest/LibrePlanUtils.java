package eql.org.csk.LibreplanTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class LibrePlanUtils {
	
	public static String conversion_date(String date_origine) {
		String[] elements_date = date_origine.split(" ");
		String date_convertie ="";
		if (elements_date[0].length()==1) {
			date_convertie = "0";
		}
		date_convertie =date_convertie + elements_date[0]+"/";
		switch(elements_date[1]) {
		case "janv." : date_convertie = date_convertie + "01";break;
		case "févr." : date_convertie = date_convertie + "02";break;
		case "mars" : date_convertie = date_convertie + "03";break;
		case "avr." : date_convertie = date_convertie + "04";break;
		case "mai" : date_convertie = date_convertie + "05";break;
		case "juin" : date_convertie = date_convertie + "06";break;
		case "juil." : date_convertie = date_convertie + "07";break;
		case "août" : date_convertie = date_convertie + "08";break;
		case "sept." : date_convertie = date_convertie + "09";break;
		case "oct." : date_convertie = date_convertie + "10";break;
		case "nov." : date_convertie = date_convertie + "11";break;
		case "déc." : date_convertie = date_convertie + "12S";break;
		}
		date_convertie = date_convertie +"/" + elements_date[2];
		return date_convertie;
	}
	

	
	public static String dateDuJour() {
        DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date dateAjd = new Date(); //ajd
        return formatDate.format(dateAjd);
       
    }
	
	
	/**
	 * Ajoute n jours à la date du jour et renvoie le résultat sous la forme d'un String de format dd//MM//yyyy
	 * @param n le nombre de jour ajoutés
	 * @return un String contenant la date au format dd//MM//yyyy
	 */
	public static String jPlusN(int n) {  
		DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	    Date dateAjd = new Date(); //ajd
	    Calendar calendrier = Calendar.getInstance(); // convertir date to calendrier
	    calendrier.setTime(dateAjd);
	         
	    calendrier.add(Calendar.DATE, n); //ajd+n
	    Date ajdPlusNCal = calendrier.getTime(); //convertir calendrier en date        
	    String ajdPlusNStr = formatDate.format(ajdPlusNCal);//convertir date en String
		return ajdPlusNStr;
	}
}
