package SCL_Issue.File_Upload;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pagefactory.utility;

public class testLogin {
	utility selUtility = new utility();
	WebDriver wd = utility.startBrowser("chrome", "http://192.168.1.15:8080/CVWeb/cvLgn");

	LoginPage lg = new LoginPage(wd);
	NewDocumentPage nd = new NewDocumentPage(wd);

	@BeforeMethod
	public void lg() 
	{
		lg.login("rajendra", "pass_123");
	}

	@Test(priority = 1)
	public void createworddoc() throws IOException, InterruptedException 
	{
		nd.worddoc();
	}

	@AfterMethod
	public void quit() 
	{
		wd.quit();

	}
}
