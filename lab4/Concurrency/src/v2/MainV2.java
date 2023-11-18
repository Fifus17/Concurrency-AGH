package v2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import com.opencsv.CSVWriter;

public class MainV2 {

    public static void main(String[] args) throws InterruptedException, IOException {

//        List<String[]> csvData = createCsvDataSimple();
//
//        // default all fields are enclosed in double quotes
//        // default separator is a comma
//        try (CSVWriter writer = new CSVWriter(new FileWriter("c:\\test\\test.csv"))) {
//            writer.writeAll(csvData);
//        }

        int n = 5;
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
        for (int i = 0; i < n; i++) {
            p.get(i).join();
            p.get(i).summarize();
        }
    }
}
