import java.util.LinkedHashSet;
import java.util.Set;

public class Path {
	private Set<Flot> path;
	private Set<Integer> sommet;
	private int flotMin;
	
	public Path(Flot f) {
		this.path = new LinkedHashSet<Flot>();
		this.sommet = new LinkedHashSet<Integer>();
		this.path.add(f);
		this.sommet.add(0);
		this.sommet.add(f.getVoisin().getNumbPixel());
		this.flotMin = f.getCapacite()-f.getFlot();
	}

	public Set<Flot> getPath() {
		return path;
	}

	public int getFlotMin() {
		return flotMin;
	}
	
	public Set<Integer> getSommet(){
		return this.sommet;
	}

	public void setFlotMin(int flotMin) {
		this.flotMin = flotMin;
	}
	
	private boolean addsommet(int s) {
		return (this.sommet.add(s));
	}
	
	public void removeSommet(int s) {
		this.sommet.remove(s);
	}
	
	public boolean addPath(Flot p) {
		if(this.addsommet(p.getVoisin().getNumbPixel()))
			return (this.path.add(p));
		else return false;
	}
	
	public void removePath(Flot p) {
		this.removeSommet(p.getVoisin().getNumbPixel());
		this.path.remove(p);
	}
}
