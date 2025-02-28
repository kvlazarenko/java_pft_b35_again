package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		app.getNavigationHelper().gotoHomePage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactDate("firstname1", "lastname1"
							, "phone1", "e-mail1", "test1"), true);
		}
		List<ContactDate> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().initContactModification(before.size() - 1);
		ContactDate contact = new ContactDate(before.get(before.size() - 1).getId(), "mod_firstname", "mod_lastname1"
						, "mod_phone1", "mod_e-mail1", null);
		app.getContactHelper().fillContactForm(contact, false);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();
		List<ContactDate> after = app.getContactHelper().getContactList();
		Assert.assertEquals(before.size(), after.size());
		before.remove(before.size() - 1);

		before.add(contact);
		before.sort(Comparator.comparing(ContactDate::getId));
		after.sort(Comparator.comparing(ContactDate::getId));
		Assert.assertEquals(before, after);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
