package mk.finki.ukim.mk.lab.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{
    private WebElement username;
    private WebElement password;
    private WebElement submit;
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage openLogin(WebDriver driver){
        get(driver, "/user");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static BalloonPage doLogin(WebDriver driver, LoginPage loginPage, String username, String password){
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();

        return PageFactory.initElements(driver, BalloonPage.class);
    }
}
