package a01a.e2;

public interface Logic {
	
	public static enum Hits{
		BOAT,
		VICTORY,
		MISS,
		INVALID,
		DEFEAT
	}
	
	public Hits tile(Pair<Integer, Integer> coords);

}
