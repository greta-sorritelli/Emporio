import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ScritturaFile {

	public void ScritturaNelFile() throws FileNotFoundException, ArrayIndexOutOfBoundsException {
		File txt = new File("EMPORIO.TXT");

		BufferedReader br;

		try {

			int presenza = 0;
			for (Map.Entry<String, Articolo> entry1 : Utilita.listaArticoli.entrySet()) {

				String code = entry1.getValue().getCodice();
				br = new BufferedReader(new FileReader(txt));
				String linea1 = br.readLine();

				while (linea1 != null) {

					List<String> linea = new ArrayList<String>();
					linea = Arrays.asList(linea1.split(";"));
					String cod = linea.get(4);

					if (cod.equals(code)) {
						presenza++;
					}
					linea1 = br.readLine();
				}

				if (presenza < 1) {
					ScritturaTXT(code);
				}
				br.close();
				presenza = 0;
			}

		} catch (IOException e) {
			System.out.println("Errore lettura file di testo.");
		}
	}

	private void ScritturaTXT(String code) {
		BufferedWriter bw;
		FileWriter fw;
		try {
			String lines = " ";
			String descrizione = " ";

			for (Map.Entry<String, Articolo> entry : Utilita.listaArticoli.entrySet()) {

				if (entry.getKey().equals(code)) {

					ArrayList<String> desc = entry.getValue().getDescrizione();

					for (int i = 0; i < desc.size(); i++) {
						descrizione = desc.get(i);
					}
					lines += "\n" + entry.getValue().getReparto() + ";" + entry.getValue().getUnita() + ";"
							+ entry.getValue().getQuantita() + ";" + entry.getValue().getPrezzoEuro() + ";"
							+ entry.getValue().getCodice() + ";" + entry.getValue().getConsumoWatt();

					if (descrizione != null && descrizione.length() > 0) {
						lines += ";" + descrizione + ";";
					}

					fw = new FileWriter("EMPORIO.TXT", true);
					bw = new BufferedWriter(fw);
					bw.write(lines);
					bw.close();
				}

			}
		} catch (IOException e) {
			System.out.println("Impossibile scrivere nel file!");
		}
		System.out.println("Azione completata!");
	}

}
