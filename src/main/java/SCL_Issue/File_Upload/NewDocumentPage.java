package SCL_Issue.File_Upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Pagefactory.utility;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NewDocumentPage extends utility
{
	public WebDriver wd;
	public NewDocumentPage(WebDriver wd) 
	{
		this.wd = wd;
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(xpath = "//div[@id='addPagesDropDown']")
	WebElement clicknewdocument;
	
	@FindBy(xpath = "//input[@id='viewDocumentAddPages']")
	WebElement browse;
	
	@FindBy (xpath = "//div[@id='addPagesDropDown']")
	WebElement plusion;
	
	@FindBy(linkText = "New Word Document")
	WebElement worddoc;
	
	@FindBy (xpath = "//input[@id='createDocuemtnLocation']")
	WebElement destinationfolder;
	
	@FindBy(xpath = "//select[@id='docTypeList']")
	WebElement ddDocumenttype;
	
	@FindBy(xpath = "//input[@id='newTemplateFileName']")
	WebElement txtnewfilename;
	
	@FindBy(xpath = "//button[@id='templateOK']")
	WebElement btnok;
	
	@FindBy(xpath = "//button[@id='templateCanel']")
	WebElement btncancel;
	
	@FindBy(xpath = "(//div[@id='progressMsg'])[2]")
	WebElement progressbar;
	
	@FindBy(xpath = "//input[@id='createDocuemtnLocation']")
	WebElement menuselectdestination;
	
	@FindBy(linkText = "SCL")
	WebElement selectfolder;
	
	@FindBy(xpath = "//button[@id='navigatorTreeOk']")
	WebElement btnokselectfolder;
	
	@FindBy(xpath = "//button[@id='navigatorTreeCancle']")
	WebElement btncancleselectfolder;
	
	@FindBy(xpath = "(//*[contains(text(), 'SCL')]/ancestor::li/ins[@class='jstree-icon'])[1]")
	WebElement btnplus1;
	
	@FindBy(xpath = "(//*[contains(text(), 'SCL')]/ancestor::li/ins[@class='jstree-icon'])[2]")
	WebElement btnplus2;
	
	@FindBy(linkText ="SCL")
	WebElement txtnewcabinet;
	
	@FindBy(linkText ="SCL")
	WebElement txtnewdrawer;
	
	@FindBy(linkText = "SCLUpload") 
	WebElement txtnewfolder;
	
	@FindBy(xpath = "//select[@id='docTypeList']")
	WebElement dddocumenttype;
	
	@FindBy(xpath = "//input[@id='indices_188']")
	WebElement txtproductnamepresentation;
	
	@FindBy(xpath = "//input[@id='indicesViewText_1']")
	WebElement txtAuthername;
	
	@FindBy(xpath = "//button[@id='createDocumentSubmit']")
	WebElement btncreatedocument;
	
	@FindBy(xpath = "//button[@id='viewCreatedDocument']")
	WebElement btnviewdocument;
	
	@FindBy(xpath = "//div[@id='addPagesDropDown']")
	WebElement btnadddocument;
	
	@FindBy(linkText = "New Word Document")
	WebElement newworddocument;
	
	@FindBy(xpath = "//input[@id='newTemplateFileName']")
	WebElement txtnewdocumentname;
	
	@FindBy(xpath = "//button[@id='templateOK']")
	WebElement btnokcreatedocument;
	
	@FindBy(xpath = "//input[@id='retainBtn']")
	WebElement chkretain;
	
	@FindBy(xpath = "//input[@id='indices_5']")
	WebElement txtreportname;
	
	@FindBy(xpath = "(//div[@class='spinner-border spinner-border-lg'])[2]")
	WebElement loader;
	
	@FindBy(xpath = "//button[@id='modelNewDocument']")
	WebElement btnnewDocument;
	
	@FindBy(xpath = "//a[@id='createDocument']")
	WebElement tabcreatenewdocument;
  
	@SuppressWarnings("resource")
	public void worddoc() throws IOException, InterruptedException  
	{
		Actions a=new Actions(wd);
		btnplus1.click();
		btnplus2.click();
		utility.isVisible(txtnewfolder, wd, 15);
		txtnewfolder.click();
		tabcreatenewdocument.click();
		utility.Dropdownbytxt(dddocumenttype,"CVReports");
		
		utility.isVisible(txtreportname, wd, 15);
		txtreportname.sendKeys("Rajendra");
		chkretain.click();
		
		FileInputStream fis = new FileInputStream("D:\\File\\WALLPAPER-DOCS\\WALLPAPER-DOCS\\filenames.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
	     
		XSSFSheet sheet = wb.getSheet("Sheet2");
		int rowCount = sheet.getPhysicalNumberOfRows()-1;
		  
	    System.out.println("No of Record Found Into Excel :- " + rowCount);
	
	    for(int i=1;i<=rowCount;i++)
		{
		
	    	try {
	    		a.moveToElement(clicknewdocument).perform();
	    		browse.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
	    		Thread.sleep(4000);
	    		
	    		if(utility.isAlertPresent(wd)==true)
	    		{
	    		 wd.switchTo().alert().accept();	
	    		}
	    		else 
	    		{
	    		
	    		}
	    		Thread.sleep(4000);
	    		
	    		btncreatedocument.click();
	    		
	    		utility.isInvisible(loader, wd,10);
	    		btnnewDocument.click();
				
			} catch (Exception e) 
	    	{
				e.printStackTrace();
			}
		
		
		}
	}
		
}
	

