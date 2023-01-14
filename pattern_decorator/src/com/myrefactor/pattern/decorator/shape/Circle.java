package com.myrefactor.pattern.decorator.shape;

import com.myrefactor.pattern.decorator.Shape;

public class Circle implements Shape {

  @Override
  public void draw() {
    System.out.println("Drawing a circle");
  }

}
