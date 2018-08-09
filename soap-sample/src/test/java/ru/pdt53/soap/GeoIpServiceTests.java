package ru.pdt53.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    boolean check1 = true;
    boolean check2 = false;
    String countryIpLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("85.141.67.175");
    System.out.println(countryIpLocation);
    if (countryIpLocation.contains("RU")) { check2 = true; }
    assertEquals(check1, check2);
  }
}
