package v1;

import java.util.ArrayList;
import java.util.List;

public class MainV1 {

    public static void main(String[] args) throws InterruptedException {

        int n = 500;
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
            Philosopher ph = p.get(i);
            ph.join();
            ph.summarize();
        }
//        System.out.println("Average time philosophers waited: " + p.stream().map(Philosopher::getAvgTimeWaited).reduce(0, (subtotal, time) -> subtotal + time)/p.size() + " ms");
    }
}
