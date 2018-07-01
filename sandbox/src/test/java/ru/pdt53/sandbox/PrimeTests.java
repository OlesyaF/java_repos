package ru.pdt53.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }

  @Test
  public void testPrimesFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimesFast() {
    Assert.assertFalse(Primes.isPrimeFast(Integer.MAX_VALUE - 2));
  }

}
