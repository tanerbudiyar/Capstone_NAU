package Utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	private Driver() {
	}

	public static synchronized WebDriver getDriver() {
		if (tlDriver.get() == null) {
			String browserName = ConfigReader.getProperty("browser").toLowerCase();
			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver());
			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver());
			} else {
				System.out
						.println("Browser name " + browserName + " is not found. Please pass the correct browser...!");
			}

		}

		return tlDriver.get();

	}
public static void closeDriver() {
	
	if(tlDriver!= null) {
		tlDriver.get().quit();
		tlDriver.remove();
	}
}

	public static void main(String[] args) {
		Driver.getDriver().get(ConfigReader.getProperty("url"));

	}

}
