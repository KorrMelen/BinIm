import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	
	public static Graph ConstructionReseau (String file) throws java.io.IOException {
		java.util.Scanner lecteur ;
		java.io.File fichier = new File(file);
		lecteur = new Scanner(fichier);
		int line = lecteur.nextInt();
		int row = lecteur.nextInt();
		
		Graph graph = new Graph(line,row);
		for(int i = 0; i < line; i++) {
			for(int j = 0; j < row; j++) {
				Pixel pixel = new Pixel(i,j,i*row+j+1);
				graph.addPixel(pixel,i*row+j+1);
				graph.addProba(i*row+j+1, lecteur.nextInt(), true);
			}
		}
		
		for(int i = 0; i < line; i++) {
			for(int j = 0; j < row; j++) {
				graph.addProba(i*row+j+1, lecteur.nextInt(), false);
			}
		}
		
		for(int i = 0; i < line; i++) {
			for(int j = 0; j < row-1; j++) {
				graph.addPenalite(lecteur.nextInt(),i*row+j+1,i*row+j+2);
			}
		}
		
		for(int i = 0; i < line-1; i++) {
			for(int j = 0; j < row; j++) {
				graph.addPenalite(lecteur.nextInt(),i*row+j+1,(i+1)*row+j+1);
			}
		}
		
		lecteur.close();
		return graph;
	}
	
	
	private static Path searchPath(Graph graph, Path path) {
		Arcs arcTest = graph.getArcs()[0];
		for(Arcs lastArc:path.getPath()){
			arcTest = lastArc;
		}
		arcTest = graph.getArcs()[arcTest.getSommetDestination().getNumbPixel()];
		while(arcTest != null) {
			if(arcTest.getFlot() < arcTest.getCapacite()) {
				if (arcTest.getSommetDestination().getNumbPixel() == -1) {
					path.addPath(arcTest);
					path.setFlotMin(Math.min(path.getFlotMin(),arcTest.getCapacite()-arcTest.getFlot()));
					return path;
				}
				if(path.addPath(arcTest)){
					int flot = Math.min(path.getFlotMin(),arcTest.getCapacite()-arcTest.getFlot());
					Path path2 = searchPath(graph, path);
					int i = 0;
					for(int lastSommet:path.getSommet()){
						i = lastSommet;
					}
					if (i ==-1) {
						path.setFlotMin(flot);
						return path2;
					}
					path.removePath(arcTest);
				}
			}
			arcTest = arcTest.getNextArc();
		}
		return path;
	}
	
	public static int CalculFlotMax (Graph graph) {
		int flotMax = 0;
		Arcs arc = graph.getArcs()[0];
		while (arc != null) {
			if (arc.getFlot() >= arc.getCapacite()){
				arc = arc.getNextArc();
			}else{
				Path path = searchPath(graph, new Path(arc));
				if(path.getPath().size() != 1) {
					int minf = path.getFlotMin();
					flotMax += minf;
					for(Arcs arcPath:path.getPath()){
						arcPath.setFlot(minf);
						if(arcPath.getSommetDestination().getNumbPixel() != -1) {
							Arcs arcDestination = graph.getArcs()[arcPath.getSommetDestination().getNumbPixel()];
							while(arcDestination != null) {
								if (arcDestination.getSommetDestination().getNumbPixel() == arcPath.getSommetOrigin().getNumbPixel()) {
									arcDestination.setFlot(-minf);
								}
								arcDestination = arcDestination.getNextArc();
							}
							
						}
					}
				}else{
					arc = arc.getNextArc();
				}
			}
		}
		return flotMax;
	}
	
	private static void profondeur(Graph graph, int sommet) {
		Arcs arc = graph.getArcs()[sommet];
		while(arc != null) {
			if (arc.getCapacite() - arc.getFlot() > 0 && !arc.getSommetDestination().getSetA()) {
				arc.getSommetDestination().setSetA(true);
				profondeur(graph,arc.getSommetDestination().getNumbPixel());
			}
			arc = arc.getNextArc();
		}
	}
	
	public static void CalculCoupeMin(Graph graph) {
		profondeur(graph,0);
	}
	
	public static void ResoudreBinIm () throws IOException {
		System.out.println("Nom du fichier dans le répertoir fichier (avec l'extension) ?");
		Scanner lectureKB = new Scanner(System.in);
		String fichier = lectureKB.nextLine();
		long debut = System.currentTimeMillis();
		
		Graph graph = ConstructionReseau("../fichier/"+fichier);
		int flotMax = CalculFlotMax(graph);
		System.out.println("Flot maximum = "+flotMax);
		CalculCoupeMin(graph);
		System.out.println("Temps d'éxécution des fonctions pour calculer la coupe : " + (System.currentTimeMillis() - debut) + "ms");
		
		Pixel[] pixels = graph.getPixels();
		int choix;
		do {
			System.out.println("Représentation des resultat en format graphique ou text (1 ou 2) ?");
			choix = lectureKB.nextInt();
		}while(choix != 1 && choix != 2);
		lectureKB.close();
		
		if(choix == 2) {
			System.out.println("--------------------------------------------");
			for (int i = 1; i < pixels.length-1; i++) {
				if (pixels[i].getSetA())
					System.out.println("Pixel de coordonné "+pixels[i].getCoordi()+","+pixels[i].getCoordj()+" : Ensemble A");
				else
					System.out.println("Pixel de coordonné "+pixels[i].getCoordi()+","+pixels[i].getCoordj()+" : Ensemble B");
			}
			System.out.println("--------------------------------------------");
		}else {
			System.out.println("--------------------------------------------");
			for(int i = 0; i < graph.getLine(); i++) {
				for(int j = 0; j < graph.getRow(); j++) {
					if (pixels[i*graph.getRow()+j+1].getSetA())
						System.out.print("A ");
					else
						System.out.print("  ");
				}
				System.out.println();
			}
			System.out.println("--------------------------------------------");
		}
	}
	
	
	public static void main(String[] arg) throws IOException {
		ResoudreBinIm();
	}
}
