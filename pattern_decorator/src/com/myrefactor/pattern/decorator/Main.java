package com.myrefactor.pattern.decorator;

import com.myrefactor.pattern.decorator.shape.Circle;
import com.myrefactor.pattern.decorator.shape.Rectangle;

/*
 @startuml

interface Shape {
  +draw(): void
}

class Circle implements Shape {
  +draw(): void
}

class Rectangle implements Shape {
  +draw(): void
}

abstract class ShapeDecorator implements Shape {
  -decoratedShape: Shape

  +ShapeDecorator(decoratedShape: Shape)
  +draw(): void
}

class RedShapeDecorator extends ShapeDecorator {
  +draw(): void
  +setRedBorder(decoratedShape: Shape): void
}

@enduml
 */
public class Main {

  public static void main(String[] args) {
    Shape circle = new Circle();
    Shape redCircle = new RedShapeDecorator(new Circle());
    Shape redRectangle = new RedShapeDecorator(new Rectangle());
    circle.draw();
    redCircle.draw();
    redRectangle.draw();
  }

}
