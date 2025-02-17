package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	protected WebDriver wd;
	private SessionHelper sessionHelper;
	private GroupHelper groupHelper;
	private NavigationHelper navigationHelper;
	private ContactHelper contactHelper;
	private String browserName;

	public ApplicationManager(String browserName) {

		this.browserName = browserName;
	}

	public void init() {
		if (browserName == Browser.CHROME.browserName()){
			wd = new ChromeDriver();
		} else if (browserName == Browser.FIREFOX.browserName()) {
			wd = new FirefoxDriver();
		} else if (browserName == Browser.IE.browserName()) {
			wd = new InternetExplorerDriver();
		}
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");

		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/group.php");
		sessionHelper = new SessionHelper(wd);
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login("admin", "secret");
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
		public GroupHelper getGroupHelper() {
		return groupHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}

	public SessionHelper getSessionHelper() {
		return sessionHelper;
	}
	public ContactHelper getContactHelper() {
		return contactHelper;
	}
}
