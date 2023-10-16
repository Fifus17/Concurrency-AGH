public class ValueSetter extends Thread{

    static boolean work = true;
    SingleVal val;
    Double valueToSet;

    public ValueSetter(SingleVal val, Double valSet) {
        this.val = val;
        this.valueToSet = valSet;
    }

    @Override
    public void run() {
        while (work) work = val.setVal(valueToSet);
        System.out.println("Error");
    }
}


