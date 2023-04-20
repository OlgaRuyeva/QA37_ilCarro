package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase{

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
        int z =(int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow"+i+"@gmail.com")
                .setPassword("Snow12345$");
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        // You are logged in success
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Assert checks is message 'You are logged in success' present");
    }
    @Test
    public void registrationEmptyName(){
        User user = new User()
                .setFirstName("")
                .setLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12345$");
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checks is message 'Name is required' present");
        logger.info("Assert checks is 'YallaButtonNotActive' present");
    }
    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12345$");
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checks is message 'Name is required' present");
        logger.info("Assert checks is 'YallaButtonNotActive' present");
    }
    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("Simpson")
                .setEmail("simpsongmail.com")
                .setPassword("Simp12345$");
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checks is message contains 'Wrong email format'");
        logger.info("Assert checks is 'YallaButtonNotActive' present");
    }

    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .setFirstName("Gomer")
                .setLastName("Simpson")
                .setEmail("simpson@gmail.com")
                .setPassword("Simp12");
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain " +
                "minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, " +
                "1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checks is message 'Password must contain \" +\n" +
                "                \"minimum 8 symbols\\n\" +\n" +
                "                \"Password must contain 1 uppercase letter, 1 lowercase letter, \" +\n" +
                "                \"1 number and one special symbol of [@$#^&*!]' present");
        logger.info("Assert checks is 'YallaButtonNotActive' present");

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}