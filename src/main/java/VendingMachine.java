import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachine implements VendingMachineInterface
{
    private final String RESET_CODE = "15670932348";
    private final Map<String, Integer> coinValues = new HashMap<>();
    private final Map<String, Integer> productValues = new HashMap<>();
    private int balance;
    private int productTotal;

    public VendingMachine()
    {
        coinValues.put(Coins.penny.name(), 1);
        coinValues.put(Coins.nickel.name(), 5);
        coinValues.put(Coins.dime.name(), 10);
        coinValues.put(Coins.quarter.name(), 25);

        productValues.put(Products.coke.name(), 25);
        productValues.put(Products.pepsi.name(), 35);
        productValues.put(Products.soda.name(), 45);
    }

    @Override
    public String acceptCoins(final List<String> coins)
    {
        final int filterListSize = coins.stream().filter(coin -> Arrays.stream(Coins.values()).anyMatch(coinsName -> coinsName.name().equals(coin.toLowerCase()))).collect(Collectors.toList()).size();
        final boolean isAccepted = coins.size() == filterListSize;

        if(isAccepted)
        {
            totalBalance(coins);
        }

        return isAccepted ? "Coins accepted" : "Invalid - coin is not accepted";
    }

    @Override
    public String selectProduct(final List<String> products)
    {
        final int filterListSize = products.stream().filter(product -> Arrays.stream(Products.values()).anyMatch(productName -> productName.name().equals(product.toLowerCase()))).collect(Collectors.toList()).size();
        final boolean isAccepted = products.size() == filterListSize;

        if(isAccepted)
        {
            totalProducts(products);
        }

        return isAccepted ? "Products accepted" : "Invalid - that item does not exist";
    }

    @Override
    public String cancelRequest()
    {
        final String message = "Amount refunded: " + balance;
        balance = 0;
        return message;
    }

    @Override
    public String buyProduct()
    {
        final boolean enoughBalance = checkBalance();
        if(!enoughBalance)
        {
            return "Error - not enough balance to buy products";
        }

        balance -= productTotal;

        return "Success, balance remaining: " + balance;
    }

    @Override
    public String reset(final String code)
    {
        return code.equals(RESET_CODE) ? "Success - machine has been reset" : "Error - incorrect code";
    }

    public int getBalance()
    { // test aid
        return balance;
    }

    public int getProductTotal()
    { // test aid
        return productTotal;
    }

    private void totalBalance(final List<String> coins)
    {
        coins
                .stream()
                .map(coin -> coinValues.get(coin.toLowerCase()))
                .toList()
                .forEach(coinAmount -> balance += coinAmount);
    }

    private void totalProducts(final List<String> products)
    {
        products
                .stream()
                .map(coin -> productValues.get(coin.toLowerCase()))
                .toList()
                .forEach(productAmount -> productTotal += productAmount);
    }

    private boolean checkBalance() {
        return balance >= productTotal;
    }
}
