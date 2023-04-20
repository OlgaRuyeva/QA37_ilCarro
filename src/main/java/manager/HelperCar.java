package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//*[text()=' Let the car work ']"));
    }


    public void fillCraForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.id("year"),car.getYear());
        select(By.id("fuel"),car.getFuel());
        type(By.id("seats"),String.valueOf(car.getSeats()));//превратили int в String
        type(By.id("class"),car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        //type(By.id("price"),String.valueOf(car.getPrice()));
        type(By.id("price"),car.getPrice()+"");//также превращает int double в String, the same 29
        type(By.id("about"),car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //gas
        //select.selectByIndex(5);
        //select.selectByValue("Gas");
        //select.deselectByVisibleText(" Gas ");

    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"),location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnHome() {
        click(By.xpath("//*[text()='Search cars']"));
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

        //click (By.xpath("//*[text()=' 28 ']"));

        String[] locatorFrom1 = dateFrom.split("/");
        int dayNumber = Integer.parseInt(locatorFrom1[1]);
        String locator = "//*[text()=' "+dayNumber+" ']";
        WebElement dayElement = wd.findElement(By.xpath(locator));
        dayElement.click();

        //click (By.xpath("//*[text()=' 28 ']"));

        String[] locatorFrom2 = dateTo.split("/");
        int dayNumber1 = Integer.parseInt(locatorFrom1[1]);
        String locator1 = "//*[text()=' "+dayNumber+" ']";
        WebElement dayElement1 = wd.findElement(By.xpath(locator));
        dayElement1.click();



    }

    private void typeCity(String city) {
        type(By.id("city"),city);
        click(By.cssSelector("div.pac-item"));
    }
}
