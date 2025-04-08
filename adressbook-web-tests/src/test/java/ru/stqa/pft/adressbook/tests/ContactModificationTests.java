package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		if (app.db().contacts().size() == 0) {
			app.goTo().homePage();
			app.contact().create(new ContactDate()
							.withFirstname("firstname")
							.withLastname("lastname")
							.withAddress("Бульвар Олимпийский")
							.withHomePhone("homephone")
							.withMobilePhone("mobilephone")
							.withWorkPhone("workphone")
							.withEmail("e-mail1")
							.withEmail2("e-mail12")
							.withEmail3("e-mail13"), true);
		}

		Contacts before = app.db().contacts();
		ContactDate modifiedContact = before.iterator().next();
		ContactDate contact = new ContactDate()
						.withId(modifiedContact.getId())
						.withFirstname("mod_firstname")
						.withLastname("mod_lastname")
						.withAddress("mod_Бульвар Олимпийский")
						.withHomePhone("mod_homephone")
						.withMobilePhone("mod_momilehone")
						.withWorkPhone("mod_workphone")
						.withEmail("mod_e-mail1")
						.withEmail2("mod_email2")
						.withEmail3("mod_email3");
		app.contact().modify(contact);
		Assert.assertEquals(app.contact().count(), before.size());
		Contacts after = app.db().contacts();
		Assert.assertEquals(before.size(), after.size());
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
}
