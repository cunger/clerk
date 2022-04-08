package clerk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListSchedulingTest {

    private final ListScheduler scheduler = new ListScheduler(OrderingStrategy.PRIORITY);

    // Task queue will be set for each test.
    private TaskQueue tasks;

    @Test
    @DisplayName("Empty task queue leads to empty schedule")
    void emptyTaskQueue() {
        tasks = new TaskQueue();

        Schedule schedule = scheduler.schedule(tasks, 2);

        assertThat(schedule.taskPlanForResource(1)).isEmpty();
        assertThat(schedule.taskPlanForResource(2)).isEmpty();
        assertThat(schedule.totalRuntime()).isEqualTo(0);
    }

    @Test
    @DisplayName("Next task is assigned to first available resource")
    void singletonTaskQueue() {
        tasks = new TaskQueue(1);

        Schedule schedule = scheduler.schedule(tasks, 2);

        assertThat(schedule.taskPlanForResource(1)).containsExactly(1);
        assertThat(schedule.taskPlanForResource(2)).isEmpty();
        assertThat(schedule.totalRuntime()).isEqualTo(1);
    }

    @Test
    void parallelTaskAssignments() {
        tasks = new TaskQueue(5, 5, 1, 1);

        Schedule schedule = scheduler.schedule(tasks, 2);

        assertThat(schedule.taskPlanForResource(1)).containsExactly(1, 3);
        assertThat(schedule.taskPlanForResource(2)).containsExactly(2, 4);
        assertThat(schedule.totalRuntime()).isEqualTo(6);
    }

    @Test
    void overlappingTaskAssignmentsWithIdleTime() {
        tasks = new TaskQueue(5, 1, 1, 5);

        Schedule schedule = scheduler.schedule(tasks, 2);

        assertThat(schedule.taskPlanForResource(1)).containsExactly(1);
        assertThat(schedule.taskPlanForResource(2)).containsExactly(2, 3, 4);
        assertThat(schedule.totalRuntime()).isEqualTo(7);
    }

    @Test
    @DisplayName("Scheduling with longest-runtime-first ordering")
    void lptListScheduling() {
        ListScheduler lptScheduler = new ListScheduler(OrderingStrategy.LONGEST_RUNTIME_FIRST);

        assertThat(
            lptScheduler
                .schedule(new TaskQueue(5, 1, 2, 5, 4), 2)
                .totalRuntime()
        ).isEqualTo(
            scheduler
                .schedule(new TaskQueue(5, 5, 4, 2, 1), 2)
                .totalRuntime()
        );
    }
}
