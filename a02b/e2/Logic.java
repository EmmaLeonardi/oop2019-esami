package a02b.e2;

import java.util.Set;

public interface Logic {
	
	public void addX(Pair<Integer,Integer> coords);
	
	public Set<Pair<Integer,Integer>> Xcoords();
	
	public void next();

}
