package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;
import ru.pdt53.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = (Contacts) app.contact().all();
    ContactData contact = new ContactData().
            withFirstname("andrey").withLastname("ivanov").withMobile("+79851013301").withEmail("anseiv@yandex.ru").withGroup("groupA");
    app.contact().create(contact, true);
    app.goTo().gotoHomePage();
    Contacts after = (Contacts) app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
