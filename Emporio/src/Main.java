import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception, NullPointerException {

		LetturaFile rf = new LetturaFile();
		rf.LetturaTXT();

		System.out.print("\nSeleziona un'azione digitando il numero corrispondente.\n"
				+ "-------------------------------------------------------\n"
				+ "| 1 - calcolo del valore totale del magazzino         |\n"
				+ "| 2 - vendita di un prodotto                          |\n"
				+ "| 3 - inserimento di un prodotto                      |\n"
				+ "| 4 - ricerca di un prodotto mediante codice univoco  |\n"
				+ "| 5 - stampa delle righe non riconosciute             |\n"
				+ "| 6 - stampa di tutti gli articoli                    |\n"
				+ "| 7 - terminare il programma                          |\n"
				+ "-------------------------------------------------------\n");

		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in)); // scrittura da console

		int inputNumb = 0;

		while (inputNumb < 7) {

			System.out.print("Inserisci il numero dell'azione da eseguire: ");

			try {
				inputNumb = Integer.parseInt(brInput.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Formato non valido, inserisci un numero tra quelli presenti.");
			} catch (IllegalArgumentException e) {
				System.out.println("Formato non valido, inserisci un numero tra quelli presenti.");
			}

			OperazioniMagazzino m = new OperazioniMagazzino();

			switch (inputNumb) {

			case 0:
				break;

			case 1:

				m.CalcoloValoreMagazzino();

				break;

			case 2:

				m.VenditaArticolo();

				break;

			case 3:

				m.InserimentoArticolo();

				break;

			case 4:

				m.RicercaArticolo();

				break;

			case 5:

				System.out.println("Elementi non riconosciuti: " + Utilita.elementiNonRiconosciuti);

				break;

			case 6:

				for (String name : Utilita.listaArticoli.keySet()) {
					String key = name;
					String value = Utilita.listaArticoli.get(name).toString();
					System.out.println(key + " - " + value);

				}

				break;

			case 7:
				ScritturaFile wf = new ScritturaFile();
				wf.ScritturaNelFile();

				break;
			}
		}
		System.out.println("\n\nGrazie per aver usato il nostro programma. ");

	}
}
