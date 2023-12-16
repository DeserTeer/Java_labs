package bsu.rfe.java.group_5.lab_1.Grintsevich.B_1;

public class Cheese extends Food {
    public Cheese() {
        super("Сыр");
    }
    public void consume() {
        System.out.println(this + " съеден");
    }

    @Override
    public int calculateCalories() {
        return 402;
    }}
