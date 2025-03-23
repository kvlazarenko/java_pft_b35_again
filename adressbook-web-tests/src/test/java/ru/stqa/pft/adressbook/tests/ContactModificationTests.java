package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		app.goTo().homePage();
		if (!app.contact().isThereAContact()) {
			app.contact().create(new ContactDate().withFirstname("firstname1")
							.withLastname("lastname1").withHomePhone("homephone").withMobilePhone("momilehone").withWorkPhone("workphone")
							.withEmail("e-mail1"), true);
		}
		Contacts before = app.contact().all();
		ContactDate modifiedContact = before.iterator().next();
		ContactDate contact = new ContactDate().withId(modifiedContact.getId())
						.withFirstname("mod_firstname1").withLastname("mod_lastname1")
						.withHomePhone("homephone").withMobilePhone("momilehone").withWorkPhone("workphone")
						.withEmail("e-mail1");
		app.contact().modify(contact);
		Assert.assertEquals(app.contact().count(), before.size());
		Contacts after = app.contact().all();
		Assert.assertEquals(before.size(), after.size());
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}


}
