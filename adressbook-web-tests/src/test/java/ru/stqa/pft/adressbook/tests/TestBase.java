package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.adressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class TestBase {
	Logger logger = LoggerFactory.getLogger(TestBase.class);
	protected final static ApplicationManager app
					= new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();
	}

	@BeforeMethod
	public void logTestStart(Method m, Object[] p) {
		logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
	}

	@AfterMethod(alwaysRun = true)
	public void logTestStop(Method m) {
		logger.info("Stop test " + m.getName());
	}

	public ApplicationManager getApp() {
		return app;
	}
}
