package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.HashSet;
import java.util.List;

public class GroupDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupDate().withName("test1"));
		}
	}

	@Test
	public void testGroupDeletion() throws Exception {
		List<GroupDate> before = app.group().list();
		int index = before.size() - 1;
		app.group().delete(index);
		List<GroupDate> after = app.group().list();
		Assert.assertEquals(after.size(), before.size() - 1);
		before.remove(index);
		Assert.assertEquals(before, after);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}


}
