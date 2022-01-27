package a01b.e2;

public interface Logic {
	
	public static enum Tile{
		SAFE,
		MINE,
		INVALID
	};
	
	public int nearbyMines(Pair<Integer, Integer> coords);
	
	public Tile press(Pair<Integer, Integer> coords);
	
	public boolean hasWon();

}
