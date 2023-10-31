import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedNode {

    private Object object;

    private SynchronizedNode prev;

    private SynchronizedNode next;

    // Means it is being modified and cannot be read or modified by another writer
    private ReentrantLock writerLock = new ReentrantLock();

    // Means it is being read and cannot be modified
    private ReentrantLock readerLock = new ReentrantLock();

    public SynchronizedNode(Object object) {
        this.object = object;
    }

    public SynchronizedNode(Object object, SynchronizedNode prev) {
        this.object = object;
        this.prev = prev;
    }

    public SynchronizedNode(Object object, SynchronizedNode prev, SynchronizedNode next) {
        this.object = object;
        this.prev = prev;
        this.next = next;
    }

    public void writerLock() {
        writerLock.lock();
    }

    public void readerLock() {
        readerLock.lock();
    }

    public void writerUnlock() {
        writerLock.unlock();
    }

    public void readerUnlock() {
        readerLock.unlock();
    }

    public boolean isWriterLock() {
        return writerLock.isLocked();
    }

    public boolean isReaderLock() {
        return readerLock.isLocked();
    }

    public Object get() {
        return object;
    }

    public void set(Object object) {
        this.object = object;
    }

    public SynchronizedNode getPrev() {
        return prev;
    }

    public void setPrev(SynchronizedNode prev) {
        this.prev = prev;
    }

    public SynchronizedNode getNext() {
        return next;
    }

    public void setNext(SynchronizedNode next) {
        this.next = next;
    }

    public boolean contains(Object o) {
        if (prev != null) prev.readerUnlock();
        if (object.equals(o)) {
            readerUnlock();
            return true;
        }
        if (next != null) {
            if(!next.isReaderLock()) next.readerLock();
            return next.contains(o);
        }
        readerUnlock();
        return false;
    }

    public boolean remove(Object o) {
        if (object.equals(o)) {
            if(prev != null) prev.setNext(next);
            if (next != null) next.setPrev(prev);
            return true;
        }
        if (next != null) return next.remove(o);
        return false;
    }

}