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
     public void start() throws InterruptedException {
          while (--numberOfCycles > -1) {
               while (taskIndex < tasks.length && tasks[taskIndex].getCreationTime() == clock.getCurrentCycle()) {
                    System.out.println(tasks[taskIndex]);
                    scheduler.addTask(tasks[taskIndex++]);
               }
               System.out.println("/");
               if (!scheduler.isQueueEmpty() && !availableProcessors.isEmpty()) {
                    scheduler.scheduleTasks(availableProcessors);
               }
               report();
               nextCycle();
          }
          report();
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
     public void report(){
          System.out.println("current cycle:" +  clock.getCurrentCycle());
          for (int i = 0; i < numberOfProcessors; i++) {
               System.out.println(processors[i]);
          }
          System.out.println("-------------");
          System.out.println("in queue: "+ scheduler.getQueue());
     }

}
