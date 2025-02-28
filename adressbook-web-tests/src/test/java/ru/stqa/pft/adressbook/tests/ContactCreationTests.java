package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.*;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.getNavigationHelper().gotoHomePage();
		List<ContactDate> before = app.getContactHelper().getContactList();
		ContactDate contact = new ContactDate("firstname1", "lastname1"
						, "phone1", "e-mail1", "test1");
		app.getContactHelper().createContact(contact, true);
		List<ContactDate> after = app.getContactHelper().getContactList();

		// Сравнение размеров списков
		Assert.assertEquals(before.size() + 1, after.size());

		// Добавление максимального ID для контакта
		contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    // Добавление контакта в список before
		before.add(contact);

		// Сортировка списков before и after
		before.sort(Comparator.comparing(ContactDate::getId));
		after.sort(Comparator.comparing(ContactDate::getId));

		// Сравнение списков before и after
		Assert.assertEquals(before, after);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
	}
}
