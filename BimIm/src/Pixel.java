public class Pixel {
	private int coordi;
	private int coordj;
	private float a;
	private float b;
	private char set;
	public Pixel(int coordi, int coordj) {
		this.coordi = coordi;
		this.coordj = coordj;
	}
	
	public void setA(float a) {
		this.a = a;
	}

	public void setB(float b) {
		this.b = b;
	}

	public void setSet(char poney) {
		this.set = poney;
	}

	public int getCoordi() {
		return coordi;
	}

	public int getCoordj() {
		return coordj;
	}

	public float getA() {
		return a;
	}

	public float getB() {
		return b;
	}

	public char getSet() {
		return set;
	}
	
}
