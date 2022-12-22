package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage{
    @FindBy(css = "tr[class=Balloon]")
    private List<WebElement> balloonRows;
    @FindBy(css = ".aEdit")
    private List<WebElement> EditButton;
    @FindBy(css = ".btnDelete")
    private List<WebElement> DeleteButton;
    @FindBy(css = ".aNewBalloon")
    private List<WebElement> NewBalloonButton;

    public BalloonPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonPage to(WebDriver driver){
        get(driver, "/balloon");
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public void assertElemts(int BalloonNumber, int Editbtn,  int Deletebtn, int NewBalloonbtn){
        Assert.assertEquals("rows do not match", BalloonNumber, this.getBalloonRows().size());
        Assert.assertEquals("edit do not match", Editbtn, this.getEditButton().size());
        Assert.assertEquals("delete do not match", Deletebtn, this.getDeleteButton().size());
        Assert.assertEquals("NewBalloon do not match", NewBalloonbtn, this.getNewBalloonButton().size());
    }
}
