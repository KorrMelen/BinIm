public class Pixel {
	private int coordi;
	private int coordj;
	private int a;
	private int b;
	private int h;
	private int e;
	private char set;
	
	public Pixel(int coordi, int coordj) {
		this.coordi = coordi;
		this.coordj = coordj;
		this.h = 0;
		this.e = 0;
	}
	
	public void setA(int a) {
		this.a = a;
		this.e = a;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setE(int e) {
		this.e = e;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setSet(char set) {
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

	public int getH() {
		return h;
	}

	public int getE() {
		return e;
	}
	
	public char getSet() {
		return set;
	}
	
}
