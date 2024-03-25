package ru.netology.testing.uiautomator;

// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:deviceName", "Some name")
                .amend("appium:appPackage", "ru.netology.testing.uiautomator")
                .amend("appium:appActivity", ".MainActivity")
                .amend("appium:automationName", "uiautomator2")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);
                URL remoteURL = new URL("http://127.0.0.1:4723/");

                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                driver = new AndroidDriver(remoteURL, options);
    }

    @Test
    public void testNewWindowText() {
        var el1 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.click();
        el1.sendKeys("Netology");
        var el2 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.click();
        var el3 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/text"));
        el3.isDisplayed();
        Assert.assertEquals(el3.getText() , "Netology");
    }

    @Test
    public void testNoTextAdded() {
        var el1 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el1.click();
        var el2 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el2.isDisplayed();
        Assert.assertEquals(el2.getText() , "Hello UiAutomator!");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
