package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.goTo().groupPage();
		List<GroupDate> before = app.group().list();
		GroupDate group = new GroupDate().withName("test1");
		app.group().create(group);
		List<GroupDate> after = app.group().list();
		Assert.assertEquals(after.size(), before.size() + 1);


		//int max = 0;
		//for (GroupDate g : after) {
		//	if (g.getId() > max) {
		//		max = g.getId();
		//	}
		//	group.setId(max);
		//}

		//Comparator<? super GroupDate> byId = new Comparator<GroupDate>() {
		//	@Override
		//	public int compare(GroupDate o1, GroupDate o2) {
		//		return Integer.compare(o1.getId(), o2.getId());
		//	}
		//};
		//int max1 = after.stream().max(byId).get().getId();

		group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

		before.add(group);

		before.sort(Comparator.comparing(GroupDate::getId));
		after.sort(Comparator.comparing(GroupDate::getId));
		Assert.assertEquals(before,after);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
