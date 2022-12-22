package mk.finki.ukim.mk.lab.selenium;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    @Autowired
    UserService userService;
    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    BalloonService balloonService;

    private Optional<Balloon> b1;
    private Optional<Balloon> b2;
    private Manufacturer m1;
    private Manufacturer m2;
    private User userTest;
    private User userAdmin;

    private HtmlUnitDriver driver;

    @BeforeEach
    public void setup(){
        this.driver = new HtmlUnitDriver(true);
        initData();
    }
    @AfterEach
    public  void destroy(){
        if(this.driver != null){
            this.driver.close();
        }
    }
    public void initData(){

            m1 = manufacturerService.save("m1", "m1", "m1").get();
            m2 = manufacturerService.save("m2", "m2", "m2").get();

            b1 = balloonService.save("b1", "b1", m1.getId());
            b2 = balloonService.save("b2", "b2", m2.getId());

            userTest = userService.save("user","user","user","user", LocalDate.now(), null);
            userAdmin = userService.save("admin", "admin", "admin", "admin", LocalDate.now(), null);

    }

    @Test
    public void scenarioTest(){
        this.driver = new HtmlUnitDriver(true);

        BalloonPage balloonPage = BalloonPage.to(this.driver);
        balloonPage.assertElemts(0, 0, 0,0);

        LoginPage loginPage = LoginPage.openLogin(this.driver);
        balloonPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        balloonPage.assertElemts(0, 0, 0,1);

//        balloonPage = AddOrEditBalloon.addBalloon(this.driver, "Blue", "ExtraBig", m1.getName());
//        balloonPage.assertElemts(1, 1,1,1);
//
//        balloonPage = AddOrEditBalloon.addBalloon(this.driver, "Blue", "ExtraSmall", m2.getName());
//        balloonPage.assertElemts(2, 2,2,1);
//
//        balloonPage.getDeleteButton().get(1).click();
//        balloonPage.assertElemts(1,1,1,1);
//
//        balloonPage = AddOrEditBalloon.editBalloon(this.driver, balloonPage.getEditButton().get(0), "Red", "ExtraSmall", m1.getName());
//        balloonPage.assertElemts(1,1,1,1);
    }
}
