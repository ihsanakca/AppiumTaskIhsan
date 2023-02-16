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

public class Task2 {

    By lDevam = By.xpath("//*[@text='DEVAM']");
    By lButton1 = By.id("android:id/button1");
    By lTamam = By.xpath("//*[@text='TAMAM']");
    By lAPI_Demos = By.xpath("//*[@text='API Demos']");
    // By lAccessibility = By.xpath("//*[@text='Accessibility']");

    By lInputBox=By.id("com.touchboarder.android.api.demos:id/edit");
    By lKapaliButton=By.id("com.touchboarder.android.api.demos:id/toggle1");
    By lKapaliButton2=By.id("com.touchboarder.android.api.demos:id/toggle2");
    By lSelectBox=By.id("com.touchboarder.android.api.demos:id/spinner1");

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
   2.    Scenario 2
    a.    Views->Expendable Lists'e tiklayin
    b.    Custom Adaptor'e tiklayin
    c.    People Names'e tiklayin ve 4 adet ismin visible oldugunu assert edin
    d.    People Names'e tekrar tiklayin ve 4 adet ismin invisible oldugunu assert edin
    e.    Fish Names'e tiklayin ve ikinci siradaki ismin Bubbles oldugunu assert edin.
     */
    @Test
    public void test1() {
        click(xpathOfText("Views"));
        click(xpathOfText("Expandable Lists"));
        click(xpathOfText("1. Custom Adapter"));
        click(xpathOfText("People Names"));

        MobileElement arnold = driver.findElement(xpathOfText("Arnold"));
        Assert.assertTrue(arnold.isDisplayed());

        MobileElement barry = driver.findElement(xpathOfText("Barry"));
        Assert.assertTrue(barry.isDisplayed());

        MobileElement chuck = driver.findElement(xpathOfText("Chuck"));
        Assert.assertTrue(chuck.isDisplayed());

        MobileElement david = driver.findElement(xpathOfText("David"));
        Assert.assertTrue(david.isDisplayed());

        click(xpathOfText("People Names"));

//        String displayed = david.getAttribute("displayed");
//        System.out.println("displayed = " + displayed);


        Boolean arnold1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Arnold")));
        Assert.assertTrue(arnold1);
        Boolean barry1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Barry")));
        Boolean until = wait.until(ExpectedConditions.invisibilityOf(barry));
        System.out.println("until = " + until);
        Assert.assertTrue(barry1);
        Boolean chuck1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Chuck")));
        Assert.assertTrue(chuck1);
        Boolean david1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("David")));
        Assert.assertTrue(david1);

        click(xpathOfText("Fish Names"));

        MobileElement bubbles = driver.findElement(xpathOfText("Bubbles"));
        Assert.assertEquals(bubbles.getText(),"Bubbles");

        List<MobileElement> names = driver.findElements(By.className("android.widget.TextView"));

        Assert.assertEquals(names.get(5).getText(),"Bubbles");

        String actual=driver.findElement(By.xpath("//*[@class='android.widget.TextView'][6]")).getText();
        Assert.assertEquals(actual,"Bubbles");


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
