import java.util.*;
public class Simulator {
     private final Task []tasks;
     private final int numberOfProcessors;
     private int simulationLength;
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
     }

    public void setSimulatorClockDuration(int duration){
          clock.setCycleDuration(duration);
     }
     public void start() throws InterruptedException {
          while (--simulationLength > -1) {
               while (taskIndex < tasks.length && tasks[taskIndex].getCreationTime() == clock.getCurrentCycle()) {
                    scheduler.addTask(tasks[taskIndex++]);
               }

              if (!scheduler.isQueueEmpty()) {
                  initializeProcessors();
                  scheduleTasks();
              }
              report();
              nextCycle();
          }
          System.out.println("Simulation is done");
     }
    private void initializeProcessors() {
            while (initiatedProcessors < numberOfProcessors && availableProcessors.size() < scheduler.getQueue().size()) {
                availableProcessors.add(new Processor(true, initiatedProcessors + 1));
                initiatedProcessors += 1;
            }
    }
    private void scheduleTasks() {
        if (!availableProcessors.isEmpty()) {
            scheduler.scheduleTasks(availableProcessors, busyProcessors);
        }
    }

    public boolean endProgram(){
          return taskIndex == tasks.length && scheduler.isQueueEmpty() && busyProcessors.size() == 0;
     }
     private void nextCycle() throws InterruptedException {
          clock.nextCycle();
          for (Processor processor : busyProcessors) {
               processor.update();
               if (processor.isAvailable()) {
                    availableProcessors.add(processor);
               }
          }
          busyProcessors.removeAll(availableProcessors);
     }
     public void report(){
          System.out.println("current cycle:" +  clock.getCurrentCycle());
          System.out.println("in queue: "+ scheduler.getQueue());
          for (Processor processor : busyProcessors) {
               System.out.println(processor);
          }
          System.out.println("Number of available processors: " + (availableProcessors.size() + numberOfProcessors - initiatedProcessors));
          System.out.println("------------------------------------------------------");
     }
}
