package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.goTo().homePage();
		if (!app.contact().isThereAContact()) {
			app.contact().create(new ContactDate().withFirstname("firstname1").withLastname("lastname1")
							.withAddress("Бульвар Олимпийский").withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
							.withEmail("e-mail1").withEmail2("e-mail12").withEmail3("e-mail13"), true);
		}
		Contacts before = app.contact().all();
		ContactDate deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		Assert.assertEquals(app.contact().count(), before.size() - 1);
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.without(deletedContact)));
	}
}
