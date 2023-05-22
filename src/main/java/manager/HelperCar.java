package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;


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
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));//превратили int в String
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        //type(By.id("price"),String.valueOf(car.getPrice()));
        type(By.id("price"), car.getPrice() + "");//также превращает int double в String, the same 29
        type(By.id("about"), car.getAbout());
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
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnHome() {
        click(By.xpath("//*[text()='Search cars']"));
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        String[] from = dateFrom.split("/");
        String locatorFrom = "//*[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));
        pause(2000);

        String[] to = dateTo.split("/");
        String locatorTo = "//*[text()=' " + to[1] + " ']";
        click(By.xpath(locatorTo));

        /* String[] locatorFrom1 = dateFrom.split("/");
        int dayNumber = Integer.parseInt(locatorFrom1[1]);
        String locator = "//*[text()=' "+dayNumber+" ']";
        WebElement dayElement = wd.findElement(By.xpath(locator));
        dayElement.click();
        pause(2000);
        String[] locatorFrom  = dateTo.split("/");
        int dayNumber1 = Integer.parseInt(locatorFrom1[1]);
        String locator1  = "//*[text()=' "+dayNumber+" ']";
        WebElement dayElement1 = wd.findElement(By.xpath(locator1));
        dayElement1.click();  */
    }
    private void typeCity(String city) {
        type(By.id("city"), city);
        pause(500);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        // "//div[text()=' "+from[1]+" ']";
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        int diffYear;
        int diffMonth;
        //***from
        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) {//2023=2023
            diffMonth = from.getMonthValue() - now.getMonthValue(); //10-4=6
        } else {//2023!=2024--> 12-4=8+2=10
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();//
        }
        clickNextMonthBtn(diffMonth);//date of start period
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));
        //***to
        diffYear=to.getYear()-from.getYear();
         if (diffYear==0){
             diffMonth = to.getYear()- from.getYear();
         }else {
             diffMonth=12-from.getMonthValue()+to.getMonthValue();
         }
        clickNextMonthBtn(diffMonth);
         locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
         click(By.xpath(locator));


        //* public void searchAnyPeriod1(String city, String dateFrom, String dateTo) {
    // typeCity(city);
    //click(By.id("dates"));
    //  String[] from = dateFrom.split("/"); String locatorFrom = "//*[text()=' "+ from[1]+" ']";
    //click(By.xpath(locatorFrom));     pause(2000);
    //click(By.cssSelector(".mat-calendar-arrow"));
    // click(By.xpath("//div[text()= 2024]"));
    //pause(1000);
    // String[] to = dateTo.split( "/"); //*[text()=' MAR '] 3/28/24
    //String month = to[0];
    // String[] months = {" ","JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    // String JAN = months[Integer.parseInt(month)];
    // String locatorTo = "//div[text()=' "+JAN+" ']";
    //click(By.xpath(locatorTo));
    //String[] to1 = dateTo.split("/");
    //String locatorTo1 = "//*[text()=' "+to1[1]+" ']";
    // click(By.xpath(locatorTo1));    }

}
    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));

    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        type(By.id("dates"),dateFrom+" - "+dateTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
        //wd.findElement(By.id("dates")).sendKeys(dateFrom+"-"+dateTo);
    }

    public boolean isErrorDisplayed(String massage) {
        String text = wd.findElement(By.cssSelector("div.ng-star-inserted")).getText();
        return text.equals(massage);
    }


}







