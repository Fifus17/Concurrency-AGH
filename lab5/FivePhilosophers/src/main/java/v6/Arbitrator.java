package v6;

import java.util.concurrent.Semaphore;

public class Arbitrator {
    private Semaphore semaphore;

    public Arbitrator(int numberOfPhilosophers) {
        semaphore = new Semaphore(numberOfPhilosophers);
    }

    public boolean liftForks() throws InterruptedException {
        return semaphore.tryAcquire();
    }

    public void releaseForks() {
        semaphore.release();
        System.out.println(semaphore.availablePermits());
    }
}
