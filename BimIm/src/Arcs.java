public class Arcs {
	private int capacite;
	private int flot;
	private Arcs nextArc;
	private Pixel sommetOrigin;
	private Pixel sommetDestination;
	
	public Arcs(int capacite, Pixel sommetOrigin, Pixel sommetDestination) {
		this.capacite = capacite;
		this.flot = 0;
		this.nextArc = null;
		this.sommetOrigin = sommetOrigin;
		this.sommetDestination = sommetDestination;
	}

	public int getCapacite() {
		return this.capacite;
	}

	public int getFlot() {
		return this.flot;
	}
	
	public Arcs getNextArc() {
		return this.nextArc;
	}
	
	public Pixel getSommetOrigin() {
		return this.sommetOrigin;
	}
	
	public Pixel getSommetDestination() {
		return this.sommetDestination;
	}

	public void setFlot(int flot) {
		this.flot += flot;
	}
	
	public void setNext(Arcs arc) {
		this.nextArc = arc;
	}
}
