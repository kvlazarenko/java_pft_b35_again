package ru.stqa.pft.mantis.tests;

import org.testng.annotations.*;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTests extends TestBase {
	//@BeforeMethod
	public void startMailServer() {
		app.mail().start();
	}

	@Test
	public void testRegistration() throws MessagingException, IOException {
		long now = System.currentTimeMillis();
		String user = String.format("user1%s", now);
		String password = "password";
		String email = String.format("user1%s@localhost", now);
		app.james().createUser(user, password);
		app.registration().start(user, email);
		//List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
		List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
		String confirmationLink = findConfirmationLink(mailMessages, email);
		app.registration().finish(confirmationLink, password);
		assertTrue(app.newSession().login(user, password));
	}

	@Test
	public void testChangeUserPassword() throws IOException, MessagingException {

		long now = System.currentTimeMillis();
		String user = String.format("user%s", now);
		String password = "password";
		String email = String.format("user%s@localhost", now);
		app.james().createUser(user, password);
		app.registration().start(user, email);
		List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
		String confirmationLink = findConfirmationLinkCreateUser(mailMessages, email);
		app.registration().finish(confirmationLink, password);
		app.session().login("administrator", "root");
		app.navigationHelper().goToManageUsersPage();
		app.navigationHelper().selectUser(user);
		app.navigationHelper().resetPassword();
		List<MailMessage> mailMessagesResetPassword = app.james().waitForMailMoreOne(user, password, 60000);
		String confirmationLinkResetPassword = findConfirmationLink(mailMessagesResetPassword, email);
		String newPassword = "pas14";
		app.registration().finish(confirmationLinkResetPassword, newPassword);
		app.session().login(user, newPassword);
		assertTrue(app.newSession().login(user, newPassword));
	}

	private String findConfirmationLinkCreateUser(List<MailMessage> mailMessages, String email) {
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);
	}

	private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);
	}


	//@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}
}
