import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
//        int numberOfProcessors = Integer.parseInt(args[0]);
//        int numberOfCycles = Integer.parseInt(args[1]);
//        String path = args[2];
        int numberOfProcessors = 4;
        int numberOfCycles = 12;
       //  String path = "C:\\Users\\super\\Desktop\\Tasks\\1thousand\\10k2.txt";
        String path = "Tasks.txt";
        if(numberOfProcessors < 0 || numberOfCycles < 0){
            throw new InvalidParameterException();
        }
        FastScanner fastScanner = readFile(path);
        startSimulation(getTasks(fastScanner), numberOfProcessors, numberOfCycles);
    }

    private static void startSimulation(Task[] tasks, int numberOfProcessors,int numberOfCycles) throws InterruptedException {
        long start =  java.lang.System.currentTimeMillis();
        Simulator simulator = new Simulator(numberOfProcessors, numberOfCycles, tasks);
        simulator.start();
        long stop =  java.lang.System.currentTimeMillis();
        double elapsedTime = stop - start;

        System.out.println(start + " " + stop);
        System.out.println("simulation time : " + elapsedTime);
    }
    private static Task[] getTasks(FastScanner fastScanner) {
        int numOfTasks = fastScanner.nextInt();
        Task[] tasks = new Task[numOfTasks];
        for (int i = 0; i < numOfTasks; i++) {
            int creationTime = fastScanner.nextInt();
            int executionTime = fastScanner.nextInt();
            int priority = fastScanner.nextInt();
            tasks[i] = new Task(creationTime, executionTime, priority, "T" + (i + 1));
        }
        return tasks;
    }
    private static FastScanner readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FastScanner f = new FastScanner(file);
        return f;
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st = new StringTokenizer("");
        FastScanner(File file) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(file));
        }
        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}