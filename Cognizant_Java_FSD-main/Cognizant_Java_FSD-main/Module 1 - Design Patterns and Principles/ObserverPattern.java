import java.util.*;

interface Observer {
    void update(double price);
}

interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private double price;

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(price);
        }
    }
}

class MobileApp implements Observer {
    public void update(double price) {
        System.out.println("Mobile: " + price);
    }
}

class WebApp implements Observer {
    public void update(double price) {
        System.out.println("Web: " + price);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        market.register(new MobileApp());
        market.register(new WebApp());

        market.setPrice(2500);
    }
}