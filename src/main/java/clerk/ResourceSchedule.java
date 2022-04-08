package clerk;

import java.util.ArrayList;
import java.util.List;

public class ResourceSchedule {

    private int resourceId;
    private List<Integer> tasks;
    private int totalRuntime;

    public ResourceSchedule(int id) {
        resourceId = id;
        tasks = new ArrayList<>();
        totalRuntime = 0;
    }

    public int resourceId() {
        return resourceId;
    }

    public int totalRuntime() {
        return totalRuntime;
    }

    public List<Integer> tasks() {
        return tasks;
    }

    public void schedule(Task task) {
        tasks.add(task.id());
        totalRuntime += task.runtime();
    }
}
