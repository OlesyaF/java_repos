package ru.pdt53.addressbook;

import org.testng.annotations.Test;

public class PersonCreationTests extends TestBase{

    @Test
    public void testPersonCreation() {
        gotoNewAddPage();
        fillNewAddForm(new PersonData("vitaly", "smirnov", "+79191010101", "testn@yandex.ru"));
        saveNewPerson();
    }

}
