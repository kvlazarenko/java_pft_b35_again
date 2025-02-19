package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() throws Exception {

		app.getNavigationHelper().gotoGroupPage();
		if (! app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		}
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().deleteSelectedGroups();
		app.getGroupHelper().returnToGroupPage();
	}
}
