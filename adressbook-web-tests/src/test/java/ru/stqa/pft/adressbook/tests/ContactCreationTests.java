package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.goTo().homePage();
		Contacts before = app.contact().all();
		ContactDate contact = new ContactDate().withFirstname("firstname1").withLastname("lastname1")
						.withPhone("phone1").withEmail("e-mail1");
		app.contact().create(contact, true);
		// Сравнение размеров множеств
		Assert.assertEquals(app.contact().count(), before.size() + 1);
		Contacts after = app.contact().all();
		// Добавление максимального ID для контакта
		//contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

		int maxId = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
		contact.withId(maxId);
		assertThat(after, equalTo(before.withAdded(contact)));
	}
}
