package ru.netology.testing.uiautomator;

// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private AndroidDriver driver;
    private PORT = 4723;
    private HOST = "127.0.0.1";


    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
                URL remoteURL = new URL("http://127.0.0.1:4723/");

                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                driver = new AndroidDriver(remoteURL, desiredCapabilities);
    }



    @Test
    public void testNoTextAdded() {
        WebElement el3 = (WebElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        String oldText = el3.getText();
        WebElement el1 = (WebElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys("  ");
        WebElement el2 = (WebElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        Assertions.assertEquals(el3.getText() , oldText);
    }

    @Test
    public void testNewWindowText() {
        String textToSet = "Appium";
        WebElement el1 = (WebElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys("Appium");
        WebElement el2 = (WebElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.click();
        WebElement el3 = (WebElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        Assertions.assertEquals(el3.getText(), textToSet);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
