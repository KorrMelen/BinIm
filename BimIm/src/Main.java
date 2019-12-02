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
	
	private static boolean avancer (Graph g, int i) {
		while(i < g.getFlots().length-1) {
			Flot j = g.getFlots()[i];
			while(j != null) {
				Pixel p1 = g.getPixels()[i];
				if(p1.getE() > 0) {
					if(j.getCapacite() > 0) {
						Pixel p2 = j.getVoisin();
						if (p1.getH() == p2.getH() + 1) {
							int d = Math.min(p1.getE(), j.getCapacite());
							j.setFlot(j.getFlot()+d);
							p1.setE(p1.getE() - d);
							p2.setE(p2.getE()+d);
							return true;
						}
					}
				}
				j = j.getNext();
			}
			i++;
		}
		return false;
	}
	
	private static int elever(Graph g) {
		int i = 1;
		while(i < g.getFlots().length-1) {
			Pixel p1 = g.getPixels()[i];
			if(p1.getE() > 0) {
				Flot j = g.getFlots()[i];
				int min = Integer.MAX_VALUE;
				while(j!= null) {
					Pixel p2 = j.getVoisin();
					if (p1.getH() <= p2.getH()) {
						min = Math.min(min, p2.getH());
					}else{
						min = Integer.MAX_VALUE;
						break;
					}
					j = j.getNext();
				}
				if(min != Integer.MAX_VALUE) {
					p1.setH(1+min);
					return i;
				}
			}
			i++;
		}
		return -1;
	}
	
	public static void preflot (Graph g) {
		int i;
		boolean continu = true;
		while(continu) {
			i = 1;
			while(i <= g.getM()*g.getN()){
				if(avancer(g,i)){
					i = 1;
				}else{
					i++;
				}
			}
			int c = elever(g);
			System.out.println(c);
			if (c != -1) {
				avancer(g, c);
			}else{
				continu = false;
			}
		}
	}
	
	public static void main(String[] arg) throws IOException {
		System.out.println("nom du fichier dans le répertoir fichier?");
		Scanner lectureKB = new Scanner(System.in);
		String fichier = lectureKB.nextLine();
		Graph graph = makeGraph("fichier/"+fichier);
		preflot(graph);
		for(int i = 0; i < graph.getN(); i++) {
			for(int j = 0; j < graph.getN(); j++) {
				System.out.print(graph.getPixels()[i*graph.getN()+j].getA()+","+graph.getPixels()[i*graph.getN()+j].getB()+" ");
			}
			System.out.println("");
		}
		System.out.println(graph.getPixels()[graph.getM()*graph.getN()+1].getE());
	}
}
