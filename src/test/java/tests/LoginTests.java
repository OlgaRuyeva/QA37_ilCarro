package tests;

import models.User;
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
    public void loginSuccess1(){
        User user = new User().setEmail("00220719@gmail.com").setPassword("OO220719!oo");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }
        @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }
////////////////////////////////////////////////////////////////////////////////////////////////
   @Test
    public void LoginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719gmail.com","OO220719!oo");
        app.getHelperUser().submit();
       Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
       Assert.assertTrue(app.getHelperUser().isYallaButtonNotAktive());

    }
    @Test
    public void LoginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO22");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }
    @Test
    public void LoginUnregistratedUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("pp220719@gmail.com","OO220719!oo");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }
    @AfterMethod
    public void postCondition(){
       app.getHelperUser().closeWindow();}
}
