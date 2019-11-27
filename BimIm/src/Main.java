import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static Graph makeGraph (String file) throws java.io.IOException {
		java.util.Scanner lecteur ;
		java.io.File fichier = new File("src/test");
		lecteur = new Scanner(fichier);
		int n = lecteur.nextInt();
		int m = lecteur.nextInt();
		
		Graph graph = new Graph(n,m);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Pixel p = new Pixel(i,j);
				graph.addPixel(p);
				graph.addProba(i, j, lecteur.nextFloat(), true);
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				graph.addProba(i, j, lecteur.nextFloat(), false);
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m-1; j++) {
				graph.addPena(lecteur.nextInt(),i,j,i,j+1);
			}
		}
		
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < m; j++) {
				graph.addPena(lecteur.nextInt(),i,j,i+1,j);
			}
		}
		
		lecteur.close();
		return graph;
	}
	
	
	public static void main(String[] args) throws IOException {
		Graph graph = makeGraph("");
		for(int i = 0; i < graph.getPixels().length; i++) {
			for(int j = 0; j < graph.getPixels()[i].length; j++) {
				System.out.print(graph.getPixels()[i][j].getA()+","+graph.getPixels()[i][j].getB()+" ");
			}
			System.out.println("");
		}
	}
}
