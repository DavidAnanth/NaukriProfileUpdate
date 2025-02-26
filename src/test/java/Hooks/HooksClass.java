package Hooks;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksClass extends BaseClass {
	
	@Before
    public void beforeScenario() {
    	System.out.println("Running preCondition...");
        launchBrowser();  
        windowMaximize();
        drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     }
	
	
	
	@Before(value="@addcart")
	private void StartTime() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
        System.out.println(s.format(d));

	}
	
	@After(value="@addcart")
	private void EndTime() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
        System.out.println(s.format(d));

	}

   
    @After
    public void postCondition() {
    	closeEntireBrowser();  
    }
}