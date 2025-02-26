package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
		
	public static WebDriver drv;
	
	public static void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		drv = new ChromeDriver();
	}
	
	public static void windowMaximize() {
		drv.manage().window().maximize();
	}
	
	public static void launchUrl(String url) {
		drv.get(url);
	}
	public static String pageTitle() {
		String title = drv.getTitle();
		return title;
	}
	public static String pageUrl() {
		String url = drv.getCurrentUrl();
		return url;
	}
	public static void passText(String txt, WebElement ele) {
		ele.sendKeys(txt);
	}
	
	public static void closeEntireBrowser() {
		drv.quit();
	}
	
	public static void clickBtn(WebElement ele) {
		ele.click();
	}
	
	public static void screenshot(String fname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) drv;
		File image = ts.getScreenshotAs(OutputType.FILE);
		File f = new File(fname);
		FileUtils.copyFile(image, f);
	}
	
	public static Actions a;
	
	public static void moveTheCursor(WebElement targetWebElement) {
		a = new Actions(drv);
		a.moveToElement(targetWebElement).perform();
	}
	
	public static void dragDrop(WebElement dragWebElement, WebElement dropElement) {
		a = new Actions(drv);
		a.dragAndDrop(dragWebElement, dropElement).perform();
	}
	
	public void clearTxtBox() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
	}
	
	public void previousControl() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_SHIFT);
	}
	
	public void pasteAndEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public boolean isElementNotPresent(WebDriver driver, By locator) { 
        List<WebElement> elements = driver.findElements(locator); 
        return elements.isEmpty(); // Returns true if the list is empty, meaning the element is not present 
    }
	
	public static JavascriptExecutor js;
	
	public static void scrollThePage(WebElement tarWebEle) {
		js = (JavascriptExecutor)drv;
		js.executeScript("arguments[0].scrollIntoView(true)", tarWebEle);
	}
	
	public static void scroll(WebElement element) {
		js = (JavascriptExecutor)drv;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}
	
	public static void excelRead(String sheetName, int rowNum, int cellNum) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\SampleData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet mySheet = wb.getSheet(sheetName);
		Row r = mySheet.getRow(rowNum);
		Cell c = r.getCell(cellNum);
		CellType cellType = c.getCellType();
		int type = cellType.ordinal(); // Get the index of the enum (ordinal value)

		
		String value = " ";
		if (type==1) {
			String value1 = c.getStringCellValue();
		}
		else if (DateUtil.isCellDateFormatted(c)) {
			Date dd = c.getDateCellValue();
			SimpleDateFormat s = new SimpleDateFormat("UserForamt");
			String value2 = s.format(dd);
		}
		else {
			double d = c.getNumericCellValue();
			long l = (long) d;
			String valueOf = String.valueOf(l);
		}
		
	}
	
	public static void readAllData(String sheetName) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\SampleData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet mySheet = wb.getSheet(sheetName);
		for (int i = 0; i < mySheet.getPhysicalNumberOfRows(); i++) {
			Row r = mySheet.getRow(i);
			
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c = r.getCell(j);
				CellType cellType = c.getCellType();
				int type = cellType.ordinal(); 
				
				if (type == 1) {
					String sValue = c.getStringCellValue();
					System.out.println(sValue);
				}
				else if (DateUtil.isCellDateFormatted(c)) {
					Date dValue = c.getDateCellValue();
					SimpleDateFormat s = new SimpleDateFormat("dd-MM-YY");
					String dt = s.format(dValue);
					System.out.println(dt);
				}
				else {
					double d = c.getNumericCellValue();
					long l = (long) d;
					String nValue = String.valueOf(l);
					System.out.println(nValue);
				}
			}			
		}	
	}
	
	public static void createNewExcelFile(int rowNum, int cellNum, String writeData) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\newfile.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet newSheet = w.createSheet("Datas");
		Row newRow = newSheet.createRow(rowNum);
		Cell newCell = newRow.createCell(cellNum);
		newCell.setCellValue(writeData);
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);		
	}
	
	public static void createCell(int getRow, int creCell, String newdata) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\SampleData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.createSheet("Ex2");
		Row r = s.createRow(getRow);
		Cell c = r.createCell(creCell);
		c.setCellValue(newdata);
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);			
	}
	
	public static void createRow(String sName, int creRow, int creCell, String newdata) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\SampleData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.createSheet(sName);
		Row r = s.createRow(creRow);
		Cell c = r.createCell(creCell);
		c.setCellValue(newdata);
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);			
	}
	
	public static void createRowNext(String sName, int creRow, int creCell, String newdata) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\SampleData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet s = wb.getSheet(sName);
		Row r = s.createRow(creRow);
		Cell c = r.createCell(creCell);
		c.setCellValue(newdata);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);			
	}
	
	public static void insertCellValue(int getRow, int creCell, String newdata) throws IOException {
		File f = new File("C:\\Users\\Lenovo\\eclipse-workspace\\DataDriven\\Excel\\SampleData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet("Demo2");
		Row r = s.getRow(getRow);
		Cell c = r.createCell(creCell);
		c.setCellValue(newdata);
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);		
	}
	
	public static void updateDataToParticularCell(int getTheRow, int getTheCell, String existingData, String writeNewdata) throws IOException {
		File f = new File("Location + fileName.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.createSheet("Datas");
		Row r = s.createRow(getTheRow);
		Cell c = r.createCell(getTheCell);
		String str = c.getStringCellValue();
		if (str.equals(existingData)) {
			c.setCellValue(writeNewdata);
			FileOutputStream fos = new FileOutputStream(f);
			w.write(fos);
		}
	}

}
