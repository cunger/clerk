import clerk.ListScheduler;
import clerk.OrderingStrategy;
import clerk.Schedule;
import clerk.TaskQueue;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        TaskQueue taskQueue = TaskQueue.from(
            Main.class.getResourceAsStream("tasks.csv")
        );

        runHomework(
            "1.4 | List Scheduler with priority ordering",
            new ListScheduler(OrderingStrategy.PRIORITY),
            taskQueue,
            new int[] {2, 4, 8}
        );

        runHomework(
            "1.4 | List Scheduler with longest-runtime-first ordering",
            new ListScheduler(OrderingStrategy.LONGEST_RUNTIME_FIRST),
            taskQueue,
            new int[] {2, 4, 8}
        );

        TaskQueue imageProcessingTasks = new TaskQueue(1, 2, 2, 10, 6, 6, 1);

        runHomework(
            "1.5 | List Scheduler with longest-runtime-first ordering",
            new ListScheduler(OrderingStrategy.LONGEST_RUNTIME_FIRST),
            imageProcessingTasks,
            new int[] {1, 4}
        );
    }

    private static void runHomework(String headline, ListScheduler listScheduler, TaskQueue taskQueue, int[] resourcePools) {
        System.out.println("\n---- " + headline + " -----\n");
        System.out.println("Tasks with runtime: ");
        taskQueue.forEach(task -> System.out.println(" " + task.id() + " (" + task.runtime() + ")"));
        System.out.println("");
        for (int numberOfResoures : resourcePools) {
            Schedule schedule = listScheduler.schedule(taskQueue, numberOfResoures);
            System.out.println(numberOfResoures + " resources");
            System.out.println(schedule.print());
        }
    }
}
