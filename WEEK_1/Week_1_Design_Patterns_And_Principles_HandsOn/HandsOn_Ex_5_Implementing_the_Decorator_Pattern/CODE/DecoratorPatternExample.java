public class DecoratorPatternExample 
{
    interface Notifier 
    {
        void send(String message);
    }

    static class EmailNotifier implements Notifier 
    {
        @Override
        public void send(String message) {
            System.out.println("Email Notification: " + message);
        }
    }

    static abstract class NotifierDecorator implements Notifier 
    {
        protected Notifier notifier;

        public NotifierDecorator(Notifier notifier) 
        {
            this.notifier = notifier;
        }

        @Override
        public void send(String message) 
        {
            notifier.send(message); 
        }
    }

    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("SMS Notification: " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("Slack Notification: " + message);
        }
    }

    public static void main(String[] args) 
    {
        Notifier emailNotifier = new EmailNotifier();

        Notifier emailAndSMS = new SMSNotifierDecorator(emailNotifier);

        Notifier fullNotifier = new SlackNotifierDecorator(emailAndSMS);

        System.out.println("=== Sending Notification via Email only ===");
        emailNotifier.send("Your report is ready.");

        System.out.println("\n=== Sending Notification via Email + SMS ===");
        emailAndSMS.send("Your package has shipped.");

        System.out.println("\n=== Sending Notification via Email + SMS + Slack ===");
        fullNotifier.send("Server is down!");
    }
}


