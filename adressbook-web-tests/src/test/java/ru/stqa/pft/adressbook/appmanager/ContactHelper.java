package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactDate;

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

	public void selectContact() {
		click(By.name("selected[]"));
	}

	public void deleteSelectedContact() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}

	public void initContactModification() {
		click(By.xpath("//img[@alt='Edit']"));
	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public void createContact(ContactDate contact,boolean creation) {
		initContactCreation();
		fillContactForm(contact, creation);
		submitContactCreation();
		returnToHomePage();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}
}



