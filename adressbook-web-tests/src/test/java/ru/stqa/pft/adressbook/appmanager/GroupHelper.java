package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.adressbook.model.GroupDate;

public class GroupHelper extends HelperBase{

	public GroupHelper(WebDriver wd) {
		super(wd);
	}

	public void returnToGroupPage() {
		click(By.linkText("group page"));
	}

	public void submitGroupCreation() {
		click(By.name("submit"));
	}


	public void fillGroupForm(GroupDate groupDate) {
		type(By.name("group_name"), groupDate.getName());
		type(By.name("group_header"), groupDate.getHeader());
		type(By.name("group_footer"), groupDate.getFooter());
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}
	public void deleteSelectedGroups() {
		click(By.name("delete"));
	}

	public void selectGroup() {
		click(By.name("selected[]"));
	}

	public void initGroupModification() {
		click(By.name("edit"));
	}

	public void submitGroupModification() {
		click(By.name("update"));
	}
}
