import java.util.*;

public class JavaChangeCounter {

    // Initialize the register
    private static LinkedHashMap<Double, Integer> register = new LinkedHashMap<Double, Integer>() {
        {
            put(5.0, 1);
            put(2.0, 3);
            put(1.0, 5);
            put(0.5, 10);
            put(0.2, 20);
            put(0.1, 200);
            put(0.05, 100);
            put(0.02, 100);
            put(0.01, 10000);
        }
    };

    public static void main(String[] args) {
        // Enter the amounts for which you want change
        double[] amounts = {1.30, 11.70, 4.30};
        for(double amount: amounts) {
            calculateChange(amount);
        }
    }

    public static void calculateChange(double amount) {
        System.out.println("\nChange for " + amount + " zł:");
        for(Map.Entry<Double, Integer> entry: register.entrySet()) {
            double coinValue = entry.getKey();
            int coinCount = entry.getValue();
            if(amount < coinValue || coinCount == 0) continue;

            int requiredCoins = (int) (amount / coinValue);
            if(requiredCoins > coinCount)
                requiredCoins = coinCount;

            amount = Math.round((amount - requiredCoins * coinValue) * 100.0) / 100.0;
            System.out.println("Give " + requiredCoins + " coins of " + coinValue + " zł");

            // Update the register
            register.put(coinValue, coinCount - requiredCoins);
        }
    }
}

