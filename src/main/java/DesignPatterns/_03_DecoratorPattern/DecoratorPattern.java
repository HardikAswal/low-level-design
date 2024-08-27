package DesignPatterns._03_DecoratorPattern;

/*
Decorator Pattern - There is a Base object with a certain functionality say F1, now in order to
get more functionalities while having the base functionality intact, we can add "Decorators" over
the base object. These decorators will be of the type of Base object itself.
Multiple such decorators can be added one over the other increasing the functionalities like
F1 -> F1, F2 -> F1, F2, F3 -> F1, F2, F3...Fn

Eg. Over a Base Pizza, multiple decorators can be added.
Like - Base Pizza -> Base Pizza + Extra Cheese, Base Pizza + Extra Cheese + Mushrooms, Base Pizza + Mushrooms etc

Need of Decorator Pattern - To avoid Class Explosion
Eg. We have a base pizza + 5 different toppings. If we make a class for all the combinations of toppings along with the base pizza,
there will be too many classes to manage. So we simply add those toppings as Decorators over each other as required

NOTE:
1. Decorator -IS A- Base Class i.e. We have a base class and all the decorators extend it.
2. Decorator -HAS A- Base class, which makes it easy to return an instance of Base class to the next decorator

 */


// Base pizza classes
abstract class BasePizza {
    public abstract int getCost();
}

class Margherita extends  BasePizza {
    @Override
    public int getCost() {
        return 100;
    }
}

class VegDelight extends  BasePizza {
    @Override
    public int getCost() {
        return 120;
    }
}

class Farmhouse extends  BasePizza {
    @Override
    public int getCost() {
        return 200;
    }
}

// Decorators

abstract class ToppingDecorator extends BasePizza {}

class ExtraCheese extends ToppingDecorator {
    BasePizza basePizza;

    ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int getCost() {
        return this.basePizza.getCost() + 10;
    }
}

class Mushroom extends ToppingDecorator {
    BasePizza basePizza;

    Mushroom(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int getCost() {
        return this.basePizza.getCost() + 15;
    }
}


public class DecoratorPattern {
    public static void main(String[] args) {
        BasePizza pizza = new Mushroom(new ExtraCheese(new Margherita()));
        System.out.println(pizza.getCost());
    }
}
