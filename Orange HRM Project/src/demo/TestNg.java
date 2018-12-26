package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNg {

	public WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		// Open GoogleChrome Driver
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void openURL() {
		// Open Orange HRM Application
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals("OrangeHRM", driver.getTitle());
	}

	@Test(priority = 2)
	public void searchUser() {

		// Type user name and password to login
		driver.findElement(By.xpath("//*[@id=\"txtUsername\"]")).sendKeys("Admin");
		driver.findElement(By.xpath("//*[@id=\"txtPassword\"]")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();

		Actions act = new Actions(driver);

		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/b")));
		act.perform();

		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu_admin_UserManagement\"]")));
		act.perform();

		driver.findElement(By.xpath("//*[@id=\"menu_admin_viewSystemUsers\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"searchSystemUser_userName\"]")).sendKeys("thomas.fleming");

		driver.findElement(By.name("searchSystemUser[userType]"));
		WebElement objWeb1 = driver.findElement(By.name("searchSystemUser[userType]"));
		Select objSelect1 = new Select(objWeb1);
		objSelect1.selectByValue("2");

		driver.findElement(By.xpath("//*[@id=\"searchSystemUser_employeeName_empName\"]")).sendKeys("Thomas Fleming");

		driver.findElement(By.name("searchSystemUser[status]"));
		WebElement objWeb2 = driver.findElement(By.name("searchSystemUser[status]"));
		Select objSelect2 = new Select(objWeb2);
		objSelect2.selectByValue("1");

		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();

		System.out.println();
		System.out.println(driver.findElement(By.xpath("//*[@id=\"search-results\"]/div")).getText());
		System.out.println();

		// Verify Searching User
		Assert.assertEquals("thomas.fleming",
				driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[2]")).getText());

		System.out.println("Searching User Successful");

	}

	@Test(priority = 3)
	public void addUser() {

		driver.findElement(By.xpath("//*[@id=\"btnAdd\"]")).click();

		driver.findElement(By.name("systemUser[userType]"));
		WebElement objWeb3 = driver.findElement(By.name("systemUser[userType]"));
		Select objSelect3 = new Select(objWeb3);
		objSelect3.selectByValue("2");

		driver.findElement(By.xpath("//*[@id=\"systemUser_employeeName_empName\"]")).sendKeys("Steven Edwards");

		driver.findElement(By.xpath("//*[@id=\"systemUser_userName\"]")).sendKeys("farahkhaled");

		driver.findElement(By.name("systemUser[status]"));
		WebElement objWeb4 = driver.findElement(By.name("systemUser[status]"));
		Select objSelect4 = new Select(objWeb4);
		objSelect4.selectByValue("1");

		driver.findElement(By.xpath("//*[@id=\"systemUser_password\"]")).sendKeys("Zxc123 $2018mn");

		driver.findElement(By.xpath("//*[@id=\"systemUser_confirmPassword\"]")).sendKeys("Zxc123 $2018mn");

		driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id=\"searchSystemUser_userName\"]")).sendKeys("farahkhaled");

		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();

		// Verify Adding User
		Assert.assertEquals("farahkhaled",
				driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[2]")).getText());

		System.out.println("Adding User Successful");

	}

	@AfterMethod
	public void closeBrowser() {
		// Close GoogleChrome Driver
		driver.close();
		System.out.print("\n");
		System.out.println("Automation Testing has been succeeded");
	}

}
