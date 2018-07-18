package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;
import ru.pdt53.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = (Contacts) app.contact().all();
    File photo = new File("src/test/resources/rose.jpg");
    ContactData contact = new ContactData()
            .withFirstname("vitaly").withLastname("polev").withPhoto(photo)
            .withMobilePhone("+79161013301").withEmail("vipo@yandex.ru").withGroup("groupA");
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = (Contacts) app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/rose.jpg");
    System.out.println(photo.exists());
  }

}