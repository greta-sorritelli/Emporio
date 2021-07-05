import java.util.HashMap;
import java.util.Map;

public class Utilita {

	public static int elementiNonRiconosciuti = 0;
	public static Map<String, Articolo> listaArticoli = new HashMap<>();

	private static boolean controlloLunghezza(String codice) {
		if (codice.length() == 8) {
			return true;
		} else {
			return false;
		}

	}

	private static boolean isNumeric(String codice) { // controllo numeri da 1 a 9
		return codice != null && codice.matches("\\d+");
	}

	public static boolean isNumber(String s) { // controllo numeri negativi
		return s != null && s.matches("\\d.*");
	}

	public static boolean controlloCodiceEsistente(String codice, Map<String, Articolo> map)
			throws EccezioneCodiceEsistente {

		if (map.containsKey(codice)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean controlloCodice(String codice) throws EccezioneCodice {
		if (controlloLunghezza(codice) && isNumeric(codice)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean controlloCodice(String codice, Map<String, Articolo> map)
			throws EccezioneCodice, EccezioneCodiceEsistente {
		if (controlloLunghezza(codice) && isNumeric(codice) && controlloCodiceEsistente(codice, map)) {
			return true;
		} else {
			return false;
		}
	}
}