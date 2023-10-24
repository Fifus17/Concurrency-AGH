package v2;

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
        int i = 0;
        long start = 0;
        while (i < noIters) {
            start = start == 0 ? System.currentTimeMillis() : start;
            boolean a=left.lock.tryLock();
            boolean b=right.lock.tryLock();
            if(a && b) {
                left.lift(id, false);
                right.lift(id, true);
                long end = System.currentTimeMillis();
                long time = end - start;
                System.out.println("Philosopher " + id + " is eating. He has waited for " + time + " ms");
                timeWaited += time;
                start = 0;
                i++;
            }
            if(a) left.lock.unlock();
            if(b) right.lock.unlock();
        }
    }

    public void summarize() {
        timeWaited = timeWaited/noIters;
        System.out.println("Philosopher " + id + " has waited on average for " + timeWaited + " ms");
    }
}
