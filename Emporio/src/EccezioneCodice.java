public class EccezioneCodice extends Exception {
	
   private static final long serialVersionUID = 1L;

	public EccezioneCodice(String codice) {
		super("Codice errato");

   }
}