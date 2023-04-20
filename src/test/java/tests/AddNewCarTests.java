package tests;

import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {
    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLoggedFinish()){
            app.getHelperUser().login(new User().setEmail("00220719@gmail.com").setPassword("OO220719!oo") );
        }
    }
    @Test
    public void AddNewCarSuccessAll() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv,Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(9)
                .carClass("C")
                .carRegNumber("678-990-" + i)
                .price(50)
                .about("Very nice car")
                .build();
        logger.info("Test data--->"+car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCraForm(car);
        app.getHelperCar().submit();
        app.getHelperCar().pause(2000);
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+" "
                                                                    +car.getModel()+" added successful");
        logger.info("Assert checks is message' added successful' present");
         }
    @Test
    public void AddNewCarSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv,Israel")
                .manufacture("Lexus")
                .model("250")
                .year("2021")
                .fuel("Petrol")
                .seats(2)
                .carClass("C")
                .carRegNumber("238-990-" + i)
                .price(60)
                .build();
        logger.info("Test data--->"+car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCraForm(car);
        app.getHelperCar().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+" "
                +car.getModel()+" added successful");
        logger.info("Assert checks is message' added successful' present");

    }

    @AfterMethod
    public void posCondition(){
        app.getHelperCar().returnHome();
    }
}
