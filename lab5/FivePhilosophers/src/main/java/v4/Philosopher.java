package v4;

import java.util.Random;

public class Philosopher extends Thread{

    private int id;
    private final Fork left;
    private final Fork right;

    private final int noIters = 5;

    private int timeWaited = 0;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!Thread.interrupted()) {
            long start = System.currentTimeMillis();
            int order = random.nextInt(2);
            synchronized ((order==0) ? left : right) {
                ((order==0) ? left : right).lift(id, order==1);
                synchronized ((order==0) ? right : left) {
                    ((order==0) ? right : left).lift(id, order==0);
                    long end = System.currentTimeMillis();
                    long time = end - start;
                    timeWaited += time;
                    System.out.println("Philosopher " + id + " is eating. He waited for: " + (end - start) + " ms");
                }
            }
        }
    }

    public String[] summarize() {
        double averageTimeWaited = (double) timeWaited / (1000 * noIters);
        String formattedTimeWaited = String.format("%.2f", averageTimeWaited);
        System.out.println("Philosopher " + id + " has waited on average for " + formattedTimeWaited + " s");
        return new String[]{String.valueOf(id), formattedTimeWaited};
    }

}
