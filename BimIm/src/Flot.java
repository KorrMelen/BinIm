public class Flot {
	private int capacite;
	private int flot;
	private int antiflot;
	
	public Flot(int capacite) {
		this.capacite = capacite;
		this.flot = 0;
		this.antiflot = 0;
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

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public void setFlot(int flot) {
		this.flot = flot;
	}

	public void setAntiflot(int antiflot) {
		this.antiflot = antiflot;
	}
}
