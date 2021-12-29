public enum Coins
{
    PENNY("penny", 1),
    NICKEL("nickel", 5),
    DIME("dime", 10),
    QUARTER("quarter", 25);

    private final String name;
    private final int denomination;

    Coins(String name, int denomination)
    {
        this.name = name;
        this.denomination = denomination;
    }

    public int getDenomination()
    {
        return denomination;
    }
}
