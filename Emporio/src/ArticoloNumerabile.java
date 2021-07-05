import java.util.ArrayList;

public class ArticoloNumerabile extends Articolo {
	
	public ArticoloNumerabile(Lettera reparto, Misura unita, int quantita, double prezzoEuro, String codice, double consumoWatt, ArrayList<String> descrizione) {
		
		super(reparto, unita, quantita, prezzoEuro, codice, consumoWatt,descrizione );
	}
	
	public boolean Vendita(String codice,int quantita) {
		if(quantita < super.quantita.intValue()) {
			super.quantita= super.quantita.intValue() - quantita ;
			return true;
		}
		else if(quantita > super.quantita.intValue() ) {
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
