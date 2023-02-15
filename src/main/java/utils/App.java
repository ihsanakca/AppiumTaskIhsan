package utils;

public enum App {
    APIDEMO("com.touchboarder.android.api.demos","com.touchboarder.androidapidemos.MainActivity", ""),
    CALCULATOR("com.miui.calculator","com.miui.calculator.cal.CalculatorActivity", "")
    ;

    public String appPackage;
    public String appActivity;
    public String apk;


    App(String appPackage, String appActivity, String apk) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.apk = apk;
    }
}
