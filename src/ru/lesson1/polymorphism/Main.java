package ru.lesson1.polymorphism;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Circle());
        shapeList.add(new Square());
        shapeList.add(new Triangle());
        for (Shape shape : shapeList) {
            doSomething(shape);
        }
    }

    private static void doSomething(Shape shape) {
        shape.draw();
    }
}
