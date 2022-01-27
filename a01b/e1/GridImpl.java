package a01b.e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GridImpl<E> implements Grid<E> {

	private final Map<Pair<Integer, Integer>, Optional<E>> grid;
	private final int rows;
	private final int cols;

	public GridImpl(int rows, int cols) {
		grid = new HashMap<>();
		this.rows = rows;
		this.cols = cols;
		for (int i = 0; i < this.cols; i++) {
			for (int j = 0; j < this.rows; j++) {
				grid.put(new Pair<>(j, i), Optional.empty());
			}
		}

	}

	@Override
	public int getRows() {
		return this.rows;
	}

	@Override
	public int getColumns() {
		return this.cols;
	}

	@Override
	public E getValue(int row, int column) {
		return grid.get(new Pair<>(row, column)).orElse(null);
	}

	@Override
	public void setColumn(int column, E value) {
		for (int i = 0; i < this.rows; i++) {
			grid.put(new Pair<>(i, column), fillOptional(value));
		}
	}

	@Override
	public void setRow(int row, E value) {
		for (int i = 0; i < this.cols; i++) {
			grid.put(new Pair<>(row, i), fillOptional(value));
		}
	}

	@Override
	public void setBorder(E value) {
		// Il bordo è 0...
		// ...0
		// max ....
		// ... max

		for (int i = 0; i < this.rows; i++) {
			grid.put(new Pair<>(i, 0), fillOptional(value));
			grid.put(new Pair<>(i, this.cols - 1), fillOptional(value));
		}
		for (int i = 0; i < this.cols; i++) {
			grid.put(new Pair<>(0, i), fillOptional(value));
			grid.put(new Pair<>(this.rows - 1, i), fillOptional(value));
		}

	}

	@Override
	public void setDiagonal(E value) {
		final int max = this.cols < this.rows ? this.cols : this.rows;
		for (int i = 0; i < max; i++) {
			grid.put(new Pair<>(i, i), fillOptional(value));
		}

	}

	@Override
	public Iterator<Cell<E>> iterator(boolean onlyNonNull) {
		List<Cell<E>> mapIterator = new ArrayList<>();
		grid.forEach((p, o) -> mapIterator.add(new Cell<>(p.getX(), p.getY(), o.orElse(null))));
		mapIterator.sort((a,b)->{
			if(Integer.compare(a.getRow(), b.getRow())==0) {
				return Integer.compare(a.getColumn(),b.getColumn());
			}
			else {
				return Integer.compare(a.getRow(),b.getRow());
			}
		});
		if (onlyNonNull == false) {
			return mapIterator.iterator();
		} else {
			// Voglio solo quelli con valore
			return mapIterator.stream().filter(a -> a.getValue() != null).iterator();
		}

	}

	private Optional<E> fillOptional(E value) {
		if (value == null) {
			return Optional.empty();
		} else {
			return Optional.of(value);
		}
	}

}
