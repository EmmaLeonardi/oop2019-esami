package a01a.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {

	private final Set<Pair<Integer, Integer>> boatCoords;
	private int moves = 0;
	private final int maxMoves;
	private final int size;
	private final Random random = new Random();

	public LogicImpl(int size, int boat, int moves) {
		this.maxMoves = moves;
		this.size = size;
		this.boatCoords = new HashSet<>();
		var boatStart = new Pair<>(random.nextInt(size - boat+1), random.nextInt(size - 1));
		for (int i = 0; i < boat; i++) {
			boatCoords.add(new Pair<>(boatStart.getX() + i, boatStart.getY()));
		}
		System.out.println(boatCoords.toString());
	}

	@Override
	public Hits tile(Pair<Integer, Integer> coords) {

		if (coords.getX() >= 0 && coords.getX() <= size - 1 && coords.getY() >= 0 && coords.getY() <= size - 1) {

			if (boatCoords.contains(coords)) {
				boatCoords.remove(coords);
				if (boatCoords.isEmpty()) {
					// Ho colpito l'ultima parte della barca
					return Hits.VICTORY;
				}
				// Ho colpito una parte della barca
				return Hits.BOAT;

			}
			// Non hai colpito una barca
			this.moves++;
			if(this.moves==this.maxMoves) {
				return Hits.DEFEAT;
			}
			return Hits.MISS;

		}
		return Hits.INVALID;
	}

}
