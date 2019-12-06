public class Pixel {
	private int coordi;
	private int coordj;
	private int pixelNumber; //numéro du pixel (compris entre 1 et line*row)
	private boolean setA; //true si appartien à l'ensemble A, false sinon
	
	public Pixel(int coordi, int coordj, int numbPixel) {
		this.coordi = coordi;
		this.coordj = coordj;
		this.pixelNumber = numbPixel;
		this.setA = false;
	}

	public void setSetA(boolean set) {
		this.setA = set;
	}

	public int getCoordi() {
		return this.coordi;
	}

	public int getCoordj() {
		return this.coordj;
	}
	
	public int getPixelNumber() {
		return this.pixelNumber;
	}

	public boolean getSetA() {
		return this.setA;
	}
	
}
