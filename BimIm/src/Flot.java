public class Flot {
	private int capacite;
	private int flot;
	private int antiflot;
	private Flot next;
	private Pixel voisin;
	
	public Flot(int capacite, Pixel p) {
		this.capacite = capacite;
		this.flot = 0;
		this.antiflot = 0;
		this.next = null;
		this.voisin = p;
	}

	public int getCapacite() {
		return capacite;
	}

	public int getFlot() {
		return flot;
	}

	public int getAntiflot() {
		return antiflot;
	}
	
	public Flot getNext() {
		return this.next;
	}
	
	public Pixel getVoisin() {
		return this.voisin;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public void setFlot(int flot) {
		this.flot = flot;
		this.setAntiflot(-this.flot);
	}

	public void setAntiflot(int antiflot) {
		this.antiflot = antiflot;
	}
	
	public void setNext(Flot f) {
		this.next = f;
	}
}
