public class StrategyPatternExample 
{
    interface PaymentStrategy 
    {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy 
    {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) 
        {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(double amount) 
        {
            System.out.println("Paid $" + amount + " using Credit Card: " + cardNumber);
        }
    }

    static class PayPalPayment implements PaymentStrategy 
    {
        private String email;

        public PayPalPayment(String email) 
        {
            this.email = email;
        }

        @Override
        public void pay(double amount) 
        {
            System.out.println("Paid $" + amount + " using PayPal account: " + email);
        }
    }

    static class PaymentContext 
    {
        private PaymentStrategy strategy;

        public void setPaymentStrategy(PaymentStrategy strategy) 
        {
            this.strategy = strategy;
        }

        public void payAmount(double amount) 
        {
            if (strategy == null) 
            {
                System.out.println("Payment strategy not set.");
                return;
            }
            strategy.pay(amount);
        }
    }

    public static void main(String[] args) 
    {
        PaymentContext context = new PaymentContext();

        System.out.println("=== Paying with Credit Card ===");
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.payAmount(150.75);

        System.out.println("\n=== Paying with PayPal ===");
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.payAmount(89.99);
    }
}


