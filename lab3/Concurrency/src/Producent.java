import java.util.concurrent.TimeUnit;

class Producer extends Thread {
    private Buffer buffer;
    private String name;

    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.put(new Object(), name);
//                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
