package clerk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TaskQueue {

    private List<Task> tasks;

    public TaskQueue(int... runtimes) {
        tasks = new ArrayList<>();
        int i = 1;
        for (int runtime : runtimes) {
            tasks.add(new Task(i, runtime));
            i++;
        }
    }

    public void forEach(Consumer<Task> action) {
        tasks.forEach(action);
    }

    public void orderBy(Comparator<Task> taskComparator) {
        Collections.sort(tasks, taskComparator);
    }

    public static TaskQueue from(InputStream file) throws IOException {
        TaskQueue queue = new TaskQueue();

        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String line = "";
        while ((line = reader.readLine()) != null) {
            try {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                int runtime = Integer.parseInt(parts[1].trim());
                queue.add(id, runtime);
            } catch (Exception e) {
                System.out.println("Skipping line (" + e.getMessage() + "): " + line);
            }
        }

        return queue;
    }

    private void add(int id, int runtime) {
        tasks.add(new Task(id, runtime));
    }
}
