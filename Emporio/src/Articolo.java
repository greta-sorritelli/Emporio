import java.util.ArrayList;

public abstract class Articolo {

	private Lettera reparto;
	private Misura unita;
	protected Number quantita;
	private double prezzoEuro;
	private String codice;
	private ArrayList<String> descrizione;
	private double consumoWatt;

	public Articolo(Lettera reparto, Misura unita, double quantita, double prezzo, String codice, ArrayList<String> descrizione) {

		this.reparto = reparto;
		this.unita = unita;
		this.quantita = quantita;
		this.prezzoEuro = prezzo;
	    this.codice = codice;
		this.descrizione = descrizione;
	}

	public Articolo(Lettera reparto, Misura unita, int quantita, double prezzo, String codice, double consumoWatt, ArrayList<String> descrizione) {

		this.reparto = reparto;
		this.unita = unita;
		this.quantita = quantita;
		this.prezzoEuro = prezzo;
		this.codice = codice;
		this.descrizione = descrizione;
		this.consumoWatt = consumoWatt;
	}

	public String toString() {
		return reparto + " " + unita + " " + quantita + " " + prezzoEuro + " " + codice + " " +consumoWatt + " " + descrizione;
	}

	public Lettera getReparto() {
		return reparto;
	}

	public Misura getUnita() {
		return unita;
	}

	public Number getQuantita() {
		return quantita;
	}

	public double getPrezzoEuro() {
		return prezzoEuro;
	}

	public String getCodice() {
		return codice;
	}

	public ArrayList<String> getDescrizione() {
		return descrizione;
	}

	public double getConsumoWatt() {
		return consumoWatt;
	}
}
