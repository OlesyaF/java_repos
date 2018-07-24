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

public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group_B"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withFirstname("olga").withLastname("kuznecova").withEmail("oku@mail.ru")
              .withPhoto(new File("src/test/resources/rose.jpg"))
              .inGroup(app.db().groups().iterator().next()), true);
    }
  }

  @Test
  public void testContactExclusion() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    ContactData removedContact = null;
    GroupData reducedGroup = null;

    label1:
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 1) {
        removedContact = contact;
        for (GroupData group : contact.getGroups()) {
          reducedGroup = group;
          break label1;
        }
      }
    }

    if (removedContact == null) {
      app.goTo().HomePage();
      reducedGroup = groups.iterator().next();
      removedContact = new ContactData()
              .withFirstname("ivan").withLastname("andreev").withEmail("iva@yandex.ru")
              .withPhoto(new File("src/test/resources/rose.jpg"))
              .inGroup(reducedGroup);
      app.contact().create(removedContact, true);
      Contacts before = app.db().contacts();
      label2:
      for (ContactData contact : before) {
        if (contact.getGroups().size() == 1) {
          removedContact = contact;
          for (GroupData group : contact.getGroups()) {
            reducedGroup = group;
            break label2;
          }
        }
      }
    }

    Contacts before = app.db().contacts();

    app.goTo().HomePageAllGroup();
    app.contact().remove(removedContact, reducedGroup);

    app.goTo().HomePageAllGroup();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(removedContact).withAdded(new ContactData()
            .withId(removedContact.getId()).withFirstname(removedContact.getFirstname()).withLastname(removedContact.getLastname())
            .withMobilePhone(removedContact.getMobilePhone()).withEmail(removedContact.getEmail()))));
    verifyContactListInUI();
  }

}
