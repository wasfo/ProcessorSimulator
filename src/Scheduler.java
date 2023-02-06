import java.util.*;

public class Scheduler {
    private PriorityQueue<Task> queue;
    public Scheduler() {
        queue = new PriorityQueue<>(new TaskComparator());
    }
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
    public void addTask(Task task) {
        queue.add(task);
    }
    public void scheduleTasks(Processor []processors) {
        for (int i = 0; i < processors.length; i++) {
            if (queue.isEmpty()) break;
            if (processors[i].isAvailable()) {
                Task task = queue.poll();
                processors[i].executeTask(task);
                System.out.println(processors[i]);
            }
        }
    }

    class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task taskOne, Task taskTwo) {
            if (taskOne.getPriority() > taskTwo.getPriority()) return -1;
            if (taskOne.getPriority() == taskTwo.getPriority()) {
                if (taskOne.getExecutionTime() > taskTwo.getExecutionTime()) return -1;
                if (taskOne.getExecutionTime() == taskTwo.getExecutionTime()) return Math.random() < 0.5 ? 1 : -1;
                return 1;
            }
            return 1;
        }
    }
}
