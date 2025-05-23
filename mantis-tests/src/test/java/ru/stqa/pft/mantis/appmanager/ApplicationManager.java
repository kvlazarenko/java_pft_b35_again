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


	private final String browserName;
	private RegistrationHelper registrationHelper;
	private FtpHelper ftp;
	private MailHelper mailHelper;
	private JamesHelper jamesHelper;
	private NavigationHelper navigationHelper;
	private SessionHelper sessionHelper;
	private DbHelper dbHelper;
	private SoapHelper soapHelper;

	public ApplicationManager(String browserName) {
		this.browserName = browserName;
		properties = new Properties();
	}


	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
		System.setProperty("webdriver.gecko.driver", "C:\\Windows\\System32\\geckodriver.exe");
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

	public FtpHelper ftp() {
		if (ftp == null) {
			ftp = new FtpHelper(this);
		}
		return ftp;
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
			//wd.get(properties.getProperty("web.baseUrl"));
		}
		return wd;
	}

	public MailHelper mail() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}

	public JamesHelper james() {
		if (jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}

	public NavigationHelper navigationHelper() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public SessionHelper session() {
		if (sessionHelper == null) {
			sessionHelper = new SessionHelper(this);
		}
		return sessionHelper;
	}

	public DbHelper db() {
		if (dbHelper == null) {
			dbHelper = new DbHelper(this);
		}
		return dbHelper;
	}

	public SoapHelper soap() {
		if (soapHelper == null) {
			soapHelper = new SoapHelper(this);
		}
		return soapHelper;
	}
}
