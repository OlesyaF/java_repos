package ru.pdt53.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Olesya");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(6, 4);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(Rectangle r) {
    return r.a * r.b;
  }

}
