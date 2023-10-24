package v2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    public Lock lock = new ReentrantLock();

    public void lift(int id, boolean isRight) {
        System.out.println("Philosopher " + id + " has lifted " + (isRight ? "right " : "left ") + "fork");
    }
}
