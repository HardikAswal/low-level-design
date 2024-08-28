package DesignPatterns._04_FactoryAndAbstractFactoryPattern;

/*
Abstract Factory Pattern - Factory of Factory. It is used when we have multiple factories from where we may
have to get the objects of classes.
i.e. Objects are grouped and we have different factories in place to get objects

 */

interface Car {
    public int getTopSpeed();
}

class EconomicCar1 implements Car {
    @Override
    public int getTopSpeed() {
        return 100;
    }
}

class EconomicCar2 implements Car {
    @Override
    public int getTopSpeed() {
        return 150;
    }
}

class LuxuryCar1 implements Car {
    @Override
    public int getTopSpeed() {
        return 250;
    }
}

class LuxuryCar2 implements Car {
    @Override
    public int getTopSpeed() {
        return 300;
    }
}

// Vehicle Factories & interface
interface AbstractCarFactory {
    public Car getInstance(int price);
}

class EconomicCarFactory implements AbstractCarFactory {
    @Override
    public Car getInstance(int price) {
        if(price <= 300000)
            return new EconomicCar1();
        else
            return new EconomicCar2();
    }
}

class LuxuryCarFactory implements AbstractCarFactory {
    @Override
    public Car getInstance(int price) {
        if(price >= 1000000 && price <= 2000000)
            return new LuxuryCar1();
        else if(price > 2000000)
            return new LuxuryCar2();
        return null;
    }
}

// Factory of Factories
class AbstractFactoryProducer {
    public AbstractCarFactory getFactoryInstance(String value) {
        if(value.equals("Economic"))
            return new EconomicCarFactory();
        else if(value.equals("Luxury") || value.equals("Premium"))
            return new LuxuryCarFactory();
        else
            return null;
    }
}


public class _02_AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactoryProducer abstractFactoryProducerOb = new AbstractFactoryProducer();
        AbstractCarFactory abstractFactoryObj = abstractFactoryProducerOb.getFactoryInstance("Economic");
        Car carObj = abstractFactoryObj.getInstance(500000);
        System.out.println(carObj.getTopSpeed());
    }
}
