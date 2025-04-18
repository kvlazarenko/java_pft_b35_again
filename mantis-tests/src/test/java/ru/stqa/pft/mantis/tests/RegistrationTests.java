package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
	@Test
	public void testRegistration() {
		app.registration().start("username", "user1@localhost.localdomain");
	}
}
