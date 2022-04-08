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
            "List Scheduler with priority ordering",
            new ListScheduler(OrderingStrategy.PRIORITY),
            taskQueue
        );

        runHomework(
            "List Scheduler with longest-runtime-first ordering",
            new ListScheduler(OrderingStrategy.LONGEST_RUNTIME_FIRST),
            taskQueue
        );
    }

    private static void runHomework(String headline, ListScheduler listScheduler, TaskQueue taskQueue) {
        System.out.println("\n---- " + headline + " -----\n");
        for (int numberOfResoures : new int[] {2, 4, 8}) {
            Schedule schedule = listScheduler.schedule(taskQueue, numberOfResoures);
            System.out.println(numberOfResoures + " resources");
            System.out.println(schedule.print());
        }
    }
}
