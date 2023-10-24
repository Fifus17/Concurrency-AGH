package v1;

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
        for (int i = 0; i < noIters; i++) {
            long start = System.currentTimeMillis();
            synchronized (left) {
                synchronized (right) {
                    left.lift(id, false);
                    right.lift(id, true);
                    long end = System.currentTimeMillis();
                    long time = end - start;
                    System.out.println("Philosopher " + id + " is eating. He waited for: " + time + " ms");
                    timeWaited += time;
                }
            }
        }
    }

    public void summarize() {
        timeWaited = timeWaited/noIters;
        System.out.println("Philosopher " + id + " has waited on average for " + timeWaited + " ms");
    }

    public int getAvgTimeWaited() { return timeWaited; }
}
