package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().gotoHomePage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactDate("firstname1", "lastname1"
							, "phone1", "e-mail1", "test1"), true);
		}
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
	}
}
