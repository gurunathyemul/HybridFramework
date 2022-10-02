package genericLib;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public Propertyfille pdata=new Propertyfille();
	public WebDriverUtilies driverutilies=new WebDriverUtilies();
	@Parameters(value = {"browserName"})
	@BeforeMethod
	public void openAPP(@Optional("chrome") String browserName) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.edgedriver().setup();
		if(browserName.equals("chrome")) {
		driver=new ChromeDriver();
		}
		else {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(pdata.getPropertyfile("url"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeApp(ITestResult res) throws IOException {
		int status = res.getStatus();
		String name = res.getName();
		
		if(status==2) {
			Sceenshot s=new Sceenshot();
			s.getPhoto(driver, name);
		}
		
		driver.quit();
	}
	
	

}
