package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	private final Properties properties;
	private WebDriver wd;
	private String browserName;
	private RegistrationHelper registrationHelper;

	public ApplicationManager(String browserName) {
		this.browserName = browserName;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

		//System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
		//properties.getProperty("webdriver.chrome.driver");

	}

	public void stop() {
		if (wd != null) {
			wd.quit();
		}
	}

	public HttpSession newSession() {
		return new HttpSession(this);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public RegistrationHelper registration() {
		if (registrationHelper == null) {
			registrationHelper = new RegistrationHelper(this);
		}
		return registrationHelper;
	}

	public WebDriver getDriver() {
		if (wd == null) {
			if (browserName == Browser.CHROME.browserName()) {
				wd = new ChromeDriver();
			} else if (browserName == Browser.FIREFOX.browserName()) {
				wd = new FirefoxDriver();
			} else if (browserName == Browser.IE.browserName()) {
				wd = new InternetExplorerDriver();
			}
			wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			wd.get(properties.getProperty("web.baseUrl"));
		}
		return wd;
	}
}

