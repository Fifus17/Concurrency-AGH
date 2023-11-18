package v6;

public class Fork {

    public void lift(int id, boolean isRight) {
        System.out.println("Philosopher " + id + " has lifted " + (isRight ? "right " : "left ") + "fork");
    }
}
