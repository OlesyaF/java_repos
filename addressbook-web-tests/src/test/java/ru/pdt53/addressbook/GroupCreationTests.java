package ru.pdt53.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("group2", "group_header2", "comment2"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
