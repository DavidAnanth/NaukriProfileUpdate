package org.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.base.BaseClass;
import org.naukriPojo.NaukriPojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NaukriProfileUpdate extends BaseClass {
	
	NaukriPojo np = new NaukriPojo(drv);
	
	@Given("Open chrome browser and hit the Naukri url")
	public void open_chrome_browser_and_hit_the_naukri_url() {
	     launchUrl("https://www.naukri.com/");
	}

	@When("when website is loaded, click on Login button.")
	public void when_website_is_loaded_click_on_login_button() {
	    np.getLoginLink().click();
	}

	@Then("provide the valid Username in the username field")
	public void provide_the_valid_username_in_the_username_field() {
	    np.getUsername().sendKeys("ananthkalaimani1610@gmail.com");
	}

	@Then("provide the valid password in the password field")
	public void provide_the_valid_password_in_the_password_field() {
	    np.getPassword().sendKeys("Tony@2408");
	}

	@Then("Click on Login button")
	public void click_on_login_button() {
	    np.getLoginButton().click();
	}
	
	@When("Profile page is loaded click on View Profile button")
	public void profile_page_is_loaded_click_on_view_profile_button() {
	    np.getViewProfile().click();
	}
	
	@Then("click on update resume button and upload the updated resume")
	public void click_on_update_resume_button_and_upload_the_updated_resume() throws IOException, InterruptedException, AWTException {
		String filePath = System.getProperty("user.dir") + File.separator + "Files" + File.separator + "AnanthResume.pdf";
	    np.getUpdateProfile().click();
	    Thread.sleep(3000);
	    StringSelection str = new StringSelection(filePath);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
	    pasteAndEnter();
	    Thread.sleep(3000);
	    screenshot("Screenshots\\updateMsg.png");
	}


}
