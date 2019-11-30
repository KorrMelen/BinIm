import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	
	public static Graph makeGraph (String file) throws java.io.IOException {
		java.util.Scanner lecteur ;
		java.io.File fichier = new File(file);
		lecteur = new Scanner(fichier);
		int n = lecteur.nextInt();
		int m = lecteur.nextInt();
		
		Graph graph = new Graph(n,m);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Pixel p = new Pixel(i,j);
				graph.addPixel(p,i*n+j+1);
				graph.addProba(i*n+j+1, lecteur.nextInt(), true);
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				graph.addProba(i*n+j+1, lecteur.nextInt(), false);
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m-1; j++) {
				graph.addPena(lecteur.nextInt(),i*n+j+1,i*n+j+2);
			}
		}
		
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < m; j++) {
				graph.addPena(lecteur.nextInt(),i*n+j+1,(i+1)*n+j+1);
			}
		}
		
		lecteur.close();
		return graph;
	}
	
	private boolean avancer (Graph g, int i, int j) {
		if(g.getPixels()[i].getE() > 0) {
			if(g.getFlots()[i][j].getCapacite() > 0) {
				if (g.getPixels()[i].getH() == g.getPixels()[j].getH() + 1) {
					int d = Math.min(g.getPixels()[i].getE(), g.getFlots()[i][j].getCapacite());
					g.setFlots(i, j, d);
					g.setPixels(i, d);
					g.setPixels(j, d);
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		Graph graph = makeGraph("src/test");
		for(int i = 0; i < graph.getN(); i++) {
			for(int j = 0; j < graph.getN(); j++) {
				System.out.print(graph.getPixels()[i*graph.getN()+j].getA()+","+graph.getPixels()[i*graph.getN()+j].getB()+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		for(int i = 0; i < graph.getFlots().length;i++) {
			for(int j = 0; j< graph.getFlots().length;j++) {
				if(graph.getFlots()[i][j] != null)
				System.out.print(graph.getFlots()[i][j].getCapacite()+" ");
				else System.out.print("-1 ");
			}
			System.out.println("");
		}
	}
}
