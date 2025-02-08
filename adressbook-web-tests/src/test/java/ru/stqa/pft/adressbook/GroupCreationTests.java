package ru.stqa.pft.adressbook;

import org.testng.annotations.*;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		gotoGroupPage();
		initGroupCreation();
		fillGroupForm(new GroupDate("test1", "test2", "test3"));
		submitGroupCreation();
		returnToGroupPage();
	}
}
