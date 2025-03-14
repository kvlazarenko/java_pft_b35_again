package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.adressbook.appmanager.ApplicationManager;

public class TestBase {

	protected final static ApplicationManager app = new ApplicationManager(Browser.CHROME.browserName());

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		app.init();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();
	}

	public ApplicationManager getApp() {
		return app;
	}
}
