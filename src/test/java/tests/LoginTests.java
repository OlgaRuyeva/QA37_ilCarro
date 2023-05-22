package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLoggedFinish()) {
            app.getHelperUser().logOut();
            logger.info("Befor finish method logOut");
        }
    }

    @Test(dataProvider = "loginFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccess1(User user) {
        logger.info("Start test 'loginSuccess1'");
        logger.info("Test data--->"+user.toString());
        //User user = new User().setEmail("00220719@gmail.com").setPassword("OO220719!oo");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().getScreen("src/test/screenshots/screen.png");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check is message 'Logged in success' present");

    }

    @Test
    public void loginSuccess() {
        logger.info("Test data---> email: '00220719@gmail.com' & password:'OO220719!oo'");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com", "OO220719!oo");
        app.getHelperCar().getScreen("src/test/screenshots/screen.png");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check is message 'Logged in success' present");
    }

    @Test(dataProvider = "loginFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check is message 'Logged in success' present");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void LoginWrongEmail() {
        logger.info("Test data---> email: '00220719gmail.com' & password:'OO220719!oo'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719gmail.com", "OO220719!oo");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check is error  text  'It'snot look like email' present");
        logger.info("Assert check is 'Yalla Button Not Active' ");
   }

    @Test
    public void LoginWrongPassword() {
        logger.info("Test data---> email: '00220719@gmail.com' & password:'OO22'");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com", "OO22");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check is message 'Login or Password incorrect' present");
    }

    @Test
    public void LoginUnregistratedUser() {
        logger.info("Test data---> email: 'pp220719@gmail.com' & password:'OO220719!oo'");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("pp220719@gmail.com", "OO220719!oo");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check is message 'Login or Password incorrect' present");

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeWindow();
    }
}
