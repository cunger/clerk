package clerk;

import java.util.Comparator;

public class ListScheduler {

    final private Comparator<Task> orderingStrategy;

    public ListScheduler(Comparator<Task> orderingStrategy) {
        this.orderingStrategy = orderingStrategy;
    }

    /**
     * Schedule each task for the resource that is available first.
     */
    public Schedule schedule(TaskQueue tasks, int numberOfResources) {
        tasks.orderBy(orderingStrategy);

        Schedule schedule = new Schedule(numberOfResources);
        tasks.forEach(task ->
            schedule.assign(task, schedule.firstAvailableResource())
        );

        return schedule;
    }
}
