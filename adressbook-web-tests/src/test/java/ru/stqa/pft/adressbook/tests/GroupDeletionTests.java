package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.List;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() throws Exception {

		app.getNavigationHelper().gotoGroupPage();
		if (! app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		}
		List<GroupDate> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().selectGroup(before.size() -1);
		app.getGroupHelper().deleteSelectedGroups();
		app.getGroupHelper().returnToGroupPage();
		List<GroupDate> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() - 1);
	}
}
