import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetturaFile {

	public void LetturaTXT() throws EccezioneCodice, EccezioneCodiceEsistente {

		try {
			File txt = new File("EMPORIO.TXT");
			BufferedReader br = new BufferedReader(new FileReader(txt));     
			
			while (true) {
				String line = null;
				line = br.readLine();
				if (line == null)
					break;

				List<String> parametri = new ArrayList<String>();

				parametri = Arrays.asList(line.split(";"));

				Lettera reparto = null;
				Misura unita = null;
				int quantitaInt = 0;
				double quantitaDouble = 0;
				double prezzoEuro = 0;
				String codice = null;
				double consumoWatt = 0;

				 String rep = (String) parametri.get(0);
				 String uni = (String) parametri.get(1);
				 String qDouble = (String) parametri.get(2);
				 String pre = (String) parametri.get(3);
				 String cod = (String) parametri.get(4);
				 String cons = (String) parametri.get(5);

				consumoWatt = Double.parseDouble(cons);
				codice = cod;

				if (rep.length() == 1) {
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

				}

				if (uni.length() > 0) {
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
					
				} else {
					Utilita.elementiNonRiconosciuti += 1;

				}
				double p = Double.parseDouble(pre);
				prezzoEuro = p;

				if (Utilita.controlloCodice(codice) == false) {
					Utilita.elementiNonRiconosciuti += 1;
				}

				ArrayList<String> descrizione = new ArrayList<String>();
				for (int i = 6; i < parametri.size(); i++) {

					descrizione.add(parametri.get(i));
				}

				if (unita == Misura.U) {
					ArticoloNumerabile articolo = new ArticoloNumerabile(reparto, unita, quantitaInt, prezzoEuro,
							codice, consumoWatt, descrizione);
					Utilita.listaArticoli.put(codice, articolo);
				} else {
					ArticoloNonNumerabile articoloN = new ArticoloNonNumerabile(reparto, unita, quantitaDouble,
							prezzoEuro, codice, descrizione);
					Utilita.listaArticoli.put(codice, articoloN);
				}

			}
			
			br.close();

		} catch (IllegalArgumentException e) {
			Utilita.elementiNonRiconosciuti += 1;
			System.out.println("Errore durante il caricamento del file: uno o più parametri hanno un formato errato.");
		} catch (ArrayIndexOutOfBoundsException e) {
			Utilita.elementiNonRiconosciuti += 1;
			System.out.println("Errore durante il caricamento del file: numero di parametri errato.");
		} catch (IOException e) {
			System.out.println("Errore");
		}

	}
}