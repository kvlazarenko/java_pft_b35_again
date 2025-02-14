package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(new ContactDate("firstname1", "lastname1", "phone1", "e-mail1"));
		app.getContactHelper().submitContactCreation();
		app.getContactHelper().returnToHomePage();
	}


}
