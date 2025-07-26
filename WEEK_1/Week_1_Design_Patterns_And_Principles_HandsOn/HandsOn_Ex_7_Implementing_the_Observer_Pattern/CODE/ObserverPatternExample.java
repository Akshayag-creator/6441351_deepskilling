import java.util.*;
public class ObserverPatternExample 
{
    interface Observer 
    {
        void update(String stockName, double price);
    }

    interface Stock 
    {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers(String stockName, double price);
    }

    static class StockMarket implements Stock 
    {
        private List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) 
        {
            observers.add(o);
            System.out.println(o.getClass().getSimpleName() + " registered.");
        }

        @Override
        public void removeObserver(Observer o) 
        {
            observers.remove(o);
            System.out.println(o.getClass().getSimpleName() + " removed.");
        }

        @Override
        public void notifyObservers(String stockName, double price) 
        {
            for (Observer observer : observers) 
            {
                observer.update(stockName, price);
            }
        }

        public void setStockPrice(String stockName, double price) 
        {
            System.out.println("\n[StockMarket] Updating " + stockName + " price to $" + price);
            notifyObservers(stockName, price);
        }
    }

    static class MobileApp implements Observer 
    {
        @Override
        public void update(String stockName, double price) 
        {
            System.out.println("MobileApp: " + stockName + " is now $" + price);
        }
    }

    static class WebApp implements Observer 
    {
        @Override
        public void update(String stockName, double price) 
        {
            System.out.println("WebApp: " + stockName + " updated to $" + price);
        }
    }

    public static void main(String[] args) 
    {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice("AAPL", 190.5);
        stockMarket.setStockPrice("GOOGL", 3050.75);

        stockMarket.removeObserver(webApp);
        stockMarket.setStockPrice("AAPL", 193.0);
    }
}


