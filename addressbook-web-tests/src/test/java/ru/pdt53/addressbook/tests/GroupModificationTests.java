package ru.pdt53.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pdt53.addressbook.model.GroupData;
import ru.pdt53.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("group_mod"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("group_nm").withHeader("test_hm").withFooter("test_fm");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
  }

}
