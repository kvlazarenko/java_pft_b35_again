package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.goTo().groupPage();
		Groups before = app.group().all();
		GroupDate group = new GroupDate().withName("test1");
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size() + 1));
		Groups after = app.group().all();
		assertThat(after, equalTo((before.withAdded(
						group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))));
	}
}
