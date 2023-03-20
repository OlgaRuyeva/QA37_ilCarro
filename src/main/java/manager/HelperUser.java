package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
     public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm(){
        click (By.cssSelector("a[href='/login?url=%2Fsearch']"));

    }

    public void fillLoginForm(String email,String password){
        type(By.id("email"),email);
        type(By.xpath("//input[@id='password']"),password);

    }
    public void submitLoginYalla(){
         click(By.cssSelector("button[type='submit']"));
    }
    public boolean isLogged(){
         return isElementPresent(By.xpath(" //*[text()='Ok']"));
    }
}
