public class IncrementingWorker extends Thread{

    SingleVal val;
    int n;

    public IncrementingWorker(SingleVal val, int n) {
        this.val = val;
        this.n = n;
    }

//    public void run() {
//        for (int i = 0; i < n; i++) {
//            val.increment();
//            SingleVal.staticIncrement();
//        }
//
//    }
}
