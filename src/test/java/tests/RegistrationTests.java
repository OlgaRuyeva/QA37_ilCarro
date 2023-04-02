package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests  extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLoggedFinish()){
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z = (int)System.currentTimeMillis()/1000;

        User user = new User().setFirstName("Lisa").setLastName("Snow")
                   .setEmail("snow"+i+"@gmail.com").setPassword("Snow12345!");
//email should be all time is different
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();//  check box
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        }
        @Test
        public void registrationEmptyName(){
            User user = new User().setFirstName("").setLastName("Simpson")
                       .setEmail("simpson@gmail.com").setPassword("Simp12345!");
            app.getHelperUser().openRegistrationForm();
            app.getHelperUser().fillRegistrationForm(user);
            app.getHelperUser().checkPolicyXY();//  check box
            app.getHelperUser().submit();
            Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
            Assert.assertTrue(app.getHelperUser().isYallaButtonNotAktive());

        }
    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotAktive());

    }
        @Test
    public void registrationWrongEmail(){
        User user = new User().setFirstName("Sonya").setLastName("Simpson")
                .setEmail("simpsongmail.com").setPassword("Simp12345!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format\n" +
                "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotAktive());
    }
    @Test
    public void registrationWrongPassword(){
        User user = new User().setFirstName("Sonya").setLastName("Simpson")
                .setEmail("simpson@gmail.com").setPassword("Simp12");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Password must contain minimum 8 symbols"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotAktive());
    }

    @AfterMethod(enabled = false)
    public void postCondition(){
        app.getHelperUser().closeWindow();}
}