package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {
    @Test
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel Aviv,Israel", "5/1/2023", "5/15/2023");
        app.getHelperCar().getScreen("src/test/screenshots/currentmonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }
    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel Aviv,Israel", "6/30/2023", "10/28/2023");
        app.getHelperCar().getScreen("src/test/screenshots/currentyear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }
@Test
    public void searchAnyPeriodSuccess() {
        app.getHelperCar().searchAnyPeriod ("Tel Aviv,Israel","12/27/2023","2/28/2024");
    app.getHelperCar().getScreen("src/test/screenshots/any.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }
    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("TelAviv,Israil","1/10/2023","10/10/2023");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isErrorDisplayed("You can't pick date before today"));
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
    }
    @BeforeMethod
    public void postCondition() {
        app.getHelperCar().navigateByLogo();  }


}