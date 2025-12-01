package labs7;

import java.util.LinkedList;

public class TaskQueue {
    private static final LinkedList<Task> queue = new LinkedList<>();

    public static void addTask(Task task) {
        queue.addLast(task);
    }

    public static synchronized Task getTask() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.removeFirst();
    }
}
