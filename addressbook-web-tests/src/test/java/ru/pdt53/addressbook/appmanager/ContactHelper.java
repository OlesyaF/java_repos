package ru.pdt53.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.pdt53.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
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

  public void initContactModification() { click(By.name("modifiy")); }

  public void submitContactModification() { click(By.name("update")); }

  public void selectModificatedContact() { click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")); }

  public void acceptionContactDeletion() { wd.switchTo().alert().accept(); }

  public void deleteSelectedContacts() { click(By.xpath("//div/div[4]/form[2]/div[2]/input")); }

  public void selectContact() { click(By.name("selected[]")); }

}
