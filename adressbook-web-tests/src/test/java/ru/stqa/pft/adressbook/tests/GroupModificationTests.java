package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModification() {
		app.getNavigationHelper().gotoGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		}
		List<GroupDate> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().selectGroup(before.size() - 1);
		app.getGroupHelper().initGroupModification();

		GroupDate group = new GroupDate(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupPage();
		List<GroupDate> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());


		before.remove(before.size() - 1);
		before.add(group);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
