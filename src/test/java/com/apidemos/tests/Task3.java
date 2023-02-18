package com.apidemos.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Task3 {

    By lDevam = By.xpath("//*[@text='DEVAM']");
    By lButton1 = By.id("android:id/button1");
    By lTamam = By.xpath("//*[@text='TAMAM']");
    By lAPI_Demos = By.xpath("//*[@text='API Demos']");
    // By lAccessibility = By.xpath("//*[@text='Accessibility']");

    By lInputBox = By.id("com.touchboarder.android.api.demos:id/edit");
    By lKapaliButton = By.id("com.touchboarder.android.api.demos:id/toggle1");
    By lKapaliButton2 = By.id("com.touchboarder.android.api.demos:id/toggle2");
    By lSelectBox = By.id("com.touchboarder.android.api.demos:id/spinner1");

    String textXpath = "//*[@text=\"{0}\"]";

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        Driver.runAppium();
        driver = Driver.getDriver(Device.Redmi_Note_8, App.APIDEMO);
        wait = new WebDriverWait(driver, 1);
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

    /*
   3.    Scenario 3
    a.    10 ile 20 arasinda random bir sayi secin
    b.    Views->TextSwitcher'a tiklayin
    c.    Secilen random sayiya gelinceye kadar Next butonuna tiklayin
    d.    Istenilen sayiya ulasilinca navigate().back() ile geri gelin.
     */
    @Test
    public void test1() {
        click(xpathOfText("Views"));
        swipeUntil(xpathOfText("TextSwitcher"), .6, .2);
        click(xpathOfText("TextSwitcher"));

        Random rd=new Random();
        int expected= rd.nextInt(10)+10;
        System.out.println("expected = " + expected);
       int actual=0;
        for (int i = 0; i <expected ; i++) {
            if (actual!=expected){
                click(xpathOfText("NEXT"));
            }
            actual=Integer.parseInt(driver.findElement(By.className("android.widget.TextView")).getText());
        }
        System.out.println("actual = " + actual);
        Assert.assertEquals(actual,expected);
        driver.navigate().back();
    }


    public void click(By locator) {
        driver.findElement(locator).click();
    }

    By xpathOfText(String... text) {
        return By.xpath(MessageFormat.format(textXpath, text));
    }

    void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void swipeV(double startPoint, double endPoint) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w / 2, (int) (h * startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w / 2, (int) (h * endPoint)))
                .release()
                .perform();
    }

    public void swipeUntil(By locator, double start, double end) {
        while (driver.findElements(locator).size() <= 0)
            swipeV(start, end);
    }


}
