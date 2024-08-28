package DesignPatterns._04_FactoryAndAbstractFactoryPattern;

/*
Factory Pattern - It is a creational design pattern.
It is used when objects of different classes are to be created on the basis of some condition.
For eg. If condition 1 matches, create object of class A, if condition 2 matches create object of class B and so on.

The other way of solving this would be to use if else, and if the same logic is to be used multiple times in the codebase,
it gets messy. Thus, a factory which creates objects based on certain condition matching is used.

 */

// Interface for all the classes that we may need to create objects of, on conditional basis
interface Shape {
    void draw();
}

// Concrete classes
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

// Factory - which returns objects on the basis of conditions
class ShapeFactory {
    public Shape getShape(String input) {
        switch(input) {
            case "circle": return new Circle();
            case "rectangle": return new Rectangle();
            case "square": return  new Square();
            default: return null;
        }
    }
}

public class _01_FactoryPattern {
    public static void main(String[] args) {
        ShapeFactory shapeFactoryObj = new ShapeFactory();
        Shape shapeObj = shapeFactoryObj.getShape("circle");
        shapeObj.draw();
    }
}
