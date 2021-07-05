import java.util.ArrayList;

public class ArticoloNonNumerabile extends Articolo {

	public ArticoloNonNumerabile(Lettera reparto, Misura unita, double quantita, double prezzoEuro, String codice, ArrayList<String> descrizione) {
		
		super(reparto, unita, quantita, prezzoEuro, codice, descrizione);
	}
	
	public boolean Vendita(String codice,double quantita) {
		if(quantita < super.quantita.doubleValue()) {
			super.quantita= super.quantita.doubleValue() - quantita ;
			return true;
		}
		else if(quantita > super.quantita.doubleValue() ) {
			return false;
		}
		else {
			Utilita.listaArticoli.remove(codice);
			return true;
		}
	}
	
	public String toString() {
		return super.toString();
	}

}
