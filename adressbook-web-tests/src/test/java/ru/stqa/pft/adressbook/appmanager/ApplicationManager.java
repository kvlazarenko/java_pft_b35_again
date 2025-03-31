package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	private final Properties properties;
	protected WebDriver wd;
	private SessionHelper sessionHelper;
	private GroupHelper groupHelper;
	private NavigationHelper navigationHelper;
	private ContactHelper contactHelper;
	private String browserName;

	public ApplicationManager(String browserName) {

		this.browserName = browserName;
		properties = new Properties();

	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
		if (browserName == Browser.CHROME.browserName()) {
			wd = new ChromeDriver();
		} else if (browserName == Browser.FIREFOX.browserName()) {
			wd = new FirefoxDriver();
		} else if (browserName == Browser.IE.browserName()) {
			wd = new InternetExplorerDriver();
		}
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");

		wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wd.get(properties.getProperty("web.baseUrl"));
		sessionHelper = new SessionHelper(wd);
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
	}

	public void stop() {
		wd.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public GroupHelper group() {
		return groupHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}

	public SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	public ContactHelper contact() {
		return contactHelper;
	}
}
