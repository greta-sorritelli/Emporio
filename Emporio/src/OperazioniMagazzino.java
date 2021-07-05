import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.io.InputStreamReader;

public class OperazioniMagazzino {

	public void CalcoloValoreMagazzino() {

		double valoreTotale = 0;

		for (Map.Entry<String, Articolo> entry : Utilita.listaArticoli.entrySet()) {

			Articolo value = entry.getValue();
			Number quantita = value.getQuantita();
			double prezzoEuro = value.getPrezzoEuro();
			double prodotto = quantita.doubleValue() * prezzoEuro;
			valoreTotale += prodotto;

		}

		System.out.print("Il valore totale del magazzino è: " + valoreTotale + "\n");
	}

	public void VenditaArticolo() throws IOException, EccezioneCodice {
		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in)); // scrittura da console
		System.out.print("Inserisci il codice: ");
		try {
			String codice = brInput.readLine();
			if (Utilita.controlloCodice(codice) && Utilita.listaArticoli.containsKey(codice)) {
				System.out.println("Inserisci la quantita da vendere: ");
				String quantita = brInput.readLine();
				if (Utilita.listaArticoli.get(codice).getUnita() == Misura.U) {
					ArticoloNumerabile articoloNumerabile = (ArticoloNumerabile) Utilita.listaArticoli.get(codice);
					try {
						int quantitaInt = Integer.parseInt(quantita);
						if (articoloNumerabile.Vendita(codice, quantitaInt)) {
							System.out.println("Articolo venduto correttamente.");
						} else {
							System.out.println(
									"Non è possibile vendere il prodotto, perchè la quantità in magazzino non è sufficiente.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Quantità non riconosciuta.");
					}
				} else {
					ArticoloNonNumerabile articoloNonNumerabile = (ArticoloNonNumerabile) Utilita.listaArticoli
							.get(codice);
					try {
						double quantitaDouble = Double.parseDouble(quantita);
						if (articoloNonNumerabile.Vendita(codice, quantitaDouble)) {
							System.out.println("Articolo venduto correttamente.");
						} else {
							System.out.println(
									"Non è possibile vendere il prodotto, perchè la quantita in magazzino non è sufficiente.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Quantità non riconosciuta.");
					}
				}
			} else {
				System.out.println("Codice prodotto non riconosciuto.");
			}

		} finally {
		}
	}

	public void InserimentoArticolo() throws IOException, NoSuchElementException, IllegalStateException,
			EccezioneCodice, EccezioneCodiceEsistente {

		System.out.println(
				"Inserisci i parametri dell'articolo (Reparto;Unità;Quantità;Prezzo in euro;Codice univoco;Consumo in watt;Descrizione;)");
		BufferedReader nInput = new BufferedReader(new InputStreamReader(System.in)); // scrittura da console
		String nuovo = null;

		if ((nuovo = nInput.readLine()) != null) {

			List<String> campi = new ArrayList<String>();
			campi = Arrays.asList(nuovo.split(";"));

			boolean primo;
			boolean secondo;

			switch (campi.get(0)) {

			case "E":
			case "F":
			case "L":
			case "C":
			case "S":
				primo = true;
				break;
			default:
				primo = false;
				break;
			}

			switch (campi.get(1)) {

			case "U":
			case "K":
			case "L":
			case "M":
				secondo = true;
				break;
			default:
				secondo = false;
				break;
			}

			if (primo && secondo) {

				if (Utilita.controlloCodice(campi.get(4), Utilita.listaArticoli)) {

					if (Utilita.isNumber(campi.get(2)) && Utilita.isNumber(campi.get(3))) {

						if (Utilita.isNumber(campi.get(5))) {

							Lettera reparto = null;
							Misura unita = null;
							int quantitaInt = 0;
							double quantitaDouble = 0;
							double prezzoEuro = 0;

							String rep = (String) campi.get(0);
							String uni = (String) campi.get(1);
							String qDouble = (String) campi.get(2);
							String pre = (String) campi.get(3);
							String cod = (String) campi.get(4);
							String cons = (String) campi.get(5);

							double cons1 = Double.parseDouble(cons);

							if (rep.equals("E")) {
								reparto = Lettera.E;
							} else if (rep.equals("F")) {
								reparto = Lettera.F;
							} else if (rep.equals("L")) {
								reparto = Lettera.L;
							} else if (rep.equals("C")) {
								reparto = Lettera.C;
							} else if (rep.equals("S")) {
								reparto = Lettera.S;
							}

							if (uni.equals("U")) {
								unita = Misura.U;
								int q1 = Integer.parseInt(qDouble);
								quantitaInt = q1;
							} else if (uni.equals("K")) {
								unita = Misura.K;
								double q2 = Double.parseDouble(qDouble);
								quantitaDouble = q2;
							} else if (uni.equals("M")) {
								unita = Misura.M;
								double q2 = Double.parseDouble(qDouble);
								quantitaDouble = q2;
							} else if (uni.equals("L")) {
								unita = Misura.L;
								double q2 = Double.parseDouble(qDouble);
								quantitaDouble = q2;
							}

							else {
								Utilita.elementiNonRiconosciuti += 1;
							}

							double p = Double.parseDouble(pre);
							prezzoEuro = p;

							if (Utilita.controlloCodice(cod, Utilita.listaArticoli) == false) {
								Utilita.elementiNonRiconosciuti += 1;
							}

							ArrayList<String> descrizione = new ArrayList<String>();
							for (int i = 6; i < campi.size(); i++) {

								descrizione.add(campi.get(i));
							}

							if (unita == Misura.U) {
								ArticoloNumerabile articolo = new ArticoloNumerabile(reparto, unita, quantitaInt,
										prezzoEuro, cod, cons1, descrizione);
								Utilita.listaArticoli.put(cod, articolo);
							} else if (unita == Misura.K || unita == Misura.M || unita == Misura.L) {
								ArticoloNonNumerabile articoloN = new ArticoloNonNumerabile(reparto, unita,
										quantitaDouble, prezzoEuro, cod, descrizione);
								Utilita.listaArticoli.put(cod, articoloN);
							}

						}

					} else {
						System.out.println("Valori numerici non validi.");
					}
				} else {
					System.out.println("Codice errato,articolo presente.");
				}

			} else {
				System.out.println("Reparto o unità di misura non validi.");
			}
		}
	}

	public void RicercaArticolo() throws IOException, EccezioneCodice {
		System.out.println("Inserisci il codice dell'articolo da cercare:");
		BufferedReader rInput = new BufferedReader(new InputStreamReader(System.in)); // scrittura da console
		String s = rInput.readLine();

		if (Utilita.controlloCodice(s)) {
			try {
				String valore = Utilita.listaArticoli.get(s).toString();
				System.out.println("L'articolo corrispondente è: " + valore);
			} catch (Exception e) {
				System.out.println("A nessun articolo è associato questo codice.");
			}
		} else {
			System.out.println("Errore nella ricerca dell'articolo.");
		}
	}
}