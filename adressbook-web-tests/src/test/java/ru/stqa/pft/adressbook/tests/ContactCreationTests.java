package ru.stqa.pft.adressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
	@DataProvider
	public Iterator<Object[]> validContactFromXml() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
		String xml = "";
		String line = reader.readLine();
		while (line != null) {
			xml += line;
			line = reader.readLine();
		}
		XStream xstream = new XStream();
		xstream.processAnnotations(ContactDate.class);
		List<ContactDate> contacts = (List<ContactDate>) xstream.fromXML(xml);
		return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
	}

	@Test(dataProvider = "validContactFromXml")
	public void testContactCreation(ContactDate contact) throws Exception {
		app.goTo().homePage();
		Contacts before = app.contact().all();
		app.contact().create(contact, true);
		Assert.assertEquals(app.contact().count(), before.size() + 1);
		Contacts after = app.contact().all();
		int maxId = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
		contact.withId(maxId);
		assertThat(after, equalTo(before.withAdded(contact)));
	}

	@Test(enabled = false)
	public void testCurrentDir() {
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		File photo = new File("src/test/resources/img.jpg");
		System.out.println(photo.getAbsolutePath());
		System.out.println(photo.exists());
	}
}
