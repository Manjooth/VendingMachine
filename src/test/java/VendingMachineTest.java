import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest
{

    public static final String INVALID_COIN_IS_NOT_ACCEPTED_MESSAGE = "Invalid - coin is not accepted";
    public static final String COINS_ACCEPTED_MESSAGE = "Coins accepted";
    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp()
    {
        vendingMachine = new VendingMachine();
    }

    @Test
    void shouldReturnErrorMessageWhenCoinsAreNotAccepted()
    {
        String response = vendingMachine.acceptCoins(List.of("100"));
        assertEquals(INVALID_COIN_IS_NOT_ACCEPTED_MESSAGE, response);
    }

    @Test
    void shouldReturnSuccessMessageWhenCoinsAreAccepted()
    {
        String response = vendingMachine.acceptCoins(List.of("penny"));
        assertEquals(COINS_ACCEPTED_MESSAGE, response);
    }

    @Test
    void shouldReturnErrorMessageWhenListOfCoinsGivenAndTwoCoinsAreNotAccepted()
    {
        String response = vendingMachine.acceptCoins(List.of("penny", "dime", "fifty", "twenty"));
        String response2 = vendingMachine.acceptCoins(List.of("penny", "dime", "fifty", "twenty", "penny", "penny", "quarter"));
        String response3 = vendingMachine.acceptCoins(List.of("penny", "penny", "quarter", "1"));

        assertEquals(INVALID_COIN_IS_NOT_ACCEPTED_MESSAGE, response);
        assertEquals(INVALID_COIN_IS_NOT_ACCEPTED_MESSAGE, response2);
        assertEquals(INVALID_COIN_IS_NOT_ACCEPTED_MESSAGE, response3);
    }

    @Test
    void shouldReturnSuccessMessageWhenListOfAcceptedCoinsAreGiven()
    {
        String response = vendingMachine.acceptCoins(List.of("penny", "penny", "quarter"));
        String response2 = vendingMachine.acceptCoins(List.of("quarter", "nickel"));
        String response3 = vendingMachine.acceptCoins(List.of("dime", "penny", "nickel", "dime", "penny", "quarter"));

        assertEquals(COINS_ACCEPTED_MESSAGE, response);
        assertEquals(COINS_ACCEPTED_MESSAGE, response2);
        assertEquals(COINS_ACCEPTED_MESSAGE, response3);
    }

    @Test
    void shouldReturnCorrectBalanceWhenCoinsAreAllCorrect()
    {
        vendingMachine.acceptCoins(List.of("nickel", "dime", "quarter"));
        int response = vendingMachine.getBalance();

        assertEquals(40, response);
    }

    @Test
    void shouldReturnCorrectBalanceWhenCoinsAreIncorrect()
    {
        vendingMachine.acceptCoins(List.of("nickel", "dime", "apple"));
        int response = vendingMachine.getBalance();

        assertEquals(0, response);
    }

    @Test
    void shouldReturnErrorMessageWhenProductDoesNotExist()
    {
        String response = vendingMachine.selectProduct(List.of("a"));
        assertEquals("Invalid - that item does not exist", response);
    }

    @Test
    void shouldReturnSuccessMessageWhenProductDoesExist()
    {
        String response = vendingMachine.selectProduct(List.of("coke", "pepsi"));
        assertEquals("Products accepted", response);
    }

    @Test
    void shouldReturnCorrectProductTotalWhenProductsAreCorrect()
    {
        vendingMachine.selectProduct(List.of("coke", "coke", "pepsi", "soda"));
        int response = vendingMachine.getProductTotal();

        assertEquals(130, response);
    }

    @Test
    void shouldReturnCorrectProductTotalWhenProductsAreIncorrect()
    {
        vendingMachine.selectProduct(List.of("coke", "sprite", "pepsi", "soda"));
        int response = vendingMachine.getProductTotal();

        assertEquals(0, response);
    }

    @Test
    void shouldReturnRefundMessageWhenCancellingRequestAndBalanceShouldBeZero()
    {
        vendingMachine.acceptCoins(List.of("quarter"));
        String response = vendingMachine.cancelRequest();

        assertEquals("Amount refunded: 25", response);
        assertEquals(0, vendingMachine.getBalance());
    }

    @Test
    void shouldReturnErrorMessageWhenBalanceIsNotEnoughToBuyProducts()
    {
        vendingMachine.acceptCoins(List.of("quarter"));
        vendingMachine.selectProduct(List.of("coke", "soda"));
        String response = vendingMachine.buyProduct();

        assertEquals("Error - not enough balance to buy products", response);
    }

    @Test
    void shouldReturnErrorMessageWhenBalanceIsExactlyCorrectToBuyProducts()
    {
        vendingMachine.acceptCoins(List.of("quarter", "dime"));
        vendingMachine.selectProduct(List.of("pepsi"));
        String response = vendingMachine.buyProduct();

        assertEquals("Success, balance remaining: 0", response);
    }

    @Test
    void shouldReturnSuccessMessageWhenBalanceIsMoreThanEnoughToBuyProducts()
    {
        vendingMachine.acceptCoins(List.of("quarter", "dime", "dime"));
        vendingMachine.selectProduct(List.of("pepsi"));
        String response = vendingMachine.buyProduct();

        assertEquals("Success, balance remaining: 10", response);
    }

    @Test
    void shouldReturnErrorMessageWhenCodeToResetIsIncorrect()
    {
        String response = vendingMachine.reset("");
        assertEquals("Error - incorrect code", response);
    }

    @Test
    void shouldReturnSuccessMessageWhenCodeToResetIsCorrect()
    {
        String response = vendingMachine.reset("15670932348");
        assertEquals("Success - machine has been reset", response);
    }
}
