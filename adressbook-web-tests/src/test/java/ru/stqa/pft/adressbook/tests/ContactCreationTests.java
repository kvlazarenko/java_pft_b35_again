package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.goTo().homePage();
		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/img.png");
		ContactDate contact = new ContactDate().withFirstname("firstname1").withLastname("lastname1").withPhoto(photo)
						.withAddress("Бульвар Олимпийский").withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
						.withEmail("e-mail1").withEmail2("e-mail12").withEmail3("e-mail13");
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
	@Test(enabled = false)
	public void testCurrentDir(){
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		File photo = new File("src/test/resources/img.jpg");
		System.out.println(photo.getAbsolutePath());
		System.out.println(photo.exists());
	}
}
