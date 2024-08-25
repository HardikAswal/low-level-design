package DesignPatterns._01_StrategyPattern._01_WithoutStrategyPattern;

public class WithoutStrategyPattern {
    public static void main(String[] args) {
        Vehicle vehicle = new PassengerVehicle();
        vehicle.drive();
    }
}

class Vehicle {
    public void drive() {
        System.out.println("Normal drive capability");
    }

    public void display() {
        System.out.println("Normal display");
    }
}

class PassengerVehicle extends Vehicle {
    @Override
    public void display() {
        System.out.println("Special display");
    }
}

class SportsVehicle extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Advanced drive capability");
    }
}

class OffRoadVehicle extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Advanced drive capability");
    }

    @Override
    public void display() {
        System.out.println("Special display");
    }
}

/*
Both SportyVehicle and OffRoadVehicle require a custom "Advanced Drive Capability", so that code is
duplicate.
Similarly, both PassengerVehicle and OffRoadVehicle require a special display, so that code is duplicate

Same can happen if more custom features are common among different types of vehicles.
*/