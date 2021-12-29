public enum Products
{
    COKE("coke", 25),
    PEPSI("pepsi", 35),
    SODA("soda", 45);

    private final String name;
    private final int price;

    Products(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public static int getProductValue(final String product)
    {
        return switch (product) {
            case "coke" -> COKE.price;
            case "pepsi" -> PEPSI.price;
            case "soda" -> SODA.price;
            default -> 0;
        };
    }
}
