package v3;

import java.util.ArrayList;
import java.util.List;

public class MainV3 {

    public static void main(String[] args) throws InterruptedException {

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
