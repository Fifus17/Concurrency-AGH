package v5;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainV5 {

    public static void main(String[] args) throws InterruptedException, IOException {
        List<String[]> csvData = new ArrayList<>();

        int n = 150;
        List<Philosopher> p = new ArrayList<>();
        List<Fork> f = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            f.add(new Fork());
        }

        Arbitrator arbitrator = new Arbitrator(n);

        ExecutorService executor = Executors.newFixedThreadPool(n);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            p.add(new Philosopher(i, f.get(i), f.get((i + 1) % n), arbitrator));
            futures.add(executor.submit(p.get(i)));
        }

        // Sleep for 20 seconds
        Thread.sleep(20000);

        for (Future<?> future : futures) {
            future.cancel(true); // Stop the philosopher threads
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS); // Wait for threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < n; i++) {
            csvData.add(p.get(i).summarize());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter("/Users/filipdziurdzia/IdeaProjects/FivePhilosophers/src/main/java/v5/task" + n + ".csv"))) {
            String[] header = {"id", "avg"};
            writer.writeAll(Collections.singleton(header));
            writer.writeAll(csvData);
        }
    }
}
