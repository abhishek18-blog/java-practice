enum Laptop {
    Macbook(2000), XPS(2200), Surface(2500), Ideapad(2800);

    //
    private int price;

    private Laptop(int price) {
        this.price = 700;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) {
        Laptop laptop = Laptop.Macbook;
        System.out.println(laptop.getPrice());

        Laptop.Macbook.setPrice(300);

        for (Laptop lap : Laptop.values()) {
            System.out.println(lap + " " + lap.getPrice());
        }
    }
}