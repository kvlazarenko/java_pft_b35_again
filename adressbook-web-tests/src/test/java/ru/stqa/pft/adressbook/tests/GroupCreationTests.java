package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
	@DataProvider
	public Iterator<Object[]> validGroups() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[]{new GroupDate().withName("test 1").withHeader("header 1").withFooter("footer 1")});
		list.add(new Object[]{new GroupDate().withName("test 2").withHeader("header 2").withFooter("footer 3")});
		list.add(new Object[]{new GroupDate().withName("test 3").withHeader("header 2").withFooter("footer 3")});
		return list.iterator();
	}


	@Test(dataProvider = "validGroups")
	public void testGroupCreation(GroupDate group) throws Exception {
		app.goTo().groupPage();
		Groups before = app.group().all();
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size() + 1));
		Groups after = app.group().all();
		assertThat(after, equalTo((before.withAdded(
						group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))));
	}
}
