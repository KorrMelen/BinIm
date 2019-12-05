import java.util.LinkedHashSet;
import java.util.Set;

public class Path {
	private Set<Arcs> path;
	private Set<Integer> sommets;
	private int flotMin;
	
	public Path(Arcs firstArc) {
		this.path = new LinkedHashSet<Arcs>(); //Permet à la fois de ne pas avoir de doublon et d'avoir les acrs triés par ordre d'arrivé
		this.sommets = new LinkedHashSet<Integer>(); //idem pour les sommets
		this.path.add(firstArc);
		this.sommets.add(0); //indice de la source
		this.sommets.add(firstArc.getSommetDestination().getPixelNumberl()); //On ajoute l'indice de la destination de l'arc
		this.flotMin = firstArc.getCapacite()-firstArc.getFlot(); //Initialisation du minimum du chemin
	}

	public Set<Arcs> getPath() {
		return this.path;
	}

	public int getFlotMin() {
		return this.flotMin;
	}
	
	public Set<Integer> getSommet(){
		return this.sommets;
	}

	public void setFlotMin(int flotMin) {
		this.flotMin = Math.min(this.flotMin, flotMin);
	}
	
	private boolean addsommet(int s) { // Retoure true si on peut ajouter le sommet, false sinon
		return this.sommets.add(s);
	}
	
	public void removeSommet(int s) {
		this.sommets.remove(s);
	}
	
	public boolean addPath(Arcs arc) { //Avant d'ajouter un arc, on verifie qu'on peut ajouter le sommet de destiantion
		if(this.addsommet(arc.getSommetDestination().getPixelNumberl()))
			return (this.path.add(arc)); //Retroune true si on à réussie à ajouter l'arc
		else return false;
	}
	
	public void removePath(Arcs arc) {
		this.removeSommet(arc.getSommetDestination().getPixelNumberl());
		this.path.remove(arc);
	}
}
