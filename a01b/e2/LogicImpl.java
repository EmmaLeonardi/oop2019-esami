package a01b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {

	private final int size;
	private final Set<Pair<Integer, Integer>> minesPos;
	private int tilesOpened = 0;

	public LogicImpl(int size, int mines) {
		Random random = new Random();
		this.size = size;
		this.minesPos = new HashSet<>();
		while (minesPos.size() < mines) {
			minesPos.add(new Pair<>(random.nextInt(size), random.nextInt(size)));
		}
		System.out.println(minesPos.toString());
	}

	@Override
	public int nearbyMines(Pair<Integer, Integer> coords) {
		// - - -
		// - a -
		// - - -
		int nMines = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (minesPos.contains(new Pair<>(coords.getX() + i, coords.getY() + j))) {
					// Se non è la mia casella e non contiene una bomba, contatore ++
					nMines++;
				}
			}
		}
		return nMines;

	}

	@Override
	public Tile press(Pair<Integer, Integer> coords) {
		if (minesPos.contains(coords)) {
			return Tile.MINE;
		} else if (coords.getX() >= 0 && coords.getX() < this.size && coords.getY() >= 0 && coords.getY() < this.size) {
			this.tilesOpened++;
			return Tile.SAFE;
		} else {
			return Tile.INVALID;
		}
	}

	@Override
	public boolean hasWon() {
		// Hai vinto quando non ci sono altre caselle da premere oltre le bombe
		// Contatore caselle valide? ci sta, size^2-bombe è max
		return this.tilesOpened == (this.size * this.size) - minesPos.size();
	}

}
