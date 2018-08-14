package ru.pdt53.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {
  @Test

  public void TestDistance1() {
    Point p1 = new Point();
    p1.x = 5;
    p1.y = 10;

    Point p2 = new Point();
    p2.x = 5;
    p2.y = 10;

    Assert.assertEquals(p1.distance1(p2), 2.0);
  }

  @Test

  public void TestDistance2() {
    Point p1 = new Point();
    p1.x = 0;
    p1.y = 0;

    Point p2 = new Point();
    p2.x = 3;
    p2.y = 4;

    Assert.assertEquals(p1.distance1(p2), 5.0);
  }

  @Test

  public void TestDistance3() {
    Point p1 = new Point();
    p1.x = 1;
    p1.y = 0;

    Point p2 = new Point();
    p2.x = -4;
    p2.y = -12;

    Assert.assertEquals(p1.distance1(p2), 13.0);
  }
}
