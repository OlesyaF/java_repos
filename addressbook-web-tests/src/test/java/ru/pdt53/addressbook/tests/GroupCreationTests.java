package ru.pdt53.addressbook.tests;

import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("group2", "group_header2", "comment2"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
