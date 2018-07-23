package ru.pdt53.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;
import ru.pdt53.addressbook.model.Contacts;
import ru.pdt53.addressbook.model.GroupData;
import ru.pdt53.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group_1"));
    }
  }

  @Test
  public void testContactCreation() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData()
            .withFirstname("mary").withLastname("ivanova").withEmail("miv@yandex.ru").withMobilePhone("8(985)123-00-19")
            .withPhoto(new File("src/test/resources/rose.jpg"))
            .inGroup(groups.iterator().next());
    app.goTo().HomePage();
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

}