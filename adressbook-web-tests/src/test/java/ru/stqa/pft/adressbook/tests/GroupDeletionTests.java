package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupDate().withName("test1"));
		}
	}

	@Test
	public void testGroupDeletion() throws Exception {
		Groups before = app.group().all();
		GroupDate deletedGroup = before.iterator().next();
		app.group().delete(deletedGroup);
		assertThat(app.group().count(), equalTo(before.size() - 1));
		Groups after = app.group().all();
		assertThat(after, equalTo(before.without(deletedGroup)));
	}
}
