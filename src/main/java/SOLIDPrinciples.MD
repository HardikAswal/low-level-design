# SOLID Principles

## S -> Single Responsibility Principle
## O -> Open/Closed Principle
## L -> Liskov Substitution Principle
## I -> Interface Segmented Principle
## D -> Dependency Inversion Principle


## 1. Single Responsibility Principle

A class should have only one reason to change, i.e. a class should have only one responsibility.

```java
class Marker {
    String name;
    String color;
    int year;
    int price;
    
    Marker(String name, String color, int year, int price) {
        this.name = name;
        this.color = color;
        this.year = year;
        this.color = color;
    }
}

// Invoice -> HAS A -> Marker

class Invoice {
    private Marker marker;
    private int quantity;
    
    Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }
    
    public int calculateTotal() {
        return this.marker.price * this.quantity;
    }
    
    public void printInvoice() {
        
    }
    
    public void saveToDB() {
        
    }
}
```

This class is NOT following the Single Responsibility Principle as:
1. If pricing model needs to change, we have to make changes to the Invoice class
2. If there's a change in printing logic, this class needs to be updated
3. If we have to saveToFile as well, this class needs to change

Solution:

```java
class Invoice {
    private Marker marker;
    private int quantity;
    
    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }
    
    public int calculateTotal() {
        return this.marker.price * this.quantity;
    }
}

class InvoiceDAO {
    private Invoice invoice;
    
    InvoiceDAO(Invoice invoice) {
        this.invoice = invoice;
    }
    
    public void saveToDB() {}
}

class InvoicePrinter() {
    private Invoice invoice;
    
    InvoiceaPrinter(Invoice invoice) {
        this.invoice = invoice;
    }
    
    public void print() {}
}
```

Now each class has single responsibility.



## 2. Open/Closed Principle

Class is open for extension but closed for modification

```java
class InvoiceDAO {
    private Invoice invoice;
    
    InvoiceDAO(Invoice invoice) {
        this.invoice = invoice;
    }
    
    public void saveToDB() {}
}

// Now we require saveToFile as well

class InvoiceDAO {
    private Invoice invoice;

    InvoiceDAO(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {}

    public void saveToFile() {}
}
```

This is not following Open/Closed principle. A tested and working class should be extended 
and NOT modified for getting extra capabilities

Solution:

```java
interface InvoiceDAO {
    public void save(Invoice invoice);
}

class DBInvoiceDAO implements InvoiceDAO {
    
    @Override
    public void save(Invoice invoice) {}
}

class FileInvoiceDAO implements InvoiceDAO {
    
    @Override
    public void save(Invoice invoice) {}
}
```

Now, N number of extensions can be made for the same interface.


## 3. Liskov Substitution Principle

If Class B is a subtype of Class A, then we should be able to replace object of A with B 
without breaking the behaviour of the program

Subclass should extend the capability of parent class, not narrow it down

```java
interface Bike {
    void turnOnEngine();
    void accelerate();
}

class MotorBike implements Bike {
    bool isEngineOn;
    int speed;
    
    @Override
    public void turnOnEngine() {
        isEngineOn = true;
    }
    
    @Override
    public void accelerate() {
        speed += 10;
    }
}

class Cycle implements Bike {
    bool isEngineOn;
    int speed;

    @Override
    public void turnOnEngine() {
        throw new AssertionError('No engine');
    }

    @Override
    public void accelerate() {
        speed += 10;
    }
}
```

Here, Class Cycle is narrowing down the capabilities of the Bike interface as it does not 
have an engine. 



## 4. Interface Segmented Principle

Interfaces should be designed as such that the client should not implement unnecessary function
that they do not need

```java
interface RestaurantEmployee {
    void washDishes();
    void serveCustomers();
    void cookFood();
}

class Waiter implements RestaurantEmployee {
    
    @Override
    public void washDishes() {
        // not my job    
    }
    
    @Override 
    public void serveCustomers() {
        System.out.println("Serving customers!!");
    }
    
    @Override
    public void cookFood() {
        //not my job
    }
}
```
Here, class waiter has to implement unnecessary functions that are not relevant to his job

Solution: Break interface into smaller logical units

```java
interface WaiterInterface {
    void serveCustomers();
    void takeOrder();
}

interface ChefInterface {
    void cookFood();
    void decideMenu();
}

class Waiter implements WaiterInterface {
    @Override
    public void serveCustomers() {
        System.out.println("Serving customers");
    }

    @Override
    public void takeOrder() {
        System.out.println("Taking order");
    }
}
```

Now, the interface have been divided such that class do not have to implement unnecessary
functions



## 5. Dependency Inversion Principle

Class should depend on interface rather that concrete classes

Eg: 
Interface Mouse
    Concrete classes
    - Wired Mouse
    - Bluetooth Mouse

Interface Keyboard
    Concrete classes
    - Wired keyboard
    - Bluetooth Keyboard

```java
class Macbook {
    private final WiredKeyboard keyboard;
    private final WiredMouse mouse;
    
    public Macbook() {
        this.keyboard = new WiredKeyboard();
        this.mouse = new WiredMouse();
    }
}
```

This class is NOT following the Dependency Inversion Principle.
It has a hard limit to what type of mouse and keyboard a macbook can have. If in future, 
we need to add bluetooth keyboard and bluetooth mouse to the macbook, it won't be possible

Solution:

```java
class Macbook {
    private final Keyboard keyboard;
    private final Mouse mouse;
    
    public Macbook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
}
```

Now, the class Macbook is following Dependency Inversion Principle. 
The contructor injection is taking keyboard and mouse interface types, thus allowing any type of 
keyboard and any type of mouse.