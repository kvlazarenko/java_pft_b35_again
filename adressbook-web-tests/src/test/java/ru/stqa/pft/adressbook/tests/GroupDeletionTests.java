package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.Set;

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
		Set<GroupDate> before = app.group().all();
		GroupDate deletedGroup = before.iterator().next();
		app.group().delete(deletedGroup);
		Set<GroupDate> after = app.group().all();
		Assert.assertEquals(after.size(), before.size() - 1);
		before.remove(deletedGroup);
		Assert.assertEquals(before, after);
		//Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}


}
