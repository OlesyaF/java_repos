package ru.pdt53.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point();
    p1.x = 10;
    p1.y = 30;

    Point p2 = new Point();
    p2.x = 20;
    p2.y = 30;

    System.out.println("Расстояние между двумя точками с координатами " + "(" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") равно " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
  }

}
