package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.GroupDate;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.gotoGroupPage();
		app.initGroupCreation();
		app.fillGroupForm(new GroupDate("test1", "test2", "test3"));
		app.submitGroupCreation();
		app.returnToGroupPage();
	}
}
