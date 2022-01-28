package a02a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkflowImpl<T> implements Workflow<T> {

	private final List<T> listAll;
	private final List<T> listTasksToFinish;

	public WorkflowImpl(List<T> tasks) {
		this.listAll = new ArrayList<>(tasks);
		this.listTasksToFinish = new ArrayList<>(tasks);
	}

	@Override
	public Set<T> getTasks() {
		return new HashSet<>(listAll);
	}

	@Override
	public Set<T> getNextTasksToDo() {
		Set<T> set = new HashSet<>();
		if (!listTasksToFinish.isEmpty()) {
			set.add(listTasksToFinish.get(0));
		}
		return set;
	}

	@Override
	public void doTask(T t) {
		if (listAll.contains(t)) {
			// Almeno è una task
			if (getNextTasksToDo().equals(Set.of(t))) {
				// A posto, è nelle task da finire, ed è la prima
				listTasksToFinish.remove(t);
				return;
			}
			throw new IllegalStateException(t + "is not the next task to do, it is " + getNextTasksToDo());
		}
		throw new IllegalArgumentException("Task isn't a task in this workflow");

	}

	@Override
	public boolean isCompleted() {
		return listTasksToFinish.isEmpty();
	}

}
