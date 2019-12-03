import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	
	public static Graph ConstructionReseau (String file) throws java.io.IOException {
		java.util.Scanner lecteur ;
		java.io.File fichier = new File(file);
		lecteur = new Scanner(fichier);
		int n = lecteur.nextInt();
		int m = lecteur.nextInt();
		
		Graph graph = new Graph(n,m);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Pixel p = new Pixel(i,j,i*n+j+1);
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
	
	
	private static Path chemin(Graph g, Path path) {
		Flot p = g.getFlots()[0];
		for(Flot p2:path.getPath()){
			p = p2;
		}
		p = g.getFlots()[p.getVoisin().getNumbPixel()];
		while(p != null) {
			if(p.getFlot() < p.getCapacite()) {
				if (p.getVoisin().getNumbPixel() == -1) {
					path.addPath(p);
					path.setFlotMin(Math.min(path.getFlotMin(),p.getCapacite()-p.getFlot()));
					return path;
				}
				if(path.addPath(p)){
					int flot = Math.min(path.getFlotMin(),p.getCapacite()-p.getFlot());
					Path path2 = chemin(g, path);
					int i = 0;
					for(int p2:path.getSommet()){
						i = p2;
					}
					if (i ==-1) {
						path.setFlotMin(Math.min(path.getFlotMin(),flot));
						return path2;
					}
					path.removePath(p);
				}
			}
			p = p.getNext();
		}
		return path;
	}
	
	public static int CalculFlotMax (Graph g) {
		int flot = 0;
		Flot f = g.getFlots()[0];
		while (f != null) {
			if (f.getFlot() >= f.getCapacite()) {
				f = f.getNext();
			}else{
				Path p = chemin(g, new Path(f));
				for(int p2:p.getSommet()){
				}
				if(p.getPath().size() != 1) {
					int minf = p.getFlotMin();
					flot += minf;
					for(Flot f2:p.getPath()){
						f2.setFlot(minf);
						if(f2.getVoisin().getNumbPixel() != -1) {
							Flot f3 = g.getFlots()[f2.getVoisin().getNumbPixel()];
							while(f3 != null) {
								if (f3.getVoisin().getNumbPixel() == f2.getSommet().getNumbPixel()) {
									f3.setFlot(-minf);
								}
								f3 = f3.getNext();
							}
							
						}
					}
				}else{
					f = f.getNext();
				}
			}
		}
		return flot;
	}
	
	
	public static void main(String[] arg) throws IOException {
//		System.out.println("nom du fichier dans le répertoir fichier?");
//		Scanner lectureKB = new Scanner(System.in);
//		String fichier = lectureKB.nextLine();
//		lectureKB.close();
		Graph graph = ConstructionReseau("fichier/test2"/*+fichier*/);
		System.out.println(CalculFlotMax(graph));
	}
}
