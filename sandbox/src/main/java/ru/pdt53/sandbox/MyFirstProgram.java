package ru.pdt53.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point();
    p1.x = 5;
    p1.y = 12;

    Point p2 = new Point();
    p2.x = 0;
    p2.y = 0;

    System.out.println("Расстояние между точками = " + p1.distance1(p2));

    System.out.println("Расстояние между точками = " + distance2(p1, p2));

  }

  public static double distance2(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
  }
}
