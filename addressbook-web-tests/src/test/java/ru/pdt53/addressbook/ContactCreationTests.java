package ru.pdt53.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        gotoNewAddPage();
        fillNewAddForm(new ContactData("vitaly", "smirnov", "+79191010101", "testn@yandex.ru"));
        saveNewPerson();
    }

}
