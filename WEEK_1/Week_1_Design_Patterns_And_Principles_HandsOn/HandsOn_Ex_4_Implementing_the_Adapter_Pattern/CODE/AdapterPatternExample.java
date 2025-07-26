public class AdapterPatternExample 
{
    interface PaymentProcessor 
    {
        void processPayment(double amount);
    }

    static class PayPalGateway 
    {
        public void makePayment(String accountEmail, double amount) 
        {
            System.out.println("PayPal: Processing payment of $" + amount + " for account " + accountEmail);
        }
    }

    static class StripeGateway 
    {
        public void chargeCard(String cardNumber, double amount) 
        {
            System.out.println("Stripe: Charging $" + amount + " to card " + cardNumber);
        }
    }

    static class PayPalAdapter implements PaymentProcessor 
    {
        private PayPalGateway paypal;
        private String email;

        public PayPalAdapter(PayPalGateway paypal, String email) 
        {
            this.paypal = paypal;
            this.email = email;
        }

        @Override
        public void processPayment(double amount) {
            paypal.makePayment(email, amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor 
    {
        private StripeGateway stripe;
        private String cardNumber;

        public StripeAdapter(StripeGateway stripe, String cardNumber) 
        {
            this.stripe = stripe;
            this.cardNumber = cardNumber;
        }

        @Override
        public void processPayment(double amount) 
        {
            stripe.chargeCard(cardNumber, amount);
        }
    }

    public static void main(String[] args) {
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway(), "user@example.com");
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway(), "1234-5678-9012-3456");

        System.out.println("=== Payment Processing ===");
        paypalProcessor.processPayment(100.0);
        stripeProcessor.processPayment(250.5);
    }
}


