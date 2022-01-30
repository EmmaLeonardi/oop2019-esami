package a02b.e1;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamsFactoryImpl implements ExamsFactory {

	@Override
	public CourseExam<SimpleExamActivities> simpleExam() {
		return fromMap(Map.of(
				SimpleExamActivities.WRITTEN, Set.of(), 
				SimpleExamActivities.ORAL,Set.of(SimpleExamActivities.WRITTEN),
				SimpleExamActivities.REGISTER,Set.of(SimpleExamActivities.ORAL)));
	}

	@Override
	public CourseExam<OOPExamActivities> simpleOopExam() {
		return fromMap(Map.of(
				OOPExamActivities.LAB_REGISTER, Set.of(),
				OOPExamActivities.LAB_EXAM, Set.of(OOPExamActivities.LAB_REGISTER),
				OOPExamActivities.PROJ_PROPOSE, Set.of(),
				OOPExamActivities.PROJ_SUBMIT, Set.of(OOPExamActivities.PROJ_PROPOSE),
				OOPExamActivities.PROJ_EXAM, Set.of(OOPExamActivities.PROJ_SUBMIT),
				OOPExamActivities.FINAL_EVALUATION, Set.of(OOPExamActivities.PROJ_EXAM, OOPExamActivities.LAB_EXAM)));
	}

	@Override
	public CourseExam<OOPExamActivities> fullOopExam() {
		return fromMap(Map.of(
				OOPExamActivities.STUDY,Set.of(),
				OOPExamActivities.LAB_REGISTER, Set.of(OOPExamActivities.STUDY),
				OOPExamActivities.LAB_EXAM,Set.of(OOPExamActivities.LAB_REGISTER),
				OOPExamActivities.PROJ_PROPOSE, Set.of(OOPExamActivities.STUDY),
				OOPExamActivities.PROJ_SUBMIT, Set.of(OOPExamActivities.PROJ_PROPOSE),
				OOPExamActivities.CSHARP_TASK, Set.of(OOPExamActivities.PROJ_SUBMIT),
				OOPExamActivities.PROJ_EXAM,Set.of(OOPExamActivities.PROJ_SUBMIT),
				OOPExamActivities.FINAL_EVALUATION,Set.of(OOPExamActivities.PROJ_EXAM, OOPExamActivities.LAB_EXAM,OOPExamActivities.CSHARP_TASK)));
	}

	private <T> CourseExam<T> fromMap(Map<T, Set<T>> map) {
		return new CourseExam<T>() {
			Set<T> completedTasks = new HashSet<>();

			@Override
			public Set<T> activities() {
				return Set.copyOf(map.keySet());
			}

			@Override
			public Set<T> getPendingActivities() {
				return map.entrySet().stream().filter(
						a -> completedTasks.containsAll(a.getValue()) && completedTasks.contains(a.getKey()) == false)
						.map(a -> a.getKey()).collect(Collectors.toSet());
			}

			@Override
			public void completed(T a) {
				completedTasks.add(a);
			}

			@Override
			public boolean examOver() {
				return completedTasks.containsAll(map.keySet());
			}
		};
	}

}
