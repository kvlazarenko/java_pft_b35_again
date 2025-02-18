package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(new ContactDate("firstname1", "lastname1",
						"phone1", "e-mail1", "test1"), true);
		app.getContactHelper().submitContactCreation();
		app.getNavigationHelper().gotoContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
			}
}
