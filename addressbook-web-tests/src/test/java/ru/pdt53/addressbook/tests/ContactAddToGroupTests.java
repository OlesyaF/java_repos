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
      app.group().create(new GroupData().withName("group_A"));
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

    label:
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        addedContact = contact;
        break label;
      }
    }

    GroupData extensibleGroup = groups.iterator().next();

    if (addedContact == null) {
      app.goTo().HomePage();
      addedContact = new ContactData()
              .withFirstname("vera").withLastname("simonova").withEmail("vsi@yandex.ru")
              .withPhoto(new File("src/test/resources/rose.jpg"));
      app.contact().create(addedContact, true);
      Contacts before = app.db().contacts();
      label2:
      for (ContactData contact : before) {
        if (contact.getGroups().size() == 0) {
          addedContact = contact;
          break label2;
        }
      }
    }

    Contacts before = app.db().contacts();

    app.goTo().HomePageAllGroup();
    app.contact().add(addedContact, extensibleGroup);

    app.goTo().HomePageAllGroup();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(addedContact).withAdded(new ContactData()
            .withId(addedContact.getId()).withFirstname(addedContact.getFirstname()).withLastname(addedContact.getLastname())
            .withMobilePhone(addedContact.getMobilePhone()).withEmail(addedContact.getEmail())
            .inGroup(extensibleGroup))));
    verifyContactListInUI();
  }

}