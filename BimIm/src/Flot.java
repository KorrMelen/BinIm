public class Flot {
	private Pixel pixel;
	private int penalite;
	private int flot;
	private Flot suiv;
	
	public Flot(Pixel pixel, int penalite) {
		this.pixel = pixel;
		this.penalite = penalite;
		this.flot = 0;
		this.suiv = null;
	}

	public Pixel getPixel() {
		return pixel;
	}

	public int getPenalite() {
		return penalite;
	}

	public int getFlot() {
		return flot;
	}

	public Flot getSuiv() {
		return suiv;
	}

	public void setPenalite(int penalite) {
		this.penalite = penalite;
	}

	public void setFlot(int flot) {
		this.flot = flot;
	}

	public void addSuiv(Flot suiv) {
		if(this.suiv == null) {
			this.suiv = suiv;
		}else {
			this.suiv.addSuiv(suiv);
		}
	}
}
