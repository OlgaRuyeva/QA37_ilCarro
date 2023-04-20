package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
        //"a[href^='/login']" //"//a[text()=' Log in ']

    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.xpath("//input[@id='password']"), password);

    }

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
    }

    public void closeWindow() {//click when this button is present
        if (isElementPresent(By.xpath("//*[text()='Ok']"))) {
            click(By.xpath("//*[text()='Ok']"));
        }
    }

    public boolean isLoggedFinish() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }

    public void logOut() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public String getErrorText() {
        String text = wd.findElement(By.cssSelector("div.error")).getText();
        System.out.println(text);
        return text;
        // return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;
    }

    ///*********************** Registration *************************************
    public void openRegistrationForm() {
        click(By.xpath("//*[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());

    }

    public void checkPolicy() {
        //click(By.id("terms-of-use"));0*0
        click(By.cssSelector("label[for = 'terms-of-use']"));
    }

    public void checkPolicyXY() {
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            Dimension size = wd.manage().window().getSize();
            System.out.println("Wight screen -->" + size.getWidth());

            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

            Dimension dimension = label.getSize();

            Rectangle rect = label.getRect();
            int w = rect.getWidth();

            int xOffSet = -w / 2;

            Actions actions = new Actions(wd);

            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        closeWindow();

    }

}


