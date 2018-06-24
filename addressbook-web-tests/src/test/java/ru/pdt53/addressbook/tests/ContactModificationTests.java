package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectModificatedContact();;
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewAddForm(new ContactData("ivan", "vasin", "+79192223301", "testA@yandex.ru", null), false);
    app.getContactHelper().submitContactModification();
  }
}
