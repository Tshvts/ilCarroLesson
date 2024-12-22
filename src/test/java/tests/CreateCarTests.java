package tests;

import dto.CarDto;
import dto.UserDtoLombok;
import enums.Fuel;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateCarPage;
import pages.LoginPage;
import pages.SearchPage;
import utilits.RandomUtils;

import java.util.Random;
import java.util.Random.*;


public class CreateCarTests extends AppManager
{
    LoginPage loginPage;
    SearchPage searchPage;
    CreateCarPage createCarPage;


    @BeforeMethod
    public void login()
    {

        UserDtoLombok user = UserDtoLombok.builder()
                .email("shevt2827@gmail.com")
                .password("Password123!")
                .build();

        loginPage = new LoginPage(getDriver());
        searchPage = new SearchPage(getDriver());
        createCarPage = new CreateCarPage(getDriver());
        searchPage.clickBtnLogIn();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        if(loginPage.MessageLogin("Logged in success"))
        {
            System.out.println("Login success");
            loginPage.clickBtnOk();
            searchPage.clickBtnLetTheCarWork();
        }
        else
            System.out.println("Smth went wrong");
    }

    @Test
    public void createCarPositiveTest()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("Mazda")
               .model("CX-99")
               .year("2022")
               .fuel(Fuel.HYBRID.getLocator())
               .seats(4)
               .carClass("A")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
       Assert.assertTrue(createCarPage.isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " " + "added successful"));
    }
}
