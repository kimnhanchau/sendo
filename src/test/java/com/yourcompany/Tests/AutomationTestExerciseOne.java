package com.yourcompany.Tests;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AutomationTestExerciseOne {
    WebDriver driver;
    String customerId;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4/");
        WebElement element = driver.findElement(By.name("uid"));
        element.sendKeys("mngr240640");
        element = driver.findElement(By.name("password"));
        element.sendKeys("udUputA");
        element = driver.findElement(By.name("btnLogin"));
        element.click();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "verify message displays with content 'Customer Registered Successfully!!")
    public void verifyCreateNewCustomer() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'New Customer')]"));
        element.click();
        element = driver.findElement(By.xpath("//input[@name='name']"));
        element.sendKeys("Customer Name");
        element = driver.findElement(By.xpath("//input[@value='f']"));
        element.click();
        element = driver.findElement(By.xpath("//input[@id='dob']"));
        element.sendKeys("02051992");
        element = driver.findElement(By.name("addr"));
        element.sendKeys("address");
        element = driver.findElement(By.name("city"));
        element.sendKeys("city");
        element = driver.findElement(By.name("state"));
        element.sendKeys("state");
        element = driver.findElement(By.name("pinno"));
        element.sendKeys("343454");
        element = driver.findElement(By.name("telephoneno"));
        element.sendKeys("012435436");
        element = driver.findElement(By.xpath("//input[@name='emailid' and @type='text']"));
        element.sendKeys(generatedString + "@yob.com");
        element = driver.findElement(By.xpath("//input[@type='password']"));
        element.sendKeys("1233434");
        element = driver.findElement(By.name("sub"));
        element.click();

        Assert.assertEquals("Guru99 Bank Customer Registration Page", driver.getTitle().trim());
        element = driver.findElement(By.xpath("//p[@class='heading3']"));
        String message = element.getText().trim();
        Assert.assertEquals("Customer Registered Successfully!!!", message);
        customerId = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/../td[2]")).getText();

    }

    @Test(description = "add new account and check created successfully", dependsOnMethods = {"SimpleTest.verifyCreateNewCustomer"})
    public void verifyCreateNewAccount() {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'New Account')]"));
        element.click();
        element = driver.findElement(By.name("cusid"));
        element.sendKeys(customerId);
        Select drpCountry = new Select(driver.findElement(By.name("selaccount")));
        drpCountry.selectByVisibleText("Current");
        element = driver.findElement(By.name("inideposit"));
        element.sendKeys("121435");
        element = driver.findElement(By.name("button2"));
        element.click();
        element = driver.findElement(By.xpath("//p[@class='heading3']"));
        String message = element.getText().trim();
        Assert.assertEquals("Account Generated Successfully!!!", message);
    }
}