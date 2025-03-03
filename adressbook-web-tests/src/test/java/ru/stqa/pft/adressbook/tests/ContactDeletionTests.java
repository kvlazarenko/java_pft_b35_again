package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTests extends TestBase {

	@Test(enabled = false)
	public void testContactDeletion() {
		app.goTo().gotoHomePage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactDate("firstname1", "lastname1"
							, "phone1", "e-mail1", "test1"), true);
		}
		List<ContactDate> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().deleteSelectedContact();
		app.goTo().gotoHomePage();
		List<ContactDate> after = app.getContactHelper().getContactList();
		Assert.assertEquals(before.size() - 1, after.size());

		before.remove(before.size() - 1);

		Assert.assertEquals(before, after);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
