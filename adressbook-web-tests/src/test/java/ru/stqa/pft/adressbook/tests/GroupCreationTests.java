package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.fail;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().gotoGroupPage();
		List<GroupDate> before = app.getGroupHelper().getGroupList();
		GroupDate group = new GroupDate("test1", null, null);
		app.getGroupHelper().createGroup(group);
		List<GroupDate> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() + 1);


		//	int max = 0;
		//for (GroupDate g : after) {
		//	if (g.getId() > max) {
		//		max = g.getId();
		//		}
		//		group.setId(max);
		//	}

		group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

		before.add(group);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
