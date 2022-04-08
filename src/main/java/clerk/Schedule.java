package clerk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {

    Map<Integer, ResourceSchedule> resourceSchedules;

    public Schedule(int numberOfResources) {
        resourceSchedules = new HashMap<>();
        for (int id = 1; id <= numberOfResources; id++) {
            resourceSchedules.put(id, new ResourceSchedule(id));
        }
    }

    public List<Integer> taskPlanForResource(int id) {
        return resourceSchedules.get(id).tasks();
    }

    public void assign(Task task, int resourceId) {
        resourceSchedules.get(resourceId).schedule(task);
    }

    public int firstAvailableResource() {
        return resourceSchedules.values().stream()
            .min(Comparator.comparingInt(ResourceSchedule::totalRuntime))
            .get()
            .resourceId();
    }

    public int totalRuntime() {
        return resourceSchedules.values().stream()
            .max(Comparator.comparingInt(ResourceSchedule::totalRuntime))
            .get()
            .totalRuntime();
    }

    public String print() {
        String str = "";

        str += "--------------------\n";
        str += "Total runtime: " + totalRuntime() + "\n";
        str += "Resource schedules:\n";
        for (ResourceSchedule rs : resourceSchedules.values()) {
            str += "  " + rs.resourceId() + ": " + rs.tasks() + " (" + rs.totalRuntime() + ")\n";
        }
        str += "--------------------\n";

        return str;
    }
}
