package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.gotoNewAddPage();
        app.fillNewAddForm(new ContactData("vitaly", "smirnov", "+79191010101", "testn@yandex.ru"));
        app.saveNewPerson();
    }

}
