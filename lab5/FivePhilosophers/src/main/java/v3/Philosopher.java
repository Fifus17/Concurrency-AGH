package v3;

public class Philosopher extends Thread{

    private int id;
    private final Fork left;
    private final Fork right;

    private int noIters = 0;

    private float timeWaited = 0;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            long start = System.currentTimeMillis();
            synchronized ((id%2==0) ? left : right) {
                ((id%2==0) ? left : right).lift(id, id%2==1);
                synchronized ((id%2==0) ? right : left) {
                    ((id%2==0) ? right : left).lift(id, id%2==0);
                    long end = System.currentTimeMillis();
                    long time = end - start;
                    timeWaited += time;
                    System.out.println("Philosopher " + id + " is eating. He waited for: " + (end - start) + " ms");
                }
            }
            noIters++;
        }
    }

    public String[] summarize() {
        timeWaited = timeWaited/noIters;
        String formattedTimeWaited = String.format("%.2f", timeWaited);
        System.out.println("Philosopher " + id + " has waited on average for " + timeWaited + " ms");
        return new String[]{String.valueOf(id), formattedTimeWaited};
    }
}
