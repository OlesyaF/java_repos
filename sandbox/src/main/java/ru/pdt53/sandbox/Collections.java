package ru.pdt53.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {

    //Коллекция типа массив:

    String[] langs = {"Java", "C#", "Python", "PHP"};

    for (String l : langs) {
      System.out.println("Я хочу выучить " + l);
    }

    System.out.println("\n");

    //Коллекция типа интерфейс List:

    List<String> languges = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languges) {
      System.out.println("Я хочу выучить " + l);
    }

  }
}
