package SCL_Issue.File_Upload;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import Pagefactory.utility;


public class LoginPage extends utility
{
	public WebDriver wd;

	@FindBy(id = "userName")
	WebElement username;

	@FindBy(id = "loginPassword")
	WebElement password;

	@FindBy(xpath = "//select[@id='rooms']")
	WebElement ddproject ;

	@FindBy(xpath = "//button[@id='submitid']") 
	WebElement btnlogin;
	
	@FindBy(xpath = "//div[@id='header-main']")
	WebElement header;
	
	@FindBy(xpath = "//span[@id='loginError']")
	WebElement lbllogin;
	
	@FindBy(xpath = "//button[@id='cvModelLoginValidationOk']")
	WebElement sesionmgr;
	
	@FindBy(xpath = "//div[@class='nameValidation']")
	WebElement lblusername;
	
	@FindBy(xpath = "//div[@class='passwordValidation']")
	WebElement lblpwd;

	@FindBy(xpath = "//span[@id='cvModelLoginValidationMessage']")
	WebElement validationalert;

	public LoginPage(WebDriver wd) 
	{
		this.wd = wd;
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		PageFactory.initElements(wd, this);
	}

	

	public void login(String User,String Pass) 
	{
		SoftAssert softAssert = new SoftAssert();
		username.sendKeys(User);
		password.sendKeys(Pass);
	
		
		//For use of visible by text
		//utility.Dropdownbytxt(ddproject, "DMS-SERVER.SUNDYNE");		
		
		//For select by index 
		
		utility.Dropdownbyindex(ddproject, 1);	
		
		//Direct without using method into utility
		
		//Select drop=new Select(ddproject);
		//drop.selectByIndex(1);
		
		btnlogin.click();
		
		
		/*
		String Emplyuser=lblusername.getText();	
		String EmplyPass=lblpwd.getText();
		
		
		
		if(Msg.contains("User does not exist"))
		{
			softAssert.assertAll("Invalid Username Test Pass");
		}
		
		else if (Emplyuser.contains("Please Enter Username"))
		{
			softAssert.assertAll("Blank Username Test Pass");
		}
		
		else if (EmplyPass.contains("Please Enter password"))
		{
			softAssert.assertAll("Blank Password Test Pass");
		}
		
		else 
		{
			*/
		
		
			if(utility.isDisaplyedW(sesionmgr, wd, 5))
			{
				sesionmgr.click();
				System.out.println("Another Session Ended");
			}
			
			
			
			if(utility.isVisible(header, wd, 10))
			{
				System.out.println("Login sucess");
				softAssert.assertAll("Login With Valid data Pass");
				
			}
		

		
		
		
		/*
		
		if(Msg.contains("Invalid Userame"))
		{
			softAssert.assertAll("Invalid Username");
		}
		
		*/
		
		else  
		{
			String Msg=lbllogin.getText();
			System.out.println("Login failed with reason:- "+Msg);	
		}
		
		
		
		//WebDriverWait wait=new WebDriverWait(wd, Duration.ofSeconds(15));
		//wait.until(ExpectedConditions.visibilityOf(header));
		
		
	}
	
}
