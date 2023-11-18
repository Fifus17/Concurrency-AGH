package v3;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainV3 {

    public static void main(String[] args) throws InterruptedException, IOException {

        List<String[]> csvData = new ArrayList<>();

        int n = 250;
        List<Philosopher> p = new ArrayList<>();
        List<Fork> f = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            f.add(new Fork());
        }
        for (int i = 0; i < n; i++) {
            p.add(new Philosopher(i, f.get(i), f.get((i+1)%n)));
        }
        for (int i = 0; i < n; i++) {
            p.get(i).start();
        }
        long startTime = System.currentTimeMillis();
        long duration = 20 * 1000; // 20 seconds in milliseconds

        while (System.currentTimeMillis() - startTime < duration) {}
        for (int i = 0; i < n; i++) {
            p.get(i).interrupt();
            p.get(i).join();
            csvData.add(p.get(i).summarize());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter("/Users/filipdziurdzia/IdeaProjects/FivePhilosophers/src/main/java/v3/task" + n + ".csv"))) {
            String[] header = {"id", "avg"};
            writer.writeAll(Collections.singleton(header));
            writer.writeAll(csvData);
        }
    }
}
