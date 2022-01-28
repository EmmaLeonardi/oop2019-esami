package a02a.e1;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class WorkflowsFactoryImpl implements WorkflowsFactory {

	@Override
	public <T> Workflow<T> singleTask(T task) {
		return tasksSequence(List.of(task));
	}

	@Override
	public <T> Workflow<T> tasksSequence(List<T> tasks) {
		return new WorkflowImpl<T>(tasks);
	}

	@Override
	public <T> Workflow<T> tasksJoin(Set<T> initialTasks, T finalTask) {
		List<T> list=new ArrayList<>(initialTasks);
		list.add(finalTask);
		return tasksSequence(list);
	}

	@Override
	public <T> Workflow<T> tasksFork(T initialTask, Set<T> finalTasks) {
		List<T> list=new ArrayList<>();
		list.add(initialTask);
		list.addAll(finalTasks);
		return tasksSequence(list);
	}

	@Override
	public <T> Workflow<T> concat(Workflow<T> first, Workflow<T> second) {
		// TODO Auto-generated method stub
		return null;
	}

}
