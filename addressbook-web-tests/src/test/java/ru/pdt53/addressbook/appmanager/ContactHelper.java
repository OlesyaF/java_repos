package ru.pdt53.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.pdt53.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void saveNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewAddForm(ContactData personData) {
    type(By.name("firstname"), personData.getFirstname());
    type(By.name("lastname"), personData.getLastname());
    type(By.name("mobile"), personData.getMobile());
    type(By.name("email"), personData.getEmail());
  }

  public void gotoNewAddPage() {
    click(By.linkText("add new"));
  }
}
