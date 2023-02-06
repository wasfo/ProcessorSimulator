import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Simulator {
     private final Task []tasks;
     private final int numberOfProcessors;
     private int numberOfCycles;
     private final Processor [] processors;
     private  Clock clock;
     public final Scheduler scheduler;
     private int taskIndex;
   //  private boolean isAnyProcessorAvailable;
     private List<Processor> availableProcessors;

     public Simulator(int numberOfProcessors, int numberOfCycles, Task []tasks) {
          this.numberOfProcessors = numberOfProcessors;
          this.numberOfCycles = numberOfCycles;
          this.tasks = tasks;
          taskIndex = 0;
     //     isAnyProcessorAvailable = true;
          availableProcessors = new LinkedList<>();

          clock = new Clock(1);
          scheduler = new Scheduler();
          processors = new Processor[numberOfProcessors];
          for (int i = 0; i < numberOfProcessors; i++) {
               processors[i] = new Processor(true, i + 1);
               availableProcessors.add(processors[i]);
          }
     }


     /**
          we call function start simulating:
          1- this function moves with time 1 cycle each time (1 second each time) and updates our clock.
          2- we take all the tasks which were created at the current time. (Let's call these tasks CURRENT_TASKS).
          3- we send CURRENT_TASKS to class scheduler which is responsible to assign them to the proccesors.
          4- some Y tasks from CURRENT_TASKS can't be assigned to procesors (have to wait). so the scheduler must return a Y tasks that should wait.
          5- we add the Y tasks to the simulator QUEUE.
      */
     public void start() throws InterruptedException {
          while (--numberOfCycles > -1) {
               System.out.println("Current Cycle : " + clock.getCurrentCycle());
               System.out.println("Current Cycle tasks: ");
               while (taskIndex < tasks.length && tasks[taskIndex].getCreationTime() == clock.getCurrentCycle()) {
                    System.out.println(tasks[taskIndex]);
                    scheduler.addTask(tasks[taskIndex++]);
               }
               System.out.println("/");

               if (!scheduler.isQueueEmpty() && !availableProcessors.isEmpty()) {
                    scheduler.scheduleTasks(availableProcessors);
               }

               nextCycle();
               System.out.println();
               System.out.println();
          }
     }

     private void nextCycle() throws InterruptedException {
          clock.nextCycle();
          for (int i = 0; i < numberOfProcessors; i++) {
               processors[i].update();
               if (processors[i].isAvailable()) {
                    availableProcessors.add(processors[i]);
               }
          }
     }
}
