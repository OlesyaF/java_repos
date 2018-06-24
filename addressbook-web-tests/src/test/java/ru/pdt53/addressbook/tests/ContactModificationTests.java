package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectModificatedContact();;
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewAddForm(new ContactData("vitaly", null, "+79191010101", "testn@yandex.ru"));
    app.getContactHelper().submitContactModification();
  }
}
