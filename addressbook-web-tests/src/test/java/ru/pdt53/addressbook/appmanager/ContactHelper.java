package ru.pdt53.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pdt53.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void saveNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewAddForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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
