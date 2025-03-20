package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;

import java.util.List;

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
		type(By.name("home"), contactDate.getPhone());
		type(By.name("email"), contactDate.getEmail());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByIndex(0);
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

	private Contacts contactCash = null;

	public Contacts all() {
		if (contactCash != null) {
			return new Contacts(contactCash);
		}
		contactCash = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			String lastname = element.findElements(By.tagName("td")).get(1).getText();
			String firstname = element.findElements(By.tagName("td")).get(2).getText();
			Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			//ContactDate contact = new ContactDate(firstname, lastname, null, null);
			contactCash.add(new ContactDate().withId(id).withFirstname(firstname).withLastname(lastname));
		}
		return new Contacts(contactCash);
	}
}



