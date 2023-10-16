import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PipeWorker extends Thread {

    private Buffer bufferIn;
    private Buffer bufferOut;
    private Random random;
    private String name;
    public PipeWorker(Buffer bi, Buffer bo, String name) {
        this.bufferIn = bi;
        this. bufferOut = bo;
        this.name = name;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                bufferOut.put(bufferIn.take(name), name);
                TimeUnit.MILLISECONDS.sleep((int) random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
