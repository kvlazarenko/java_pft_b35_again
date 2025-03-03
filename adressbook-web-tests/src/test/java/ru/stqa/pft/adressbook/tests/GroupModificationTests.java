package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.getNavigationHelper().gotoGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		}
	}

	@Test
	public void testGroupModification() {
		List<GroupDate> before = app.getGroupHelper().getGroupList();
		int index = before.size() - 1;
		GroupDate group = new GroupDate(before.get(index).getId(), "test1", "test2", "test3");
		app.getGroupHelper().modifyGroup(index, group);
		List<GroupDate> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
		before.remove(index);
		before.add(group);
		before.sort(Comparator.comparing(GroupDate::getId));
		after.sort(Comparator.comparing(GroupDate::getId));
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}


}
