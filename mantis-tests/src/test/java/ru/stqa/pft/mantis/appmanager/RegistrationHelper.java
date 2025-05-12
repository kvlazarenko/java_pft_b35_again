package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

	public RegistrationHelper(ApplicationManager app) {
		super(app);
	}

	public void start(String username, String email) {
		wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
		type(By.name("username"), username);
		type(By.name("email"), email);
		click(By.cssSelector("input[value='Signup']"));
	}

	public void finish(String confirmationLink, String password) {
		wd.get(confirmationLink);
		type(By.name("password"), password);
		type(By.name("password_confirm"), password);
		click(By.cssSelector("input[value='Update User']"));
	}

	public void resetPasswordUser(String username, String password, String usernameChange) {
		type(By.name("username"), username);
		type(By.name("password"), password);
		click(By.cssSelector("input[value='Login']"));
		click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']"));
		click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
		click(By.linkText(usernameChange));
		click(By.xpath("//input[@value='Reset Password']"));
		}
}
