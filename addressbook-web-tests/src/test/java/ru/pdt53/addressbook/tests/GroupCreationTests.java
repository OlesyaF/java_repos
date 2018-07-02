package ru.pdt53.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("Group123", null, "commentM"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
