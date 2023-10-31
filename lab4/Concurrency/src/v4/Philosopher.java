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
        for (int i = 0; i < noIters; i++) {
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

    public void summarize() {
        timeWaited = timeWaited/noIters;
        System.out.println("Philosopher " + id + " has waited on average for " + timeWaited + " ms");
    }
}
