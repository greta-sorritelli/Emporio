public enum Misura {
	U('U'), //unità
	K('K'), //kilo
	L('L'), //litro
	M('M'); //metro

	private char y;

	Misura(char y) {
		this.y = y;
	}

    public char getMis() {
		return y;
	}
	
}
