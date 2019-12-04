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
		
		for(int i = 0; i < line; i++) {// Lecture de la matrice des a(ij)
			for(int j = 0; j < row; j++) {
				Pixel pixel = new Pixel(i,j,i*row+j+1);
				graph.addPixel(pixel,i*row+j+1);
				graph.addProba(i*row+j+1, lecteur.nextInt(), true);
			}
		}
		
		for(int i = 0; i < line; i++) { //Lecture de la matrice des b(ij)
			for(int j = 0; j < row; j++) {
				graph.addProba(i*row+j+1, lecteur.nextInt(), false);
			}
		}
		
		for(int i = 0; i < line; i++) { // Lecture de la matrice des pénalitées horizontales
			for(int j = 0; j < row-1; j++) {
				graph.addPenalite(lecteur.nextInt(),i*row+j+1,i*row+j+2);
			}
		}
		
		for(int i = 0; i < line-1; i++) { // Lecture de la matrice des pénalitées verticales
			for(int j = 0; j < row; j++) {
				graph.addPenalite(lecteur.nextInt(),i*row+j+1,(i+1)*row+j+1);
			}
		}
		
		lecteur.close();
		return graph;
	}
	
	
	private static Path searchPath(Graph graph, Path path) {
		//Arcs arcTest = graph.getArcs()[0];
		int pixel = 0;
		for(int lastPixel:path.getSommet()){ // on parcourt tous les sommets du chemin
			pixel = lastPixel; // On réccupère le dernier sommet
		}
		Arcs arcTest = graph.getArcs()[pixel]; // On regarde tous les arcs qui partent du dernier sommet
		while(arcTest != null) {
			if(arcTest.getFlot() < arcTest.getCapacite()) { // Si on peut empreunter cet arc
				if (arcTest.getSommetDestination().getNumbPixel() == -1) { //On regarde si la destination est le puit
					path.addPath(arcTest);
					path.setFlotMin(Math.min(path.getFlotMin(),arcTest.getCapacite()-arcTest.getFlot()));
					return path; //Si c'est le cas, on ajoute l'arc et on retourne le chemin
				}
				if(path.addPath(arcTest)){ // on ajoute l'arc au chemin
					int flot = Math.min(path.getFlotMin(),arcTest.getCapacite()-arcTest.getFlot());
					path = searchPath(graph, path); //On continue le chemin à partir de celui-ci
					int i = 0;
					for(int lastSommet:path.getSommet()){
						i = lastSommet; //On reccupère le dernier sommet
					}
					if (i == -1) { // on vérifie si ce dernier sommet est le puit (alors il existerait un chemin jusqu'au puit)
						path.setFlotMin(flot);
						return path;
					}
					path.removePath(arcTest); // Sinon, il s'agit d'un cul-de-sac et on retire l'arc du chemin
				}
			}
			arcTest = arcTest.getNextArc();
		}
		return path;
	}
	
	public static int CalculFlotMax (Graph graph) {
		int flotMax = 0;
		Arcs arc = graph.getArcs()[0]; //On prend le premier arc partant de la source
		while (arc != null) {
			if (arc.getFlot() >= arc.getCapacite()){ // On verifie si on peut emprunter cet arc
				arc = arc.getNextArc();
			}else{
				Path path = searchPath(graph, new Path(arc)); // On essaye de trouver un chemin entre la source et le puit
				if(path.getPath().size() != 1) { //Si on a plus d'un arc dans le chemin alors on à un chemin allant jusqu'au puit
					int minf = path.getFlotMin();
					flotMax += minf;
					for(Arcs arcPath:path.getPath()){
						arcPath.setFlot(minf);
						if(arcPath.getSommetDestination().getNumbPixel() != -1) { // On complete les arcs retours
							Arcs arcDestination = graph.getArcs()[arcPath.getSommetDestination().getNumbPixel()];
							while(arcDestination != null) {
								if (arcDestination.getSommetDestination().getNumbPixel() == arcPath.getSommetOrigin().getNumbPixel()) {
									arcDestination.setFlot(-minf);
								}
								arcDestination = arcDestination.getNextArc();
							}
							
						}
					}
				}else{ //Si on ne trouve pas de chemin, on passe à l'arc suivant
					arc = arc.getNextArc();
				}
			}
		}
		return flotMax;
	}
	
	private static void profondeur(Graph graph, int sommet) {
		Arcs arc = graph.getArcs()[sommet];
		while(arc != null) {
			if (arc.getCapacite() - arc.getFlot() > 0 && !arc.getSommetDestination().getSetA()) { // si on peut acceder au sommet suivant et que celui-ci n'a pas encore été visité, alors on le visite
				arc.getSommetDestination().setSetA(true);
				profondeur(graph,arc.getSommetDestination().getNumbPixel());
			}
			arc = arc.getNextArc();
		}
	}
	
	public static void CalculCoupeMin(Graph graph) {
		profondeur(graph,0);
	}
	
	public static Graph ResoudreBinIm (String file) throws IOException {
		long debut = System.currentTimeMillis();
		Graph graph = ConstructionReseau("../fichier/"+file);
		int flotMax = CalculFlotMax(graph);
		System.out.println("Flot maximum = "+flotMax);
		CalculCoupeMin(graph);
		System.out.println("Temps d'execution des fonctions pour calculer la coupe : " + (System.currentTimeMillis() - debut) + "ms");
		return graph;
	}
	
	
	public static void main(String[] arg) throws IOException {
		System.out.println("Nom du fichier dans le repertoire fichier (avec l'extension) ?");
		Scanner lectureKB = new Scanner(System.in);
		String file = lectureKB.nextLine();
		Graph graph = ResoudreBinIm(file);
		
		
		//AFFICHAGE DES RESULTATS//
		
		Pixel[] pixels = graph.getPixels();
		int choix;
		do {
			System.out.println("Representation des resultat en format graphique ou texte (1 ou 2) ?");
			choix = lectureKB.nextInt();
		}while(choix != 1 && choix != 2);
		lectureKB.close();
		
		
		//CHOIX 2
		
		if(choix == 2) {
			System.out.println("--------------------------------------------");
			for (int i = 1; i < pixels.length-1; i++) {
				if (pixels[i].getSetA())
					System.out.println("Pixel de coordonné "+pixels[i].getCoordi()+","+pixels[i].getCoordj()+" : Ensemble A");
				else
					System.out.println("Pixel de coordonné "+pixels[i].getCoordi()+","+pixels[i].getCoordj()+" : Ensemble B");
			}
			System.out.println("--------------------------------------------");
			System.out.println("");
		}else {
			
			//CHOIX 1
			
			for (int i = 0 ; i < graph.getRow(); i++) {
				System.out.print("--");
			}
			System.out.println("--");
			for(int i = 0; i < graph.getLine(); i++) {
				System.out.print("|");
				for(int j = 0; j < graph.getRow(); j++) {
					if (pixels[i*graph.getRow()+j+1].getSetA())
						System.out.print("A ");
					else
						System.out.print("  ");
				}
				System.out.println("|");
			}
			for (int i = 0 ; i < graph.getRow(); i++) {
				System.out.print("--");
			}
			System.out.println("--");
		}
	}
}
