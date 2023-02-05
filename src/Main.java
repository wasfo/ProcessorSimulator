import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

       // HashMap<Integer, Boolean> hash;
       // hash.

      //  int numP = Integer.parseInt(args[0]);
     //   int numCyc = Integer.parseInt(args[1]);
  //      String path = args[2];

        File file = new File("C:\\Users\\super\\Desktop\\Tasks\\Testcase1.txt");
        FastScanner f = new FastScanner(file);
        int numOfTasks = f.nextInt();
        Task[] tasks = new Task[numOfTasks];
        System.out.println(numOfTasks);

        for (int i = 0; i < numOfTasks; i++) {
            int creationTime = f.nextInt();
            int executionTime = f.nextInt();
            int priority = f.nextInt();
            tasks[i] = new Task(creationTime, executionTime, priority, "T" + (i + 1));
        }
        for (int j = 0; j < numOfTasks; j++) {
            System.out.println(tasks[j]);
        }

        long start = System.currentTimeMillis();
        Simulator simulator = new Simulator(10, 10, tasks);
        simulator.start();
        long stop = System.currentTimeMillis();
        double elapsedTime = stop - start;
        System.out.println(elapsedTime/1000.0);
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
        long nextLong() {

            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() throws IOException {
            return br.readLine();
        }
    }
}

