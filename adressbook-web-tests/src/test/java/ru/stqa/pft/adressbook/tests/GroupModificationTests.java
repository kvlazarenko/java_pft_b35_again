package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModification() {
		app.getNavigationHelper().gotoGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		}
		int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().selectGroup(before - 1);
		app.getGroupHelper().initGroupModification();
		app.getGroupHelper().fillGroupForm(new GroupDate("test1", "test2", "test3"));
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupPage();
		int after = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before);
	}
}
