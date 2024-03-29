package SCL_Issue.File_Upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Pagefactory.utility;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewDocumentPage extends utility {
	public WebDriver wd;

	public NewDocumentPage(WebDriver wd) {
		this.wd = wd;
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//div[@id='addPagesDropDown']")
	WebElement SubMenuNwdocument;

	@FindBy(xpath = "//input[@id='viewDocumentAddPages']")
	WebElement UploadNewFile;

	@FindBy(xpath = "//div[@id='saveAddedPages']")
	WebElement btnSaveDocument;

	@FindBy(xpath = "//span[@id='messageContent42']")
	WebElement lblSaveDocument;

	@FindBy(xpath = "//button[@id='messageButtonOK42']")
	WebElement btnokSaveDocument;

	@FindBy(xpath = "//div[@id='cvDocumentClose']")
	WebElement btnCloseDocument;

	@FindBy(xpath = "(//div[@id='progressMsg'])[2]")
	WebElement DocumentUploadProgressBar;

	@FindBy(xpath = "(//*[contains(text(), 'SCL')]/ancestor::li/ins[@class='jstree-icon'])[1]")
	WebElement IconPlusCabinet;

	@FindBy(xpath = "(//*[contains(text(), 'SCL')]/ancestor::li/ins[@class='jstree-icon'])[2]")
	WebElement IconPlusDrawer;

	@FindBy(linkText = "SCLUpload")
	WebElement FolderNameWord;

	@FindBy(linkText = "SalesReportPDF_07_03")//SalesReportPDF
	WebElement FolderNamePDF;

	@FindBy(xpath = "//input[@id='tableFilter']")
	WebElement searchbarPdfDocument;

	@FindBy(xpath = "//td[@class=' customDocName']")
	WebElement clickDocument;

	@FindBy(xpath = "//select[@id='docTypeList']")
	WebElement ddDocumenttype;

	@FindBy(xpath = "//button[@id='createDocumentSubmit']")
	WebElement btnCreateDocument;

	@FindBy(xpath = "//input[@id='retainBtn']")
	WebElement chkRetain;

	@FindBy(xpath = "//input[@id='indices_5']")
	WebElement txtReportName;

	@FindBy(xpath = "(//div[@class='spinner-border spinner-border-lg'])[2]")
	WebElement DocumentUploadloader;

	@FindBy(xpath = "//button[@id='modelNewDocument']")
	WebElement btnNewDocument;

	@FindBy(xpath = "//span[@id='createDocumentMessage']")
	WebElement FileUploadlblMessage;

	@FindBy(xpath = "//a[@id='createDocument']")
	WebElement SubMenuCreateNewDocument;

	@FindBy(xpath = "//input[@id='indices_46']")
	WebElement txtCompanyName;

	@FindBy(xpath = "//div[@class='e-toast-message']")
	WebElement errorwindow;
	
	@FindBy(xpath="//button[@class='e-small e-lib e-btn e-control e-primary e-toast-danger']")
	WebElement btnCloseError; 
			
	
	
	@SuppressWarnings("resource")

	public void worddoc() throws IOException, InterruptedException {
		Actions a = new Actions(wd);
		IconPlusCabinet.click();
		IconPlusDrawer.click();
		utility.isVisible(FolderNameWord, wd, 15);
		FolderNameWord.click();
		SubMenuCreateNewDocument.click();
		utility.Dropdownbytxt(ddDocumenttype, "CVReports");

		utility.isVisible(txtReportName, wd, 15);
		txtReportName.sendKeys("Rajendra");
		if (chkRetain.isSelected()) {

		} else {
			chkRetain.click();
		}

		FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet("Sheet2");
		int rowCount = sheet.getPhysicalNumberOfRows() - 1;

		System.out.println("No of Record Found Into Excel :- " + rowCount);

		for (int i = 1; i <= rowCount; i++) {

			try {
				Thread.sleep(2000);
				utility.isDisaplyedW(SubMenuNwdocument, wd, 10);
				a.moveToElement(SubMenuNwdocument).perform();
				UploadNewFile.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
				
				Thread.sleep(3000);

				if (utility.isAlertPresent(wd) == true) {
					wd.switchTo().alert().accept();
					Thread.sleep(4000);
				} else 
				{

				}
				

				btnCreateDocument.click();

				utility.isInvisible(DocumentUploadloader, wd, 10);
				String FileUploadStatusMsg = FileUploadlblMessage.getText();

				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.createCell(1);
				FileOutputStream fos = new FileOutputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf_New.xlsx"));

				if (FileUploadStatusMsg.contains("Document created successfully")) {
					cell.setCellValue("File Upload");
					wb.write(fos);

				} else {
					cell.setCellValue("Fail");
					wb.write(fos);
				}

				btnNewDocument.click();
				FileUploadStatusMsg = "";

			} catch (Exception e) 
			{
				if (utility.isDisaplyedW(errorwindow, wd, 5)) 
				{
					System.out.println(sheet.getRow(i).getCell(1).getStringCellValue()+":- This File is zero KB");
					btnCloseError.click();
				}
				continue;

			}

		}
		System.out.println("Its Done");
	}

	public void pdf() throws IOException {

		
		FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf.xlsx"));
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet("Sheet2");
		int rowCount = sheet.getPhysicalNumberOfRows() - 1;

		Actions a = new Actions(wd);
		IconPlusCabinet.click();
		IconPlusDrawer.click();
		utility.isVisible(FolderNamePDF, wd, 15);
		FolderNamePDF.click();
		SubMenuCreateNewDocument.click();
		utility.Dropdownbytxt(ddDocumenttype, "Sales Report");
		
		if (chkRetain.isSelected()) {

		} else 
		{
			chkRetain.click();
		}

		System.out.println("No of Record Found Into Excel :- " + rowCount);

		for (int i = 1; i <= rowCount; i++) {

			try {
				Thread.sleep(2000);
				utility.isDisaplyedW(SubMenuNwdocument, wd, 10);
				a.moveToElement(SubMenuNwdocument).perform();
				String filepath= (System.getProperty("user.dir")+"\\Files\\PDF\\");
				//System.out.println(filepath);
				//System.out.println(filepath+sheet.getRow(i).getCell(1).getStringCellValue());
				UploadNewFile.sendKeys(filepath+sheet.getRow(i).getCell(1).getStringCellValue());
				
				Thread.sleep(2000);

				if (utility.isAlertPresent(wd) == true) 
				{
					wd.switchTo().alert().accept();
					Thread.sleep(4000);
				} 
				else 
				{

				}
				
				utility.isVisible(txtCompanyName, wd, 15);
				txtCompanyName.clear();
				txtCompanyName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());

				btnCreateDocument.click();
				Thread.sleep(3000);
				
				utility.isInvisible(DocumentUploadloader, wd, 10);
				String FileUploadStatusMsg = FileUploadlblMessage.getText();

				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.createCell(2);
				FileOutputStream fos = new FileOutputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf_New.xlsx"));

				if (FileUploadStatusMsg.contains("Document created successfully")) {
					cell.setCellValue("File Upload");
					wb.write(fos);

				} else {
					cell.setCellValue("Fail");
					wb.write(fos);
				}

				btnNewDocument.click();
				FileUploadStatusMsg = "";

			} catch (Exception e) {
				if (utility.isDisaplyedW(errorwindow, wd, 1)) 
				{
					System.out.println(sheet.getRow(i).getCell(1).getStringCellValue()+":- This File is zero KB");
					btnCloseError.click();
				}
				continue;

			}

		}
		System.out.println("Its Done");

	}

	public void MultiplePdf() throws IOException, InterruptedException {
		
	FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf.xlsx"));
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet("Sheet2");
		int rowCount = sheet.getPhysicalNumberOfRows() - 1;

		Actions a = new Actions(wd);
		IconPlusCabinet.click();
		IconPlusDrawer.click();
		utility.isVisible(FolderNamePDF, wd, 15);
		FolderNamePDF.click();

		System.out.println("No of Record Found Into Excel :- " + rowCount);
		for (int i = 1; i <= rowCount; i++) {
			try {
				searchbarPdfDocument.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
				searchbarPdfDocument.sendKeys(Keys.ENTER);
				clickDocument.click();
				utility.isDisaplyedW(SubMenuNwdocument, wd, 10);
				a.moveToElement(SubMenuNwdocument).perform();
				String filepath= (System.getProperty("user.dir")+"\\Files\\PDF\\");
				UploadNewFile.sendKeys(filepath+sheet.getRow(i + 1).getCell(1).getStringCellValue());
				Thread.sleep(3000);

				if (utility.isAlertPresent(wd) == true) 
				{
					wd.switchTo().alert().accept();
					Thread.sleep(3000);
					
				} else {

				}

				utility.isDisaplyedW(btnSaveDocument, wd, 20);
				btnSaveDocument.click();
				Thread.sleep(5000);

				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.createCell(2);
				FileOutputStream fos = new FileOutputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf_New.xlsx"));

				String message = lblSaveDocument.getText();
				if (message.contains("Document updated")) 
				{

					btnokSaveDocument.click();
					cell.setCellValue("File Upload");
					wb.write(fos);

				}

				else 
				{
					cell.setCellValue("Fail");
					wb.write(fos);
				}

				btnCloseDocument.click();
				Thread.sleep(2000);
				searchbarPdfDocument.clear();
				message = "";

			} catch (Exception e)

			{
				if (utility.isDisaplyedW(errorwindow, wd, 5)) 
				{
					System.out.println(sheet.getRow(i).getCell(1).getStringCellValue()+":- This File is zero KB");
					btnCloseError.click();
				}
				
				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.createCell(2);
				FileOutputStream fos = new FileOutputStream((System.getProperty("user.dir")+ File.separator + "\\Files\\Filenames_Pdf_Catch.xlsx"));
				cell.setCellValue("Fail");
				wb.write(fos);
				Thread.sleep(2000);
				btnCloseDocument.click();
				searchbarPdfDocument.clear();
				continue;
			}
		}

	}

	
}
