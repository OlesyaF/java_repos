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

public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group_1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withFirstname("anna").withLastname("petrova").withEmail("anpet@yandex.ru")
              .withPhoto(new File("src/test/resources/rose.jpg")), true);
    }
  }

  @Test
  public void testContactAdd() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    ContactData addedContact = null;
    GroupData extensibleGroup = null;

    label:
    for (ContactData contact : contacts) {
      for (GroupData group : groups) {
        if (contact.getGroups().size() != groups.size()) {
          if (contact.getGroups().contains(group)) {
          } else {
            addedContact = contact;
            extensibleGroup = group;
            break label;
          }
        }
      }
    }

    if (extensibleGroup == null) {
      addedContact = contacts.iterator().next();
      extensibleGroup = new GroupData().withName("group_ZZZ");
      app.goTo().groupPage();
      app.group().create(extensibleGroup);
    }

    Contacts before = app.db().contacts();

    app.contact().add(addedContact, extensibleGroup);

    app.goTo().HomePageAllGroup();
    assertThat(app.contact().count(), equalTo(before.size()));
    verifyContactListInUI();
  }

}