package main;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.System;

import java.util.ArrayList;

public class automation {


    private ArrayList<Integer> ordersNumberList = new ArrayList<>();
    private String orderStatus;
    private boolean notifyCustomer;
    private String customerMessage;

    public automation (ArrayList<Integer> ordersNumberList, String orderStatus, boolean notifyCustomer, String customerMessage) {
        this.ordersNumberList = ordersNumberList;
        this.orderStatus = orderStatus;
        this.notifyCustomer = notifyCustomer;
        this.customerMessage = customerMessage;
    }
    public void automationProcess() {
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
        System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        //Access Moni's opencart website
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("abc.com");
        //Fill the username
        WebElement username = driver.findElement(By.id("input-username"));
        username.sendKeys("*****");
        //Fill the password
        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("*****");
        //Press the submit button
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        for (int x : ordersNumberList) {
            WebElement orderInputID = driver.findElement(By.id("input-order-id"));
            orderInputID.clear();
            orderInputID.sendKeys("" + x);
            // Click the filter button
            WebElement buttonFilter = driver.findElement(By.id("button-filter"));
            buttonFilter.click();
            // Go to the order detail page
            WebElement viewOrderButton = driver.findElement(By.cssSelector("a[data-original-title='View']"));
            viewOrderButton.click();
            // Update the order status
            WebElement orderStatus = driver.findElement(By.id("input-order-status"));
            orderStatus.sendKeys((CharSequence) orderStatus);
            // Notify customer
            WebElement notifyCustomerCheck = driver.findElement(By.id("input-notify"));
            if (notifyCustomer == true) notifyCustomerCheck.click();
            // Submit the new order status
            WebElement orderStatusUpdateButton = driver.findElement(By.id("button-history"));
            orderStatusUpdateButton.click();
            driver.navigate().back();
        }
    }
}
