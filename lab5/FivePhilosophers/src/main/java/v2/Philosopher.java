package v2;

import java.util.Timer;

public class Philosopher extends Thread{

    private int id;
    private final Fork left;
    private final Fork right;

    private int noIters = 0;

    private int timeWaited = 0;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            long start = System.currentTimeMillis();
            boolean a=left.lock.tryLock();
            boolean b=right.lock.tryLock();
            if(a && b) {
                left.lift(id, false);
                right.lift(id, true);
                long end = System.currentTimeMillis();
                long time = end - start;
                System.out.println("Philosopher " + id + " is eating. He has waited for " + time + " ms");
                timeWaited += time;
                noIters++;
            }
            if(a) left.lock.unlock();
            if(b) right.lock.unlock();
        }
    }

    public String[] summarize() {
        if (noIters == 0) {
            System.out.println("Philosopher " + id + " didn't eat.");
            return new String[]{String.valueOf(id), "-1.00"};
        }

        double averageTimeWaited = (double) timeWaited / (1000*noIters);
        String formattedTimeWaited = String.format("%.2f", averageTimeWaited);
        System.out.println("Philosopher " + id + " has waited on average for " + averageTimeWaited + " s");
        return new String[]{String.valueOf(id), formattedTimeWaited};
    }
}
