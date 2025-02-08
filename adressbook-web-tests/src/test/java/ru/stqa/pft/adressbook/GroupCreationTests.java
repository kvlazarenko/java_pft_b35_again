package ru.stqa.pft.adressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class GroupCreationTests {
	private WebDriver wb;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
		wb = new ChromeDriver();
		wb.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wb.get("http://localhost/addressbook/group.php");
		login("admin", "secret");
	}

	private void login(String username, String password) {
		wb.findElement(By.name("user")).click();
		wb.findElement(By.name("user")).clear();
		wb.findElement(By.name("user")).sendKeys(username);
		wb.findElement(By.name("pass")).click();
		wb.findElement(By.name("pass")).clear();
		wb.findElement(By.name("pass")).sendKeys(password);
		wb.findElement(By.xpath("//input[@value='Login']")).click();
	}

	@Test
	public void testGroupCreation() throws Exception {
		gotoGroupPage();
		initGroupCreation();
		fillGroupForm(new GroupDate("test1", "test2", "test3"));
		submitGroupCreation();
		gotoGroupPage();
		returnToGoPage();
	}

	private void returnToGoPage() {
		wb.findElement(By.linkText("Logout")).click();
	}

	private void submitGroupCreation() {
		wb.findElement(By.name("submit")).click();
	}

	private void fillGroupForm(GroupDate groupDate) {
		wb.findElement(By.name("group_name")).click();
		wb.findElement(By.name("group_name")).clear();
		wb.findElement(By.name("group_name")).sendKeys(groupDate.getName());
		wb.findElement(By.name("group_header")).click();
		wb.findElement(By.name("group_header")).clear();
		wb.findElement(By.name("group_header")).sendKeys(groupDate.getHeader());
		wb.findElement(By.name("group_footer")).click();
		wb.findElement(By.name("group_footer")).clear();
		wb.findElement(By.name("group_footer")).sendKeys(groupDate.getFooter());
	}

	private void initGroupCreation() {
		wb.findElement(By.name("new")).click();
	}

	private void gotoGroupPage() {
		wb.findElement(By.linkText("groups")).click();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		wb.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			wb.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			wb.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}
