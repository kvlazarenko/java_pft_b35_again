package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.adressbook.tests.TestBase.app;

public class RemoteContactFromGroupsTests extends TestBase {
	@BeforeMethod
	public void preconditions() {

		if (app.db().groups().size() == 0) {
			app.goTo().groupPage();
			app.group().create(new GroupDate().withName("test1"));
		}
		Groups groups = app.db().groups();
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
							.withEmail3("e-mail13").inGroup(groups.iterator().next()), true);
		}
		Contacts contacts = app.db().contacts();
		if (app.contact().findContactWithGroup(contacts) == null) {
			app.goTo().homePage();
			Contacts contactsUI = app.contact().all();
			ContactDate selectedContactUI = contactsUI.iterator().next();
			GroupDate selectedGroup = groups.iterator().next();
			app.contact().addContactToGroup(selectedContactUI.getId(), selectedGroup.getId());
			app.goTo().homePage();
		}
	}

	@Test
	public void testRemoteContactFromGroups() {

		Contacts contacts = app.db().contacts();
		ContactDate contactWithGroup = app.contact().findContactWithGroup(contacts);
		int contactId = contactWithGroup.getId();
		GroupDate group = contactWithGroup.getGroups().iterator().next();
		int groupId = group.getId();
		Groups deletedGroup = app.db().getGroupById(groupId);
		GroupDate deletedGroupData = deletedGroup.iterator().next();
		app.goTo().homePage();
		app.contact().removeContactFromGroup(contactWithGroup.getId(), group.getId());

		Contacts contactAfter = app.db().getContactById(contactId);
		ContactDate contactWithoutGroup = contactAfter.iterator().next();
		assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(deletedGroupData)));
	}
}
