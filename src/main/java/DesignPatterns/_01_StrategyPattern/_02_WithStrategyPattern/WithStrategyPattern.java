package DesignPatterns._01_StrategyPattern._02_WithStrategyPattern;

/*
When to use StrategyPattern ?
When a base class has multiple child classes that need their own implementation of methods from the base class
and some of those implementations are shared
 */

// Define a "drive strategy" interface

interface DriveStrategy {
    public void drive();
}

// Define all the required driving modes i.e. Drive Strategies

class NormalDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Normal Drive Strategy");
    }
}

class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Sports Drive Strategy");
    }
}

class Vehicle {
    DriveStrategy driveStrategy;

    Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        this.driveStrategy.drive();
    }
}

class PassengerVehicle extends Vehicle {
    PassengerVehicle() {
        super(new NormalDriveStrategy());
    }
}

class OffRoadVehicle extends Vehicle {
    OffRoadVehicle() {
        super(new SportsDriveStrategy());
    }
}

class SportsVehicle extends Vehicle {
    SportsVehicle() {
        super(new SportsDriveStrategy());
    }
}

public class WithStrategyPattern {
    Vehicle vehicle = new SportsVehicle();
}
