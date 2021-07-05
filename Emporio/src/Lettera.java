public enum Lettera {
	E('E'), //Elettricità
	F('F'), //Ferramenta
	L('L'), //Legname
	C('C'), //Casa
	S('S'); //Sport e tempo libero
	
	private char x;
	
	Lettera(char x) {
		this.x = x ;
		
	}
	
	public char getLet() {
		return x ;
	}

}
