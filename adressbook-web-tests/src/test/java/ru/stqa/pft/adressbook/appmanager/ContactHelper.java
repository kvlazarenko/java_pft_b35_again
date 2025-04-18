package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void submitContactCreation() {
		click(By.name("submit"));
	}

	public void fillContactForm(ContactDate contactDate, boolean creation) {
		type(By.name("firstname"), contactDate.getFirstname());
		type(By.name("lastname"), contactDate.getLastname());
		attach(By.name("photo"), contactDate.getPhoto());
		type(By.name("address"), contactDate.getAddress());
		type(By.name("home"), contactDate.getHomePhone());
		type(By.name("mobile"), contactDate.getMobilePhone());
		type(By.name("work"), contactDate.getWorkPhone());
		type(By.name("email"), contactDate.getEmail());
		type(By.name("email2"), contactDate.getEmail2());
		type(By.name("email3"), contactDate.getEmail3());


		if (creation) {
			if (contactDate.getGroups().size() > 0) {
				Assert.assertTrue(contactDate.getGroups().size() == 1);
				new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroups().iterator().next().getName());
			}
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}
//		if (creation) {
//			new Select(wd.findElement(By.name("new_group"))).selectByIndex(0);
//		} else {
//			Assert.assertFalse(isElementPresent(By.name("new_group")));
//		}
//	}


	public void addContactToGroup(int contactId, int groupId) {
		homePage();
		selectContactById(contactId);
		selectGroup(groupId);
		clickAdd();
	}
	public void homePage() {
		click(By.linkText("home"));
	}
	public void returnToHomePage() {
		click(By.linkText("home page"));
	}
	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}
	private void selectGroup(int Id) {
		click(By.xpath("(//select[@name='to_group']/option[@value='" + Id + "'])"));
	}
	private void clickAdd() {
		click(By.xpath("(//input[@name='add'])"));
	}
	public void deleteSelectedContact() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}

	public void initContactModification(int id) {
		wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public void create(ContactDate contact, boolean creation) {
		initContactCreation();
		fillContactForm(contact, creation);
		submitContactCreation();
		contactCash = null;
		returnToHomePage();
	}

	public void modify(ContactDate contact) {
		selectContactById(contact.getId());
		initContactModification(contact.getId());
		fillContactForm(contact, false);
		submitContactModification();
		contactCash = null;
		returnToHomePage();
	}

	public void delete(ContactDate contact) {
		selectContactById(contact.getId());
		deleteSelectedContact();
		contactCash = null;
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public ContactDate infoFromEditForm(ContactDate contact) {
		initContactModificationById(contact.getId());
		String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getText();
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		String email = wd.findElement(By.name("email")).getAttribute("value");
		String email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		return new ContactDate().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
						.withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
						.withEmail(email).withEmail2(email2).withEmail3(email3);
	}

	private void initContactModificationById(int id) {
		//WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		//WebElement row = checkbox.findElement(By.xpath("./../.."));
		//List<WebElement> cells = row.findElements(By.tagName("td"));
		//cells.get(7).findElement(By.tagName("a")).click();

		wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
	}
	//wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).	click();
//wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).	click();


	private Contacts contactCash = null;

	public Contacts all() {
		if (contactCash != null) {
			return new Contacts(contactCash);
		}
		contactCash = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			String lastname = cells(element).get(1).getText();
			String firstname = cells(element).get(2).getText();
			String address = cells(element).get(3).getText();
			String allEmails = cells(element).get(4).getText();
			String allPhones = cells(element).get(5).getText();
			contactCash.add(new ContactDate().withId(id).withFirstname(firstname).withLastname(lastname)
							.withAddress(address).withAllemail(allEmails).withAllPhones(allPhones));
		}
		return new Contacts(contactCash);
	}

	private List<WebElement> cells(WebElement element) {
		return element.findElements(By.tagName("td"));
	}

	public ContactDate findContactWithoutGroup(Contacts contacts) {
		for (ContactDate contact : contacts) {
			Set<GroupDate> contactInGroup = contact.getGroups();
			if (contactInGroup.size() == 0) {
				return contact;
			}
		}
		return null;
	}

	public ContactDate findContactWithGroup(Contacts contacts) {
		for (ContactDate contact : contacts) {
			Set<GroupDate> contactInGroup = contact.getGroups();
			if (contactInGroup.size() > 0) {
				return contact;
			}
		}
		return null;
	}
	public void selectAllGroup() {
		click(By.xpath("(//select[@name='group']/option[text()='[all]'])"));
	}
	public void filterByGroup(int groupId) {
		click(By.xpath("(//select[@name='group']/option[@value='" + groupId + "'])"));
	}
	public void removeContactFromGroup(int contactId, int groupId) {
		filterByGroup(groupId);
		selectContactById(contactId);
		removeFromGroup();
	}
	public void removeFromGroup() {
		click(By.xpath("(//input[@name='remove'])"));
	}
}



