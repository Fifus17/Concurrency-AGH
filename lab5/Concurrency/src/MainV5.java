public class MainV5 {

    public static void main(String[] args) {
        SynchronizedList list = new SynchronizedList();
        list.add("a");
        list.add("c");
        list.add("z");

        // quick testing
        System.out.println(list.contains("c"));
//        System.out.println(list.remove("c"));
//        System.out.println(list.contains("c"));
//        System.out.println(list.remove("c"));
//        System.out.println(list.remove("a"));
//        System.out.println(list.remove("z"));
//        System.out.println(list.add("b"));
    }
}