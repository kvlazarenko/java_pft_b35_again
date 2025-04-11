package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() throws InterruptedException {
		if (app.db().contacts().size() == 0) {
			app.goTo().homePage();
			app.contact().create(new ContactDate()
							.withFirstname("firstname1")
							.withLastname("lastname1")
							.withAddress("Бульвар Олимпийский")
							.withHomePhone("homephone")
							.withMobilePhone("mobilephone")
							.withWorkPhone("workphone")
							.withEmail("e-mail1")
							.withEmail2("e-mail12")
							.withEmail3("e-mail13"), true);
		}
		Contacts before = app.db().contacts();
		ContactDate deletedContact = before.iterator().next();
		before.remove(deletedContact);
		app.contact().delete(deletedContact);
		Thread.sleep(1000); // Задержка в 1 секунду
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before));
	}
}
