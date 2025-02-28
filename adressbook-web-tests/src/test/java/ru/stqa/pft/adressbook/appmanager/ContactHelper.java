package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactDate;

import java.util.ArrayList;
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

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void deleteSelectedContact() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}

	public void initContactModification(int index) {
		wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public void createContact(ContactDate contact, boolean creation) {
		initContactCreation();
		fillContactForm(contact, creation);
		submitContactCreation();
		returnToHomePage();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public List<ContactDate> getContactList() {
		List<ContactDate> contacts = new ArrayList<ContactDate>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			String lastname = element.findElements(By.tagName("td")).get(1).getText();
			String firstname = element.findElements(By.tagName("td")).get(2).getText();
			Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			ContactDate contact = new ContactDate(id, firstname, lastname, null, null, null);
			contacts.add(contact);
		}
		return contacts;
	}
}



