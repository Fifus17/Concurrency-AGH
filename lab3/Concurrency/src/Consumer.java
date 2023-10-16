import java.util.concurrent.TimeUnit;

class Consumer extends Thread {
    private Buffer buffer;
    private String name;

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Object item = buffer.take(name);
//                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}