public class Pixel {
	private int coordi;
	private int coordj;
	//private int probaA;
	//private int probaB;
	private int numbPixel; //numéro du pixel (compris entre 1 et line*row)
	private boolean setA; //true si appartien à l'ensemble A, false sinon
	
	public Pixel(int coordi, int coordj, int numbPixel) {
		this.coordi = coordi;
		this.coordj = coordj;
		this.numbPixel = numbPixel;
		this.setA = false;
	}
	
//	public void setProbaA(int a) {
//		this.probaA = a;
//	}
//
//	public void setProbaB(int b) {
//		this.probaB = b;
//	}

	public void setSetA(boolean set) {
		this.setA = set;
	}

	public int getCoordi() {
		return this.coordi;
	}

	public int getCoordj() {
		return this.coordj;
	}

//	public int getProbaA() {
//		return this.probaA;
//	}
//
//	public int getProbaB() {
//		return this.probaB;
//	}
	
	public int getNumbPixel() {
		return this.numbPixel;
	}

	public boolean getSetA() {
		return this.setA;
	}
	
}
