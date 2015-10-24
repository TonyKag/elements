package com.sqa.ao.selenium.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ElementsTests {

	public static void displayableElements(List<WebElement> elements, String itemAttribute) {
		// System.out.println("Total elements: " + elements.size());
		List<WebElement> displayedItems = new ArrayList<WebElement>();
		List<WebElement> nonDisplayedItems = new ArrayList<WebElement>();
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				displayedItems.add(element);
			} else {
				nonDisplayedItems.add(element);
			}
		}
		System.out.println("There are a total of " + elements.size());
		System.out.println("\t" + displayedItems.size() + "are displayed.");
		System.out.println("\t" + nonDisplayedItems.size() + "are NOT displayed.");
		System.out.println("Displayed:");
		for (WebElement element : displayedItems) {
			System.out.println("\tIMAGE @\t" + element.getAttribute(itemAttribute));
		}
		System.out.println("NON Displayed:");
		for (WebElement element : nonDisplayedItems) {
			System.out.println("\tNON DISPLAYED\tIMAGE @\t" + element.getAttribute(itemAttribute));
		}
	}

	public static WebElement highestElement(WebElement element1, WebElement element2) {
		// System.out.println("Element 1 " + element1.getLocation().getY());
		// System.out.println("Element 2 " + element2.getLocation().getY());
		WebElement highest = element1.getLocation().getY() > element2.getLocation().getY() ? element2 : element1;

		return highest;
	}

	private boolean acceptNextAlert = true;
	private String baseUrl;

	private WebDriver driver;

	private StringBuffer verificationErrors = new StringBuffer();

	// A collection to hold Web Elements
	ArrayList<WebElement> elements = new ArrayList<WebElement>();

	@AfterClass
	public void afterClass() {
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		baseUrl = "http://amazon.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(enabled = false, groups = { "Amazon", "Chrome", "Firefox" })
	public void testAmazon() throws Exception {

		// A collection to hold Web Elements
		ArrayList<WebElement> elements = new ArrayList<WebElement>();

		// Go to URL to search for elements
		this.driver.get("http://amazon.com");

		WebElement searchSignIn = this.driver.findElement(By.linkText("Sign in"));
		// System.out.println("Element:" + searchSignIn.getLocation());
		elements.add(searchSignIn);
		searchSignIn.click();

		WebElement emailField = this.driver.findElement(By.id("ap_email"));
		elements.add(emailField);
		emailField.clear();
		emailField.sendKeys("sqasolution@hotmail.com");

		WebElement passwordField = this.driver.findElement(By.id("ap_password"));
		elements.add(passwordField);
		passwordField.clear();
		passwordField.sendKeys("sqasolution");

		WebElement signInSubmit = this.driver.findElement(By.xpath(".//*[@id='signInSubmit-input']"));
		elements.add(signInSubmit);
		signInSubmit.click();

		WebElement searchBox = this.driver.findElement(By.id("twotabsearchtextbox"));
		elements.add(searchBox);
		searchBox.click();
		searchBox.sendKeys("The Three Doctors");
		searchBox.sendKeys(Keys.RETURN);

		WebElement firstSearchResult = this.driver
				.findElement(By.cssSelector(".//*[@id='result_0']/div/div/div/div[1]/div/div/a/img"));
		elements.add(firstSearchResult);
		firstSearchResult.click();

		WebElement addToCartButton = this.driver.findElement(By.id("add-to-cart-button"));
		elements.add(addToCartButton);
		addToCartButton.click();

		WebElement departmentButton = this.driver.findElement(By.cssSelector("input[class='nav-line-2']"));
		elements.add(departmentButton);
		departmentButton.click();

		WebElement linkText = this.driver.findElement(By.linkText("Lamps &	 Light Fixtures"));
		elements.add(linkText);
		linkText.click();

		WebElement partLinkText = this.driver.findElement(By.partialLinkText("Commercial "));
		elements.add(partLinkText);
		partLinkText.click();

		WebElement byName = this.driver.findElement(By.name("$12.99"));
		elements.add(byName);
		byName.click();

		// Perform a method on all elements of the collection
		for (WebElement element : elements) {
			System.out.println(element.getAttribute("name") + " is Displayed:" + element.isDisplayed());
		}
	}

	@Test(enabled = false, groups = { "Amazon", "Chrome", "Firefox" })
	public void testDisplayableElements() throws Exception {
		this.driver.get("http://amazon.com");
		List<WebElement> displayedItems = this.driver.findElements(By.tagName("img"));
		displayableElements(displayedItems, "src");
	}

	@Test(enabled = false, groups = { "Tmz", "Chrome", "Firefox" })
	public void testTmz() throws Exception {

		// A collection to hold Web Elements
		ArrayList<WebElement> elements = new ArrayList<WebElement>();

		// Go to URL to search for elements
		this.driver.get("http://tmz.com");

		WebElement storeBtn = this.driver.findElement(By.xpath(".//*[@id='nav']/ul[1]/li[7]/a"));
		elements.add(storeBtn);
		storeBtn.click();

		// Perform a method on all elements of the collection
		for (WebElement element : elements) {
			System.out.println(element.getAttribute("name") + " is Displayed:" + element.isDisplayed());
		}
	};

	@Test(groups = { "Amazon", "Chrome", "Firefox" })
	public void testUpperMethod() throws Exception {
		driver.get("www.starbucks.com");
		WebElement instagramIcon = this.driver.findElement(By.cssSelector(".instagram>a"));
		WebElement logo = this.driver.findElement(By.cssSelector("#logo"));
		System.out.println("Which element is higher on screen?");

		System.out.println(
				"Element 1 " + instagramIcon.getAttribute("href") + " - " + "Element 2 " + logo.getAttribute("id"));
		WebElement highest = highestElement(logo, instagramIcon);
		System.out.println(highest + " is the highest element.");
	}
}
