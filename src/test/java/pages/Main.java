package pages;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Main extends Base {

	public Main(WebDriver driver) {
		super(driver);

	}

	public boolean goRegister() throws InterruptedException {
		// Click on registration button
		click(By.id("menu-item-15608"));
		return true;
	}

	public boolean find_Right_Account() throws InterruptedException {

		// Click on "I am" and select the Freelancer or SMB option.
		click(By.cssSelector("#other > div.question.first-question > div"));
		click(By.xpath(
				"//div[@class='dropdown custom-select step-one open']//li[@data-value='freelancer-or-smb'][@class='option']"));
		Thread.sleep(2500);
		click(By.cssSelector("#other > div.question.second-question > div"));
		click(By.xpath("//*[@id=\"other\"]/div[2]/div/div/ul/li[1]"));
		return true;
	}

	public boolean goRegister2() throws InterruptedException {
		// Click on 2nd registration button
		click(By.cssSelector("#plan-1 > div.plan-content > div.plan-top-content > div.plan-button > a"));
		return true;
	}

	public boolean verify_SignUpProcess() throws InterruptedException {

		driver.findElement(By.id("txtFirstName")).sendKeys("Ofir");
		driver.findElement(By.id("txtLastName")).sendKeys("Random");
		driver.findElement(By.id("txtEmail")).sendKeys("RandomOfirMail@mail.com");
		driver.findElement(By.id("txtRetypeEmail")).sendKeys("RandomOfirMail@mail.com");
		click(By.cssSelector(
				"#PersonalDetails > div.slide-form > div > div:nth-child(16) > div.field > button.ui-datepicker-trigger"));
		// Year
		Select drpYear = new Select(driver.findElement(By.className("ui-datepicker-year")));
		drpYear.selectByValue("1996");
		// Month
		Select drpMonth = new Select(driver.findElement(By.className("ui-datepicker-month")));
		drpMonth.selectByValue("0");
		// Day
		List<WebElement> columns = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));

		for (WebElement cell : columns) {
			// Select 13th Date
			if (cell.getText().equals("13")) {
				cell.findElement(By.linkText("13")).click();
				break;
			}
		}
		click(By.id("PersonalDetailsButton"));
		return true;
	}

}
