package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends TestBase {


	@Test
	public void testContactPhones() {
		app.goTo().homePage();
		if (!app.contact().isThereAContact()) {
			app.contact().create(new ContactDate().withFirstname("firstname1").withLastname("lastname1")
							.withAddress("Бульвар Олимпийский").withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
							.withEmail("e-mail1").withEmail2("e-mail12").withEmail3("e-mail13"), true);
		}

		app.goTo().homePage();
		ContactDate contact = app.contact().all().iterator().next();
		ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

		assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
		assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
	}

	private String mergePhones(ContactDate contact) {
		/*
		String result = "";
		if (contact.getHomePhone() != null) {
			result = result + contact.getFirstname();
		}
		return result;
	  */
		return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
						.stream().filter(s -> !s.equals(""))
						.map(ContactPhoneTests::cleaned)
						.collect(Collectors.joining("\n"));
	}

	private String mergeEmails(ContactDate contact) {
		/*
		String result = "";
		if (contact.getHomePhone() != null) {
			result = result + contact.getFirstname();
		}
		return result;
	  */
		return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
						.stream().filter(s -> !s.equals(""))
						.collect(Collectors.joining("\n"));
	}

	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}
