public class Main {

    public static void main(String[] args) {

         // Task 9
        SingleVal singleVal = new SingleVal();
        ValueSetter vs1 = new ValueSetter(singleVal, Double.MIN_NORMAL);
        ValueSetter vs2 = new ValueSetter(singleVal, Double.MAX_VALUE);
        ValueSetter vs3 = new ValueSetter(singleVal, 0.0);

        vs1.start();
        vs2.start();
        vs3.start();
    }
}