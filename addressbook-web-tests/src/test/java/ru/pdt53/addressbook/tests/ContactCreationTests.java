package ru.pdt53.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstname("andrey").withLastname("ivanov").withMobile("+79851013301").withEmail("anseiv@yandex.ru").withGroup("groupA");
    app.contact().create(contact, true);
    app.goTo().gotoHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
