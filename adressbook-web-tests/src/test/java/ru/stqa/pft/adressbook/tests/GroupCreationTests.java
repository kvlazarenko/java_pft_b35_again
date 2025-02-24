package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().gotoGroupPage();
		List<GroupDate> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
		List<GroupDate> after = app.getGroupHelper().getGroupList();
				Assert.assertEquals(after.size(), before.size() + 1);
	}
}
