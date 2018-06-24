package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().gotoNewAddPage();
        app.getContactHelper().fillNewAddForm(new ContactData("ivan", "stolz", "+79191013301", "testI@yandex.ru", "groupA"), true);
        app.getContactHelper().saveNewContact();
    }

}
