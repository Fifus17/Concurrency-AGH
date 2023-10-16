import java.util.*;

public class Buffer {

    private Queue<Object> buffer = new LinkedList<>();
    private int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void put(Object obj, String name) throws InterruptedException {
        while (buffer.size() == maxSize) {
            wait();
        }
        buffer.add(obj);
        System.out.println(name + " producing: " + obj);
        notify();
    }

    public synchronized Object take(String name) throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        Object item = buffer.poll();
        System.out.println(name + " consuming: " + item);
        notify();
        return item;
    }

}
