package clerk;

import java.util.Comparator;

public class OrderingStrategy {

    // Assuming that the tasks are already ordered by priority, there is no need to re-order.
    public final static Comparator<Task> PRIORITY = Comparator.comparingInt(Task::id);

    public final static Comparator<Task> LONGEST_RUNTIME_FIRST = Comparator.comparingInt(Task::runtime).reversed();
}
