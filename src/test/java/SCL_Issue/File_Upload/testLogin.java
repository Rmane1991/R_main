package SCL_Issue.File_Upload;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pagefactory.utility;

public class testLogin extends utility
{
	WebDriver wd = utility.startBrowser();

	LoginPage lg = new LoginPage(wd);
	NewDocumentPage nd = new NewDocumentPage(wd);

	//@BeforeMethod
	@Test(priority = 0)
	public void Login() throws IOException 
	{
		lg.login(); 
	}
	
	@Test(priority = 1)
	public void createworddoc() throws IOException, InterruptedException 
	{
		System.out.println("Upload Word Started");
		nd.worddoc();
	}
	
	@Test(priority = 2)
	public void createpdf() throws IOException, InterruptedException 
	{
		System.out.println("Upload Pdf Started");
		nd.pdf();
	}
	
	@Test(priority = 3)
	public void MultiplePdf() throws IOException, InterruptedException 
	{
		System.out.println("Upload Word Started");
		nd.MultiplePdf();
	}
	
	@Test(priority = 4)
	public void quit() 
	{
		System.out.println("Webdriver Quit");
		wd.quit();
	}
}
