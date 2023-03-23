package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLoggedFinish()){
            app.getHelperUser().logOut();
        }
    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submitLoginYalla();
        Assert.assertEquals(app.getHelperUser().isLogged(),"Logged in success");

    }
    @Test

    public void loginSuccessModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submitLoginYalla();
        Assert.assertEquals(app.getHelperUser().isLogged(),"Logged in success");
    }


   // @Test
    //public void LoginWrongEmail(){
      // app.getHelperUser().openLoginForm();
      // app.getHelperUser().fillLoginForm("oo220719gmail.com","OO220719!oo");
      // app.getHelperUser().submitLoginYalla();
      // Assert.assertTrue(app.getHelperUser().incorrectEmail());    }

    @Test
    public void LoginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO22");
        app.getHelperUser().submitLoginYalla();
        Assert.assertTrue(app.getHelperUser().incorrectPassword());
    }
    @Test
    public void LoginUnregistratedUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("pp220719@gmail.com","OO220719!oo");
        app.getHelperUser().submitLoginYalla();
       Assert.assertTrue(app.getHelperUser().unregesterdUser());
    }



    @AfterMethod
    public void postCondition(){
       app.getHelperUser().closeWindow();}
}
