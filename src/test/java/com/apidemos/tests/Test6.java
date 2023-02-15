package com.apidemos.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import java.text.MessageFormat;
import java.time.Duration;

public class Test6 {

    By lDevam = By.xpath("//*[@text='DEVAM']");
    By lButton1 = By.id("android:id/button1");
    By lTamam = By.xpath("//*[@text='TAMAM']");
    By lAPI_Demos = By.xpath("//*[@text='API Demos']");
    // By lAccessibility = By.xpath("//*[@text='Accessibility']");

    String textXpath = "//*[@text=\"{0}\"]";

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        Driver.runAppium();
        driver = Driver.getDriver(Device.Redmi_Note_8, App.APIDEMO);
        wait = new WebDriverWait(driver, 4);
        click(lDevam);
        click(lButton1);
        click(lTamam);
        click(lAPI_Demos);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
        Driver.stopAppium();
    }

    @Test
    public void test1() {
    click(xpathOfText("Views"));
        waitForVisibility(xpathOfText("Auto Complete"));
        swipeV(.6, .3);
        swipeV(.3, .6);

    }

    @Test
    public void test2() {

        click(xpathOfText("Views"));
        waitForVisibility(xpathOfText("Auto Complete"));
        swipeUntil(xpathOfText("WebView"), .6, .4);
        click(xpathOfText("WebView"));
        driver.navigate().back();
        swipeUntil(xpathOfText("Animation"), .4, .7);

    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    By xpathOfText(String... text) {
        return By.xpath(MessageFormat.format(textXpath, text));
    }

    void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void swipeV(double startPoint, double endPoint){
        int w=driver.manage().window().getSize().width;
        int h=driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w/2, (int) (h*startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w/2, (int) (h*endPoint)))
                .release()
                .perform();
    }
    public void swipeUntil(By locator, double start, double end){
        while (driver.findElements(locator).size()<=0)
            swipeV(start, end);
    }


}
