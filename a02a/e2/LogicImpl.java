package a02a.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicImpl implements Logic {

	private final int size;
	private int step = 1;// 1.2.3.4.3.2
	private List<Set<Pair<Integer, Integer>>> configList;
	private boolean ascending = true;
	// Al primo giro deve essere grande 4 la grid

	// Potrei generare nel costruttore la lista di configurazioni, in modo da
	// crearle una volta e poi scorrerle solo
	// LinkedList?
	// 1-2-3-4-5(3)-6(2)
	// Ogni volta che faccio uno step-> la configurazione diventa quella della
	// dimensione dispari minore
	// Config(1)->x nel centro

	public LogicImpl(int size) {
		this.size = size;
		this.configList = new ArrayList<>();
		buildConfig();
	}

	@Override
	public void next() {
		if (ascending == true && step < configList.size()) {
			step++;
			if (step == configList.size()) {
				ascending = false;
			}
		} else if (ascending == false && step > 1) {
			step--;
			if (step == 1) {
				ascending = true;
			}
		}
	}

	@Override
	public Set<Pair<Integer, Integer>> getCrossCells() {
		return Set.copyOf(this.configList.get(step - 1));
	}

	private void buildConfig() {

		final int middle = this.size / 2;
		for (int i = 1; i <= this.size; i = i + 2) {
			// faccio solo i dispari
			if (i == 1) {
				this.configList.add(new HashSet<Pair<Integer, Integer>>());
				this.configList.get(0).add(new Pair<>(middle, middle));
			} else {
				// La tabella a disposizione è almeno 3*3
				this.configList.add(new HashSet<Pair<Integer, Integer>>());
				int distance = (i / 2); // Il numero di celle distanti dal centro che vuoi
				List<Integer> values = List.of(-1, 0, 1);
				for (int j : values) {
					for (int k : values) {
						if (i != 0 || j != 0) {
							this.configList.get(this.configList.size() - 1).add(new Pair<>(j * distance + middle, k * distance + middle));
						} else {
							//System.out.println("Haha, sono il valore centrale, non mi mettere in tabella");
						}
					}
				}
				this.configList.get(this.configList.size() - 1).remove(new Pair<>(middle, middle));
			}
		}
	}

}
