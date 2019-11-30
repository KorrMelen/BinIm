public class Graph {
	private Pixel pixels[];
	private Flot flots[][];
	private int n;
	private int m;
	private Pixel s;
	private Pixel t;
	
	public Graph(int n, int m) {
		this.n = n;
		this.m = m;
		this.pixels = new Pixel [n*m+2];
		this.flots = new Flot [n*m+2][n*m+2];
		this.s = new Pixel(-1,-1);
		this.s.setH(n*m+2);
		this.t = new Pixel(-1,-1);
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

	public Flot[][] getFlots() {
		return this.flots;
	}
	
	public void setFlots (int i, int j, int val) {
		this.flots[i][j].setFlot(this.flots[i][j].getFlot() + val);
	}
	
	public void setPixels (int i, int val) {
		this.pixels[i].setE(this.pixels[i].getE() + val);
	}

	public void addPixel(Pixel pixel, int numbPixel) {
		this.pixels[numbPixel] = pixel;		
	}
	
	public void addProba (int numbPixel, int proba, boolean a) {
		if(a) {
			this.pixels[numbPixel].setA(proba);
			this.flots[0][numbPixel] = new Flot(proba);
			this.flots[0][numbPixel].setFlot(proba);
			this.flots[0][numbPixel].setAntiflot(-proba);
			this.s.setE(this.s.getE()-proba);
		}else{
			this.pixels[numbPixel].setB(proba);
			this.flots[numbPixel][this.flots.length-1] = new Flot(proba);
		}
	}
	
	public void addPena(int pena, int numbPixel1, int numbPixel2){
		this.flots[numbPixel1][numbPixel2] = new Flot(pena);
		this.flots[numbPixel2][numbPixel1] = new Flot(pena);
	}
	
//	public void addPena(int pena, int coori1, int coorj1, int coori2, int coorj2) {
//		Pixel p1 = pixels[coori1][coorj1];
//		Pixel p2 = pixels[coori2][coorj2];
//		Flot f2 = new Flot(p2, pena);
//		Flot f1 =new Flot(p1, pena);
//		if (this.flots[coori1*this.pixels[0].length+coorj1+1] != null){
//			this.flots[coori1*this.pixels[0].length+coorj1+1].addSuiv(f2);
//		}else{
//			this.flots[coori1*this.pixels[0].length+coorj1+1] = f2;
//		}
//		
//		if (this.flots[coori2*this.pixels[0].length+coorj2+1] != null){
//			this.flots[coori2*this.pixels[0].length+coorj2+1].addSuiv(f1);
//		}else{
//			this.flots[coori2*this.pixels[0].length+coorj2+1] = f1;
//		}		
//	}
//	
//	public void addpuit() {
//		for (int i = 1; i < this.flots.length-1;i++) {
//			int flot = pixels[i/pixels.length][i%pixels[0].length].getB();
//			this.flots[i].addSuiv(new Flot(new Pixel(-1,-1),flot));
//		}
//	}
}
