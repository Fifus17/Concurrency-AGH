public class SingleVal {

    private Double val;

    public boolean setVal(Double val) {
        this.val = val;
        return val == this.val;
    }
}

