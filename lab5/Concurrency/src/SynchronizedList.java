public class SynchronizedList {

    private SynchronizedNode firstNode;

    private SynchronizedNode lastNode;

    public SynchronizedList(SynchronizedNode node) {
        this.firstNode = node;
        this.lastNode = node;
    }

    public SynchronizedList() {}

    public SynchronizedNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(SynchronizedNode firstNode) {
        this.firstNode = firstNode;
    }

    public SynchronizedNode getLastNode() {
        return lastNode;
    }

    public void setLastNode(SynchronizedNode lastNode) {
        this.lastNode = lastNode;
    }

    public boolean contains(Object o) {
        if (firstNode != null) {
            firstNode.readerLock();
            return firstNode.contains(o);
        }
        return false;
    }

    public boolean remove(Object o) {
        return firstNode.remove(o) && (firstNode != null);
    }

    public boolean add(Object o) {
        SynchronizedNode newNode = new SynchronizedNode(o, lastNode);
        if (lastNode != null) {
            lastNode.setNext(newNode);
            setLastNode(newNode);
            return true;
        }
        else {
            setFirstNode(newNode);
            setLastNode(newNode);
            return true;
        }
    }

}
