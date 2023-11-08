public class Coffee extends Food {
    private String aroma;

    public Coffee(String arome) {
// Вызвать конструктор предка, передав ему имя класса
        super("Кофе");
// Инициализировать аромат
        this.aroma = arome;
    }

    @Override
    public void consume() {
        System.out.println(this + " залил в себя");
    }

    public boolean equals(Object arg0) {
        if (super.equals(arg0)) {
            if (!(arg0 instanceof Coffee)) return false;
            return aroma.equals(((Coffee)arg0).aroma);
        } else
            return false;
    }

    // Переопределѐнная версия метода toString(), возвращающая не только
// название продукта, но и его размер
    public String toString() {
        return super.toString() + " с ароматом '" + aroma.toUpperCase() + "'";
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    @Override
    public int calculateCalories() {
        if (this.aroma.equals("rich"))
            return 14;
        if (this.aroma.equals("bitter"))
            return 18;
        if (this.aroma.equals("oriental"))
            return 10;
        return 0;
    }
}
