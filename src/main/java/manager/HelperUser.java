package manager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HelperUser extends HelperBase{
     public HelperUser(WebDriver wd) {
         super(wd);
    }
    public void openLoginForm(){
         click (By.cssSelector("a[href='/login?url=%2Fsearch']"));//"a[href^='/login']" //"//a[text()=' Log in ']

    }
    public void fillLoginForm(String email,String password){
        type(By.id("email"),email);
        type(By.xpath("//input[@id='password']"),password);
    }
    public void submitLoginYalla(){
        //click(By.xpath("//*[text()='Y’alla!']"));
         click(By.cssSelector("button[type='submit']"));
    }
    public String isLogged(){
         //pause(2000);
         WebDriverWait wait = new WebDriverWait(wd,Duration.ofSeconds(5));
         wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container>h2"))));

         WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
         String text = element.getText();
         return text;
         //return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();короткая запись
        }
        public void closeWindow(){
         click(By.xpath("//*[text()='Ok']"));
        }
        public boolean isLoggedFinish(){
         return isElementPresent(By.xpath("//*[text()=' Logout ']"));
        }
        public void logOut(){
         click(By.xpath("//*[text()=' Logout ']"));
        }
        public boolean incorrectEmail(){
         return isElementPresent(By.cssSelector("button[disabled]"));
        }
    public boolean incorrectPassword(){
        return isElementPresent(By.xpath("//*[text()='\"Login or Password incorrect\"']"));
    }
    public boolean unregesterdUser(){
        return isElementPresent(By.xpath("//*[text()='\"Login or Password incorrect\"']"));

    }

}
