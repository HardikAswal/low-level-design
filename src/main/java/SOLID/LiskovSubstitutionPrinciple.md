# Liskov Substitution Principle

If Class B is a subtype of Class A, then we should be able to replace object of A with B
without breaking the behaviour of the program

Subclass should extend the capability of parent class, not narrow it down



Parent class
    - Child1
    - Child2
    - Child3

Now, on substituting the objects of parent and child, the code should not break

Parent p = new Parent(); &check;

Parent p = new Child1(); &check;

Parent p = new Child2(); &check;

Parent p = new Child3(); &check;

```java
interface Vehicle {
    int numberOfWheels() {
        return 2;
    }
    
    Boolean hasEngine() {
        return true;
    }
}

class MotorCycle implements Vehicle {
}

class Car implements Vehicle {
    @Override
    int numberOfWheels() {
        return 4;
    }
}

public class Main {
    public static void main(String args[]) {
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new MotorCycle());
        vehicleList.add(new Car());
        
        for(Vehicle v : vehicleList) {
            System.out.println(v.hasEngine().toString());
        }
    }
}

// Now we add another child that will narrow the capability
class Bicycle implements Vehicle {
    @Override
    Boolean hasEngine() {
        return null;
    }
}

public class Main {
    public static void main(String args[]) {
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new MotorCycle());
        vehicleList.add(new Car());
        vehicleList.add(new Bicycle());

        for(Vehicle v : vehicleList) {
            System.out.println(v.hasEngine().toString());
        }
    }
}

// Now when for Bicycle class, hasEngine method is called it will return null, and 
// on toString() it will throw null pointer exception
```

Solution:

```java
public class Vehicle {
    int numberOfWheels() {
        return 2;
    }
}

public class EngineVehicle extends Vehicle {
    public Boolean hasEngine() {
        return true;
    }
}

public class Bicycle extends Vehicle {
}

public class Car extends EngineVehicle {}
public class MotorCycle extends EngineVehicle {}

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new MotorCycle());
        vehicleList.add(new Car());
        vehicleList.add(new Bicycle());

        for(Vehicle v : vehicleList) {
            System.out.println(v.numberOfWheels().toString());
        }
    }
}

// We can access numberOfWheels() method for all types of vehicles as it is common property

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new MotorCycle());
        vehicleList.add(new Car());
        vehicleList.add(new Bicycle());

        for(Vehicle v : vehicleList) {
            System.out.println(v.hasEngine().toString()); // Will not be allowed for Bicycle
        }
    }
}

// for Bicycle, there's no hasEngine() method hence it won't be allowed

public class Main {
    public static void main(String[] args) {
        List<EngineVehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new MotorCycle());
        vehicleList.add(new Car());
        vehicleList.add(new Bicycle()); // Compilation Error

        for(Vehicle v : vehicleList) {
            System.out.println(v.hasEngine().toString()); // Will not be allowed for Bicycle
        }
    }
}

// Compile Time Error: Bicycle is NOT of type EngineVehicle
```
