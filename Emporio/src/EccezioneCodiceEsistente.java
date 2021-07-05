public class EccezioneCodiceEsistente extends Exception {

	private static final long serialVersionUID = 1L;

	public EccezioneCodiceEsistente() {
         System.out.println("Nessun codice inserito.");
	}
	
	public EccezioneCodiceEsistente (String codice) {
		super("Codice Esistente.");
		
	}
	
	public EccezioneCodiceEsistente(String codice, Throwable error) {
		super("Errore Codice Esistente.");
	}

}
