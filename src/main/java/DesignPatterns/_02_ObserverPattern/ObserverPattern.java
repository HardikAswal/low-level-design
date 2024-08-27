package DesignPatterns._02_ObserverPattern;

/*
Observer Pattern - Consists of an "Observable" entity and one or more "Observer" entities.
When the state of the Observable changes from S1 to S2 to S3, upon every change, it notifies all the
Observers about the state change

There is an Observable interface and an Observer interface.
The Observable interface HAS A list (one-to-many relationship) of all the Observers and they can be added or removed.
The Observable will also have a notify method which will be used to notify all the Observers upon any changes.

The Observers will have an update method which the Observable can call since Observable HAS A Observer,
on calling the update method, the notification will get completed

Optional - All Observers HAS A Observable, this way the Observer can simply check what has changed in
the Observable. This is optional because in the update method of Observer, we can send an instance of
Observable, but if there are many Observables that an Observer is observing, we will have to check everytime
that which Observer has notified now.

 */

import java.util.ArrayList;
import java.util.List;

// Observable
interface StocksObservable {

    public void addObserver(NotificationAlertObserver observer);

    public void removeObserver(NotificationAlertObserver observer);

    public void notifySubscribers();

    public void setStockCount(int stockCountAdded);

    public int getStockCount();
}

class iphoneObservableImpl implements StocksObservable {
    public List<NotificationAlertObserver> observerList  = new ArrayList<>();
    public int stockCount = 0;

    @Override
    public void addObserver(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(NotificationAlertObserver observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void setStockCount(int newStockAdded) {
        if(stockCount == 0) {
            notifySubscribers();
        }

        stockCount = newStockAdded;
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}

// Observer
interface NotificationAlertObserver {
    public void update();
}

class EmailAlertObserverImpl implements NotificationAlertObserver {

    String email;
    StocksObservable observable;

    EmailAlertObserverImpl(String email, StocksObservable observable) {
        this.email = email;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendMail(email, "Product is back in stock!");
    }

    private void sendMail(String emailId, String msg) {
        System.out.println("Mail sent to: " + emailId + "\nMessage: " + msg + "\n");
    }
}

class MobileAlertObserverImpl implements NotificationAlertObserver {

    String username;
    StocksObservable observable;

    MobileAlertObserverImpl(String username, StocksObservable observable) {
        this.username = username;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendMsgOnMobile(username, "Product is back in stock!");
    }

    private void sendMsgOnMobile(String username, String msg) {
        System.out.println("Msg sent to: " + username + "\nMessage: " + msg);
    }
}



public class ObserverPattern {
    public static void main(String[] args) {
        StocksObservable iphoneStockObservable = new iphoneObservableImpl();

        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("tim.cook@apple.com", iphoneStockObservable);
        NotificationAlertObserver observer2 = new EmailAlertObserverImpl("tim.cook@gmail.com", iphoneStockObservable);
        NotificationAlertObserver observer3 = new MobileAlertObserverImpl("Tim Cook", iphoneStockObservable);

        iphoneStockObservable.addObserver(observer1);
        iphoneStockObservable.addObserver(observer2);
        iphoneStockObservable.addObserver(observer3);

        iphoneStockObservable.setStockCount(10);
        iphoneStockObservable.setStockCount(0);
        iphoneStockObservable.setStockCount(10);
    }
}
