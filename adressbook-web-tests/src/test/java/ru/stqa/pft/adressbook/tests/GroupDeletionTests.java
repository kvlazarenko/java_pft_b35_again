package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.getNavigationHelper().gotoGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		}
	}

	@Test
	public void testGroupDeletion() throws Exception {
		List<GroupDate> before = app.getGroupHelper().getGroupList();
		int index = before.size() - 1;
		app.getGroupHelper().selectGroup(index);
		app.getGroupHelper().deleteSelectedGroups();
		app.getGroupHelper().returnToGroupPage();
		List<GroupDate> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), index);
		before.remove(index);
		Assert.assertEquals(before, after);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
