package a01b.e1;

public class GridFactoryImpl implements GridFactory {

	@Override
	public <E> Grid<E> create(int rows, int cols) {
		return new GridImpl<E>(rows, cols);
	}

}
