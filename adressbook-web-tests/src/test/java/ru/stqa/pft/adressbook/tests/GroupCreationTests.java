package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.Set;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.goTo().groupPage();
		Set<GroupDate> before = app.group().all();
		GroupDate group = new GroupDate().withName("test1");
		app.group().create(group);
		Set<GroupDate> after = app.group().all();
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

		//group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
		group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
		before.add(group);
		//before.sort(Comparator.comparing(GroupDate::getId));
		//after.sort(Comparator.comparing(GroupDate::getId));
		Assert.assertEquals(before,after);
		//Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
