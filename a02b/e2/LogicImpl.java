package a02b.e2;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LogicImpl implements Logic {

	static private enum Direction {

		DOWN(+1), UP(-1);

		private final int diff;

		private Direction(int diff) {
			this.diff = diff;
		}

	}

	// true if going down
	private final Map<Pair<Integer, Integer>, Direction> map;
	private final int size;

	public LogicImpl(int size) {
		this.map = new HashMap<>();
		this.size = size;
	}

	@Override
	public void addX(Pair<Integer, Integer> coords) {
		map.put(new Pair<>(coords.getX(), coords.getY()), Direction.DOWN);

	}

	@Override
	public Set<Pair<Integer, Integer>> Xcoords() {
		return Set.copyOf(map.keySet());
	}

	@Override
	public void next() {
		Map<Pair<Integer, Integer>, Direction> newEntries = new HashMap<>();
		map.entrySet().forEach(e -> {
			if (e.getKey().getY().equals(0)) {
				map.replace(e.getKey(), Direction.DOWN);
			}
			if (e.getKey().getY().equals(this.size - 1)) {
				map.replace(e.getKey(), Direction.UP);
			}
			newEntries.put(new Pair<>(e.getKey().getX(), e.getKey().getY() + e.getValue().diff), e.getValue());
			
		});
		this.map.clear();
		this.map.putAll(newEntries);
	}

}
