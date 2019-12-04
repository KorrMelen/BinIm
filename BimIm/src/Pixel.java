public class Pixel {
	private int coordi;
	private int coordj;
	private int a;
	private int b;
	private int numbPixel;
	private boolean set;
	
	public Pixel(int coordi, int coordj, int numbPixel) {
		this.coordi = coordi;
		this.coordj = coordj;
		this.numbPixel = numbPixel;
		this.set = false;
	}
	
	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setSet(boolean set) {
		this.set = set;
	}

	public int getCoordi() {
		return coordi;
	}

	public int getCoordj() {
		return coordj;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}
	
	public int getNumbPixel() {
		return this.numbPixel;
	}

	public boolean getSet() {
		return set;
	}
	
}
