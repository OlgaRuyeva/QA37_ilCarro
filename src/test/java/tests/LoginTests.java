package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submitLoginYalla();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

}
