package tests;

import models.Car;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {
    @BeforeClass
    public void Precondition(){
        if(!app.getHelperUser().isLoggedFinish()){

        }
    }
    @Test
    public void AddNewCarSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv,Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(9)
                .carClass("C")
                .carRegNumber("678-900-" + i)
                .price(50)
                .about("Very nice car")
                .build();
        //app.getHelperCar().openCarForm();
       // app.getHelperCar().fillCraForm();
       // app.getHelperCar().submitCarForm();
    }
}
