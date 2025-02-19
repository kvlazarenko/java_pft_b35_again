package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.GroupDate;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().gotoGroupPage();
		app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
	}
}
