package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("ivan", "stolz", "+79191013301", "testI@yandex.ru", "groupA"), true);
    }
    app.getContactHelper().selectModificatedContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewAddForm(new ContactData("max", "vasin", "+79192223301", "testA@yandex.ru", null), false);
    app.getContactHelper().submitContactModification();
  }
}
