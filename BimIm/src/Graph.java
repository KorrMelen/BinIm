public class Graph {
	private Pixel pixels [][];
	private Flot flots[];
	
	public Graph(int n, int m) {
		this.pixels = new Pixel [n][m];
		this.flots = new Flot [n*m];
	}
	
	public Pixel[][] getPixels() {
		return pixels;
	}

	public Flot[] getFlots() {
		return this.flots;
	}

	public void addPixel(Pixel pixel) {
		this.pixels[pixel.getCoordi()][pixel.getCoordj()] = pixel;
		
	}
	
	public void addProba (int i, int j, float proba, boolean a) {
		if(a)this.pixels[i][j].setA(proba);
		else this.pixels[i][j].setB(proba);
	}
	
	public void addPena(int pena, int coori1, int coorj1, int coori2, int coorj2) {
		Pixel p1 = pixels[coori1][coorj1];
		Pixel p2 = pixels[coori2][coorj2];
		Flot f2 = new Flot(p2, pena);
		Flot f1 =new Flot(p1, pena);
		if (this.flots[coori1*this.pixels[0].length+coorj1] != null){
			this.flots[coori1*this.pixels[0].length+coorj1].addSuiv(f2);
		}else{
			this.flots[coori1*this.pixels[0].length+coorj1] = f2;
		}
		
		if (this.flots[coori2*this.pixels[0].length+coorj2] != null){
			this.flots[coori2*this.pixels[0].length+coorj2].addSuiv(f1);
		}else{
			this.flots[coori2*this.pixels[0].length+coorj2] = f1;
		}
		
		
		
	}
}
