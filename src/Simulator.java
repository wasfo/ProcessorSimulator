import java.sql.SQLOutput;
import java.util.*;

public class Simulator {
     private final Task []tasks;
     private final int numberOfProcessors;
     private int simulationLength;
  //   private final Processor [] processors;
     private  Clock clock;
     public final Scheduler scheduler;
     private int taskIndex;
     private Set<Processor> availableProcessors;
     private Set<Processor> busyProcessors;

     private Integer initiatedProcessors;
     public Simulator(int numberOfProcessors, int simulationLength, Task []tasks) {
          this.numberOfProcessors = numberOfProcessors;
          this.simulationLength = simulationLength;
          this.tasks = tasks;
          taskIndex = 0;
          availableProcessors = new HashSet<>();
          busyProcessors = new HashSet<>();
          clock = new Clock(1);
          scheduler = new Scheduler();
          initiatedProcessors = 0;
      //    processors = new Processor[numberOfProcessors];
//          for (int i = 0; i < numberOfProcessors; i++) {
//      //         processors[i] = new Processor(true, i + 1);
//               availableProcessors.add(new Processor(true, i + 1));
//          }
     }

    public void setSimulatorClockDuration(int duration){
          clock.setCycleDuration(duration);
     }
     public void start() throws InterruptedException {
          while (!endProgram()) {
               while (taskIndex < tasks.length && tasks[taskIndex].getCreationTime() == clock.getCurrentCycle()) {
                    scheduler.addTask(tasks[taskIndex++]);
               }
               if (!scheduler.isQueueEmpty()) {
                   while (initiatedProcessors < numberOfProcessors && availableProcessors.size() < scheduler.getQueue().size()) {
                       availableProcessors.add(new Processor(true, initiatedProcessors + 1));
                       initiatedProcessors += 1;
                   }

                   if (!availableProcessors.isEmpty()) {
                       scheduler.scheduleTasks(availableProcessors, busyProcessors);
                   }
               }
           //    System.out.println(taskIndex + " " + tasks.length + " " + scheduler.isQueueEmpty() + " " + availableProcessors.size() + " " + numberOfProcessors);
              report();
              nextCycle();


               for (int i = 0; i < 100000000;) {
                    i++;
               }
          }



         System.out.println(availableProcessors.size());
         System.out.println("execution time : " + clock.getCurrentCycle());
          System.out.println("Simulation is done");
     }
     public boolean endProgram(){

          return taskIndex == tasks.length && scheduler.isQueueEmpty() && busyProcessors.size() == 0;
     }
     private void nextCycle() throws InterruptedException {
          clock.nextCycle();
          List<Processor> newAvailableProcessors = new LinkedList<>();
          for (Processor processor : busyProcessors) {
               processor.update();
               if (processor.isAvailable()) {
                    availableProcessors.add(processor);
                    newAvailableProcessors.add(processor);
               }
          }
          busyProcessors.removeAll(newAvailableProcessors);
     }
     public void report(){
          System.out.println("current cycle:" +  clock.getCurrentCycle());
          for (Processor processor : busyProcessors) {
               System.out.println(processor);
          }
          System.out.println("Number of available processors: " + (availableProcessors.size() + numberOfProcessors - initiatedProcessors));
          System.out.println("-------------");
          System.out.println("in queue: "+ scheduler.getQueue());
     }
}
