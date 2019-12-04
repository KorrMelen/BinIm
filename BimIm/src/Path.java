import java.util.LinkedHashSet;
import java.util.Set;

public class Path {
	private Set<Arcs> path;
	private Set<Integer> sommets;
	private int flotMin;
	
	public Path(Arcs firstArc) {
		this.path = new LinkedHashSet<Arcs>();
		this.sommets = new LinkedHashSet<Integer>();
		this.path.add(firstArc);
		this.sommets.add(0);
		this.sommets.add(firstArc.getSommetDestination().getNumbPixel());
		this.flotMin = firstArc.getCapacite()-firstArc.getFlot();
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
	
	private boolean addsommet(int s) {
		return this.sommets.add(s);
	}
	
	public void removeSommet(int s) {
		this.sommets.remove(s);
	}
	
	public boolean addPath(Arcs arc) {
		if(this.addsommet(arc.getSommetDestination().getNumbPixel()))
			return (this.path.add(arc));
		else return false;
	}
	
	public void removePath(Arcs arc) {
		this.removeSommet(arc.getSommetDestination().getNumbPixel());
		this.path.remove(arc);
	}
}
