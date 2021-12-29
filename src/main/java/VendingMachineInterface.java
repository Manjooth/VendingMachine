import java.util.List;

public interface VendingMachineInterface
{
    /**
     Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.
     **/
    String acceptCoins(List<String> coins);

    /**
     Allow user to select products Coke(25), Pepsi(35), Soda(45)
     **/
    String selectProduct(List<String> products);

    /**
     Allow user to take refund by canceling the request.
     **/
    String cancelRequest();

    /**
     Return the selected product and remaining change if any
     **/
    String buyProduct();

    /**
     Allow reset operation for vending machine supplier. Must enter correct pin
     **/
    String reset(String code);
}
