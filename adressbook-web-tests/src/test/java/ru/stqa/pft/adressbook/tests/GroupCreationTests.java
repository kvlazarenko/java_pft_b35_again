package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.GroupDate;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().gotoGroupPage();
		int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		int after = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before + 1);
	}
}
