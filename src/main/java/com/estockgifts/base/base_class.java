package com.estockgifts.base;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.epam.reportportal.message.ReportPortalMessage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base_class implements all_xpaths{

	
	
public static WebDriver driver;

private static final Logger logger = LogManager.getLogger(base_class.class);


//Method to Lauch Browser 


		 public static void launchBrowser(String browser) throws IOException {		
			 try{	
				if (browser.equalsIgnoreCase("IE")) {
					
					WebDriverManager.iedriver().setup();
					InternetExplorerOptions options =new InternetExplorerOptions();
					driver = new InternetExplorerDriver(options);
					System.out.println("IE Browser Opened Sucessfully");
					logger.info("IE Browser Opened Sucessfully");
				} else if (browser.equalsIgnoreCase("Chrome")) {
			
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
			                options.addArguments("--headless");
			                driver = new ChromeDriver(options);
					System.out.println("Chrome Browser Opened Sucessfully");
					logger.info("Chrome Browser Opened Sucessfully");
				}
				else if(browser.equalsIgnoreCase("Firefox")){
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions();
			                options.addArguments("--headless");
			                driver = new FirefoxDriver(options);
					System.out.println("Firefox Browser Opened Sucessfully");
					logger.info("Firefox Browser Opened Sucessfully");
				}
			 }catch(Exception e){
				 System.out.println("Failed to launch Browser.");
				 logger.info("Failed to launch Browser.");
			 }
			 
		
		 }
	//Method to launch application URL
		public static void sendURL(String url) {
			
			driver.navigate().to(url);
			driver.manage().window().maximize();
			System.out.println("Passing URL ");
			logger.info("Passing URL");
		}
		//Method to  Close  Browser 
		public static void quitBrowser(){
			try{
				//driver.close();
				driver.quit();
				System.out.println("Browser Quit Sucessfully");
				logger.info("Browser Quit Sucessfully");
				
			}catch(Exception e){
				System.out.println("Failed to Quite Browser.");
				logger.info("Failed to Quite Browser.");
			}
			
		}
		
		//Method to perform click operation
		public static void click(String contactus){
			
				driver.findElement(By.xpath(contactus)).click();
				System.out.println("Click Operation Performed Sucessfully on the element ");
				logger.info("Click Operation Performed Sucessfully on the element ");
			
		}
		
		//Method to perform sending value to a Textbox
		public static void sendValue(String locator, String testdata) {

			try {			
				driver.findElement(By.xpath(locator)).clear();
				driver.findElement(By.xpath(locator)).sendKeys(testdata);
				System.out.println(testdata +" Data is Passed Sucessfully to input field  ");
				logger.info(testdata +" Data is Passed Sucessfully to input field  ");
			} catch (NoSuchElementException e) {
				System.out.println("Unable to locate and pass the data");
				logger.info("Unable to locate and pass the data");
			}
		}
		
		public static void Payment_Type(String Payment) throws IOException {
			// To read The Property File 
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
			prop.load(ip);
			try {
				if (Payment.equalsIgnoreCase("creditcard")) {
					click(CreditCard);
					System.out.println("Credit/Debit card Selected. ");
					logger.info("Credit/Debit card Selected. ");
					sendValue(Name_On_card, prop.getProperty("Name"));
					sendValue(Card_Number, prop.getProperty("Card_Num"));
					sendValue(Expiry_Date, prop.getProperty("Expiry"));
					sendValue(CVV_Number, prop.getProperty("CVV"));
					click(Terms_Conditions);
					click(Purchase);

				} else if (Payment.equalsIgnoreCase("Paypal")) {
					click(Paypal);
					System.out.println("Paypal Selected. ");
					click(Terms_Conditions);
					click(paypal_Checkout);
				} else if (Payment.equalsIgnoreCase("Estockgifts_Payment")) {
					click(Estockgifts_Payment);
					System.out.println("Estockgifts payment gateway Selected. ");
					logger.info("Estockgifts payment gateway Selected. ");
					click(Terms_Conditions);
					click(Purchase);

				}

			} catch (Exception e) {
				System.out.println("Failed to Select Payment option.");
				logger.info("Failed to select payment option ");
			}

		}
		public static void Certificate_Payment(String Payment) throws IOException {
			// To read The Property File 
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
			prop.load(ip);
			try {
				if (Payment.equalsIgnoreCase("creditcard")) {
					click(Payment_Creditcard);
					System.out.println("Credit/Debit card Selected. ");
					sendValue(creditcard_Name, prop.getProperty("Name"));
					sendValue(creditcard_Number, prop.getProperty("Card_Num"));
					sendValue(creditcard_Expiry_Date, prop.getProperty("Expiry"));
					sendValue(creditcard_CVV, prop.getProperty("CVV"));
					click(Terms_conditons);
					click(purchase_button);

				} else if (Payment.equalsIgnoreCase("Paypal")) {
					click(Payment_Paypal);
					System.out.println("Paypal Selected. ");
					click(Terms_conditons);
					click(purchase_button);
				} else if (Payment.equalsIgnoreCase("Estockgifts_Payment")) {
					click(Estockgifts_Payment);
					System.out.println("Estockgifts payment gateway Selected. ");
					logger.info("Estockgifts payment gateway Selected. ");
					click(Terms_conditons);
					click(purchase_button);

				}

			} catch (Exception e) {
				System.out.println("Failed to Select Payment option.");
				logger.info("Failed to select payment option ");
			}

		}

		public static void Crypto_Type(String Cryptocard) throws IOException {
			try {
				if (Cryptocard.equalsIgnoreCase("Bitcoin")) {
					click(Bitcoin);
					System.out.println("Bitcoin Selected. ");
					logger.info("Bitcoin Selected. ");

				} else if (Cryptocard.equalsIgnoreCase("Ethereum")) {
					click(Ethereum);
					System.out.println("Ethereum Selected. ");
					logger.info("Ethereum Selected. ");

				} else if (Cryptocard.equalsIgnoreCase("Ripple")) {
					click(Ripple);
					System.out.println("Ripple Selected. ");
					logger.info("Ripple Selected. ");
				}
			} catch (Exception e) {
				System.out.println("Failed to Select Crypto type options.");
				logger.info("Failed to Select Crypto type options. ");
			}

		}

		// Locating upload image
		public static void Uploadimage(String locator, String image) {

			try {
				driver.findElement(By.xpath(locator)).sendKeys(image);

			} catch (NoSuchElementException e) {
				System.out.println("Unable to locate and pass the data");
				logger.info("Unable to locate image and pass the data");
			}
		}
		// Method to Upload Files
				public static void Upload(String Locator, String File) throws Exception {

					try {
//						driver.findElement(By.name(Locator));
						driver.findElement(By.xpath(Locator)).click();
						Thread.sleep(10000);
						StringSelection IMGsrc = new StringSelection(File);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(IMGsrc, null);
						Robot r = new Robot();
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_C);
						r.keyRelease(KeyEvent.VK_CONTROL);
						r.keyRelease(KeyEvent.VK_C);
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_V);
						r.keyRelease(KeyEvent.VK_CONTROL);
						r.keyRelease(KeyEvent.VK_V);
						r.keyPress(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_ENTER);			
						
					} catch (Exception e) {
						System.out.println("Unable to Upload File ");
					}

				}

		public static void Select_Dropdown(String locator, String id) throws IOException {
			try {

				driver.findElement(By.xpath(locator)).click();
				driver.findElement(By.xpath(id)).click();

			} catch (Exception exp) {
				System.out.println("cause is:" + exp.getCause());
				logger.info("Select Expire Date Faild");
				

			}
		}

		
		// Take screenshots
				public static void onTestFailure() {
					   
					 ReportPortalMessage message = null;
					    try {
					    	TakesScreenshot ts = (TakesScreenshot)driver;
					    	File srcFile = ts.getScreenshotAs(OutputType.FILE);
					    	java.util.Date d= new java.util.Date();
					    	 org.apache.commons.io.FileUtils.copyFile(srcFile, new File("./ScreenShots/"+d.toString().replace(":", "_")+".png"));
					    	String rp_message = "test message for ReportPortal";
					        message = new ReportPortalMessage(srcFile, rp_message);
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
					    logger.error(message);
				   
				}

}
