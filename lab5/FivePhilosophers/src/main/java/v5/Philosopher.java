package v5;

import java.util.Random;

public class Philosopher extends Thread {
    private int id;
    private final Fork left;
    private final Fork right;
    private final Arbitrator arbitrator;

    private int noIters = 0;
    private int timeWaited = 0;

    public Philosopher(int id, Fork left, Fork right, Arbitrator arbitrator) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.arbitrator = arbitrator;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            long start = System.currentTimeMillis();
            try {
                arbitrator.liftForks();
                left.lift(id, false);
                right.lift(id, true);
                arbitrator.releaseForks();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("Philosopher " + id + " is eating. He waited for: " + time + " ms");
            timeWaited += time;
            noIters++;
        }
    }

    public String[] summarize() {
        double averageTimeWaited = (double) timeWaited / (1000 * noIters);
        String formattedTimeWaited = String.format("%.4f", averageTimeWaited);
        System.out.println("Philosopher " + id + " has waited on average for " + formattedTimeWaited + " s. Number of iters: " + noIters + " Total time: " + timeWaited);
        return new String[]{String.valueOf(id), formattedTimeWaited};
    }
}
