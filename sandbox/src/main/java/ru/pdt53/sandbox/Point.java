package ru.pdt53.sandbox;

public class Point {
  public double x;
  public double y;

  public double distance1(Point p) {
    return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
  }
}
