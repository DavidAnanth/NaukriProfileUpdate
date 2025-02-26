package org.naukriPojo;

import org.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaukriPojo extends BaseClass {
	
	public NaukriPojo(WebDriver drv) {
		PageFactory.initElements(drv, this);
	}
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginLink;

	@FindBy(xpath = "//*[text()='Email ID / Username']/following::input[1]")
	private WebElement username;
	
	@FindBy(xpath = "//*[text()='Password']/following::input[1]")
	private WebElement password;
	
	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//*[text()='View']")
	private WebElement viewProfile;
	
	@FindBy(xpath = "//input[@value='Update resume']")
	private WebElement updateProfile;
	
	public WebElement getLoginLink() {
		return loginLink;
	}

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public WebElement getViewProfile() {
		return viewProfile;
	}

	public WebElement getUpdateProfile() {
		return updateProfile;
	}

}
