public class Graph {
	private Pixel pixels[];
	private Flot arc[];
	private int n;
	private int m;
	private Pixel s;
	private Pixel t;
	
	public Graph(int n, int m) {
		this.n = n;
		this.m = m;
		this.pixels = new Pixel [n*m+2];
		this.arc = new Flot [n*m+2];
		this.s = new Pixel(-1,-1,0);
		this.t = new Pixel(-1,-1,-1);
		this.pixels[0] = this.s;
		this.pixels[n*m+1] = this.t;
	}
	
	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public Pixel[] getPixels() {
		return pixels;
	}

	public Flot[] getFlots() {
		return this.arc;
	}

	public void addPixel(Pixel pixel, int numbPixel) {
		this.pixels[numbPixel] = pixel;		
	}
	
	public void addProba (int numbPixel, int proba, boolean a) {
		if(a) {
			this.pixels[numbPixel].setA(proba);
			Flot f = new Flot(proba, this.s,this.pixels[numbPixel]);
			f.setNext(this.arc[0]);
			this.arc[0] = f;
		}else{
			this.pixels[numbPixel].setB(proba);
			this.arc[numbPixel] = new Flot(proba, this.pixels[numbPixel], this.t);
		}
	}
	
	public void addPena(int pena, int numbPixel1, int numbPixel2){
		Flot f1 = new Flot(pena, this.pixels[numbPixel1], this.pixels[numbPixel2]);
		f1.setNext(this.arc[numbPixel1]);
		this.arc[numbPixel1] = f1;
		Flot f2 = new Flot(pena, this.pixels[numbPixel2], this.pixels[numbPixel1]);
		f2.setNext(this.arc[numbPixel2]);
		this.arc[numbPixel2] = f2;
	}
}
