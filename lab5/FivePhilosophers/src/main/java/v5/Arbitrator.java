package v5;

import java.util.concurrent.Semaphore;

public class Arbitrator {
    private Semaphore semaphore;

    public Arbitrator(int numberOfPhilosophers) {
        semaphore = new Semaphore(numberOfPhilosophers);
    }

    public void liftForks() throws InterruptedException {
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());
    }

    public void releaseForks() {
        semaphore.release();
        System.out.println(semaphore.availablePermits());
    }
}
