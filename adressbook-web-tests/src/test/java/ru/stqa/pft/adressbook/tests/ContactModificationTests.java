package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		app.getNavigationHelper().gotoContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactDate("mod_firstname", "mod_lastname1"
						,"mod_phone1", "mod_e-mail1", null), false);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();

	}
}
