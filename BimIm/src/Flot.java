public class Flot {
	private int capacite;
	private int flot;
	private Flot next;
	private Pixel sommet;
	private Pixel voisin;
	
	public Flot(int capacite, Pixel s, Pixel p) {
		this.capacite = capacite;
		this.flot = 0;
		this.next = null;
		this.sommet = s;
		this.voisin = p;
	}

	public int getCapacite() {
		return capacite;
	}

	public int getFlot() {
		return flot;
	}
	
	public Flot getNext() {
		return this.next;
	}
	
	public Pixel getSommet() {
		return this.sommet;
	}
	
	public Pixel getVoisin() {
		return this.voisin;
	}

	public void setFlot(int flot) {
		this.flot += flot;
	}
	
	public void setNext(Flot f) {
		this.next = f;
	}
}
