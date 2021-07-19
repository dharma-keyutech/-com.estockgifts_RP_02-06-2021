package Smoke_Test;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.estockgifts.base.base_class;

public class Login_WindowHandler extends base_class {
	protected static String Get_otp;

	@Test

	public static void Window_handle() throws IOException, InterruptedException, AWTException {

		// To read The Property File
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/data.properties");
		prop.load(ip);
		try {
			launchBrowser(prop.getProperty("Browser"));
			Thread.sleep(5000);

			sendURL(prop.getProperty("QAUrl"));

			// driver.get("https://qaupgrade.estockgifts.com");
			click(LoginButton);
			click(Login_As_Member);
			sendValue(Email_Address, prop.getProperty("SenderEmail"));
			click(Get_OTP);

			// open a new tab and perform a new action on it

			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			sendURL(prop.getProperty("Yopmail"));
			driver.manage().window().maximize();
			Thread.sleep(5000);
			sendValue(Uname, prop.getProperty("SenderEmail"));
			click(Arrow);

			Thread.sleep(2000);

			// refresh Button
			click(Refresh_Btn);

			Thread.sleep(5000);

			// Thread.sleep(2000);

			driver.switchTo().parentFrame();
			WebElement frame = driver.findElement(By.id("ifmail"));
			driver.switchTo().frame(frame);

			Thread.sleep(2000);
			System.out.println("Select Particular Text.....");

			Thread.sleep(2000);
			WebElement OTP = driver.findElement(By.xpath(
					"//*[@id='mail']/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/p[1]"));
			Get_otp = OTP.getText();

			System.out.println(Get_otp);
			System.out.println(driver.getTitle());

			driver.switchTo().window(tabs.get(0));
			System.out.println(driver.getTitle());

			sendValue(Enter_OTP, Get_otp);
			click(Submit_OTP);

			System.out.println("User"+(prop.getProperty("SenderEmail") +"Sucessfully Logged-in......"));
			
			
			Thread.sleep(5000);

			//driver.quit();
			
			quitBrowser();

//String exp=driver.getTitle();

//Assert.assertEquals( prop.getProperty("SenderEmail"),exp);

		} catch (Exception e) {

			/* logger.error("Test Fail", e); */
			// onTestFailure();
			quitBrowser();
			Assert.fail("");

		}

	}

}
