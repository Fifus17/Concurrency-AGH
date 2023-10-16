import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainLab3 {


    public static void main(String[] args) {
        // -------- EX 1 ---------
        // 1-1

//        Buffer buffer = new BufferEx1(5);
//
//        Producer producer = new Producer(buffer);
//        Consumer consumer = new Consumer(buffer);
//
//        producer.start();
//        consumer.start();

        // p > n
//        Buffer buffer = new BufferEx1(15);
//
//        Producer p1 = new Producer(buffer, "p1");
//        Producer p2 = new Producer(buffer, "p2");
//        Producer p3 = new Producer(buffer, "p3");
//        Consumer c1 = new Consumer(buffer, "c1");
//        Consumer c2 = new Consumer(buffer, "c2");
//
//        p1.start();
//        p2.start();
//        p3.start();
//        c1.start();
//        c2.start();


        // -------- EX2 ---------

        // TODO - make it universal
        Buffer bufferP = new Buffer(10);
        Producer p = new Producer(bufferP, "p");

        Buffer buffer1 = new Buffer(10);
        PipeWorker pw1 = new PipeWorker(bufferP, buffer1, "pw1");
        Buffer buffer2 = new Buffer(10);
        PipeWorker pw2 = new PipeWorker(buffer1, buffer2, "pw2");
        Buffer buffer3 = new Buffer(10);
        PipeWorker pw3 = new PipeWorker(buffer2, buffer3, "pw3");
        Buffer buffer4 = new Buffer(10);
        PipeWorker pw4 = new PipeWorker(buffer3, buffer4, "pw4");
        Buffer buffer5 = new Buffer(10);
        PipeWorker pw5 = new PipeWorker(buffer4, buffer5, "pw5");
        Buffer buffer6 = new Buffer(10);
        PipeWorker pw6 = new PipeWorker(buffer5, buffer6, "pw6");
        Buffer bufferC = new Buffer(10);
        PipeWorker pw7 = new PipeWorker(buffer6, bufferC, "pw7");

        Consumer c = new Consumer(bufferC, "c");

        p.start();
        pw1.start();
        pw2.start();
        pw3.start();
        pw4.start();
        pw5.start();
        pw6.start();
        pw7.start();
        c.start();

    }
}