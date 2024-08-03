package Assignmentcode.Assignmentcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FitPeoAutomation 
{
	public static void main(String[] args) {
        // Setup WebDriver Manager to handle the ChromeDriver binary automatically
        WebDriverManager.chromedriver().setup();

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);  // Explicit wait for elements

        try {
            // Step 1: Open the web browser and navigate to FitPeo Homepage
            driver.get("https://www.fitpeo.com");

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Revenue Calculator")));
            revenueCalculatorLink.click();

            // Step 3: Scroll down the page until the revenue calculator slider is visible
            WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("revenue-calculator-slider")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);

            // Step 4: Adjust the slider to set its value to 820
            WebElement sliderHandle = driver.findElement(By.cssSelector(".slider-handle")); 
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 820px;')", sliderHandle);
            WebElement textField = driver.findElement(By.id("slider-text-field")); 
            wait.until(ExpectedConditions.textToBePresentInElement(textField, "820"));

            // Step 5: Click on the text field and enter the value 560
            textField.click();
            textField.clear();
            textField.sendKeys("560");

            // Step 6: Ensure slider updates to reflect the value 560
            WebElement sliderUpdatedHandle = driver.findElement(By.cssSelector(".slider-handle")); 
            wait.until(ExpectedConditions.attributeToBe(sliderUpdatedHandle, "style", "left: 560px;")); 

            // Step 7: Scroll down and select checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474
            WebElement checkbox99091 = driver.findElement(By.id("cpt-99091")); 
            WebElement checkbox99453 = driver.findElement(By.id("cpt-99453"));
            WebElement checkbox99454 = driver.findElement(By.id("cpt-99454"));
            WebElement checkbox99474 = driver.findElement(By.id("cpt-99474"));

            checkbox99091.click();
            checkbox99453.click();
            checkbox99454.click();
            checkbox99474.click();

            
            WebElement totalReimbursementHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total-   reimbursement"))); 
        String headerText = totalReimbursementHeader.getText();
            if (headerText.contains("$110700")) {
                System.out.println("Total Recurring Reimbursement is correct: " + headerText);
            } else 
            {
                System.out.println("Total Recurring Reimbursement is incorrect: " + headerText);
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

