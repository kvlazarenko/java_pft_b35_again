package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactInGroupsTests extends TestBase {
	@BeforeMethod
	public void preconditions() {
		Groups groups = app.db().groups();

		if (groups.size() == 0) {
			app.goTo().groupPage();
			app.group().create(new GroupDate().withName("test1"));
		}
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
		Contacts contacts = app.db().contacts();
		if (app.contact().findContactWithoutGroup(contacts) == null) {
			app.contact().create(new ContactDate()
							.withFirstname("firstname"), true);
			app.goTo().homePage();
		}
	}

	@Test
	public void testAddContactInGroups() {
		Groups groups = app.db().groups();
		Contacts contacts = app.db().contacts();
		ContactDate contactWithoutGroup = app.contact().findContactWithoutGroup(contacts);
		Integer contactId = contactWithoutGroup.getId();
		GroupDate selectedGroup = groups.iterator().next();
		app.contact().addContactToGroup(contactId, selectedGroup.getId());
		Contacts contactAfter = app.db().getContactById(contactId);
		ContactDate contactWithGroup = contactAfter.iterator().next();
		assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(selectedGroup)));

	}
}
