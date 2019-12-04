public class Graph {
	private Pixel pixels[];
	private Arcs arcs[];
	private int line;
	private int row;
	private Pixel source;
	private Pixel well;
	
	public Graph(int line, int row) {
		this.line = line;
		this.row = row;
		this.pixels = new Pixel [line*row+2];
		this.arcs = new Arcs [line*row+2];
		this.source = new Pixel(-1,-1,0);
		this.well = new Pixel(-1,-1,-1);
		this.pixels[0] = this.source;
		this.pixels[line*row+1] = this.well;
	}
	
	public int getLine() {
		return this.line;
	}

	public int getRow() {
		return this.row;
	}

	public Pixel[] getPixels() {
		return this.pixels;
	}

	public Arcs[] getArcs() {
		return this.arcs;
	}

	public void addPixel(Pixel pixel, int numbPixel) {
		this.pixels[numbPixel] = pixel;		
	}
	
	public void addProba (int numbPixel, int proba, boolean source) {
		if(source) {
			this.pixels[numbPixel].setProbaA(proba);
			Arcs arc = new Arcs(proba, this.source,this.pixels[numbPixel]);
			arc.setNext(this.arcs[0]);
			this.arcs[0] = arc;
		}else{
			this.pixels[numbPixel].setProbaB(proba);
			this.arcs[numbPixel] = new Arcs(proba, this.pixels[numbPixel], this.well);
		}
	}
	
	public void addPenalite(int penalite, int numbPixel1, int numbPixel2){
		Arcs arc12 = new Arcs(penalite, this.pixels[numbPixel1], this.pixels[numbPixel2]);
		arc12.setNext(this.arcs[numbPixel1]);
		this.arcs[numbPixel1] = arc12;
		Arcs arc21 = new Arcs(penalite, this.pixels[numbPixel2], this.pixels[numbPixel1]);
		arc21.setNext(this.arcs[numbPixel2]);
		this.arcs[numbPixel2] = arc21;
	}
}
