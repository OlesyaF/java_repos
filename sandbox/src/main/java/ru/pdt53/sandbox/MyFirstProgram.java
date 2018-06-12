package ru.pdt53.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Olesya");

    double l = 5.0;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

    double a = 6.0;
    double b = 4.0;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double l) {
    return l * l;
  }

  public static double area(double a, double b) {
    return a * b;
  }

}