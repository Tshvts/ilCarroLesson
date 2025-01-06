package tests;

import data_provider.DPAddCar;
import dto.CarDto;
import dto.UserDtoLombok;
import enums.Fuel;
import manager.AppManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateCarPage;
import pages.LoginPage;
import pages.SearchPage;
import utilits.RetryAnalyzer;
import utilits.TestNGListener;
import java.lang.reflect.Method;
import java.util.Random;
import static utilits.PropertiesReader.*;
import static utilits.TakeScreenshot.*;

@Listeners(TestNGListener.class)

public class CreateCarTests extends AppManager
{
    LoginPage loginPage;
    SearchPage searchPage;
    CreateCarPage createCarPage;


    @BeforeMethod
    public void login()
    {

        UserDtoLombok user = UserDtoLombok.builder()
                .email(getProperty("login.properties","email"))
                .password(getProperty("login.properties","password"))
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

    @Test(retryAnalyzer = RetryAnalyzer.class)
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
       createCarPage.clickBtnSubmit();
       takeScreenshots((TakesScreenshot) getDriver());
       Assert.assertTrue(createCarPage.isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " " + "added successful"));
    }
    //HW 15
    @Test(dataProvider = "addNewCarPositive", dataProviderClass = DPAddCar.class)
    public void createCarPositiveTestDP(CarDto car)
    {
       createCarPage.typeLetCarWorkForm(car);
       createCarPage.clickBtnSubmit();
       Assert.assertTrue(createCarPage.isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " " + "added successful"));
    }

    @Test(dataProvider = "dataProviderCarFile", dataProviderClass = DPAddCar.class)
    public void createCarPositiveTestDPFile(CarDto car, Method method)
    {
       logger.info(method.getName() + "start with data-->" + car.toString());
       createCarPage.typeLetCarWorkForm(car);
       createCarPage.clickBtnSubmit();
       Assert.assertTrue(createCarPage.isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " " + "added successful"));
    }

    @Test
    public void createCarNegativeTest_WrongCity()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city(new Random().nextInt(1000) + "")
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
       createCarPage.clickBtnSubmit();
       Assert.assertTrue(createCarPage.errorMessage("Car adding failed"));
    }
    //HW15
    @Test(dataProvider = "addCarNegative_WrongCity", dataProviderClass = DPAddCar.class)
    public void createCarNegativeTest_WrongCityDP(CarDto car)
    {
       createCarPage.typeLetCarWorkForm(car);
       createCarPage.clickBtnSubmit();
       Assert.assertTrue(createCarPage.errorMessage("Car adding failed"));
    }

    @Test
    public void createCarNegativeTest_EmptyManufacture()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("")
               .model("CX-99")
               .year("2022")
               .fuel(Fuel.HYBRID.getLocator())
               .seats(4)
               .carClass("A")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
       Assert.assertTrue(createCarPage.isElementPresentDOM("//*[text() = ' Make is required ']",5));
    }

    @Test
    public void createCarNegativeTest_EmptyModel()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("Mazda")
               .model("")
               .year("2022")
               .fuel(Fuel.HYBRID.getLocator())
               .seats(4)
               .carClass("A")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
        Assert.assertTrue(createCarPage.isElementPresentDOM("//*[text() = ' Model is required ']",5));
    }

    @Test
    public void createCarNegativeTest_WrongYear()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("Mazda")
               .model("CX-99")
               .year(new Random().nextInt(10000, 20000) + "")
               .fuel(Fuel.HYBRID.getLocator())
               .seats(4)
               .carClass("A")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
        Assert.assertTrue(createCarPage.isElementPresentDOM("//*[text() = ' Wrong year ']",5));
    }

    @Test
    public void createCarNegativeTest_EmptyFuel()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("Mazda")
               .model("CX-99")
               .year("2022")
               .seats(4)
               .carClass("A")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm_EmptyFuel(car);
       Assert.assertTrue(createCarPage.isBtnSubmitClickable());
    }

    @Test
    public void createCarNegativeTest_WrongSeats()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("Mazda")
               .model("CX-99")
               .year("2022")
               .fuel(Fuel.HYBRID.getLocator())
               .seats(21)
               .carClass("A")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
        Assert.assertTrue(createCarPage.isElementPresentDOM("//*[text() = ' To much seats ']",5));
    }

    @Test
    public void createCarNegativeTest_EmptyCarClass()
    {
       CarDto car = CarDto.builder()
               .serialNumber(new Random().nextInt(1000) + "Nfd")
               .city("Haifa")
               .manufacture("Mazda")
               .model("CX-99")
               .year("2022")
               .fuel(Fuel.HYBRID.getLocator())
               .seats(4)
               .carClass("")
               .price(123.99)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
        Assert.assertTrue(createCarPage.isElementPresentDOM("//*[text() = ' Car class is required ']",5));
    }

    @Test
    public void createCarNegativeTest_WrongPrice()
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
               .price(12399)
               .about("About my car")
               .build();
       createCarPage.typeLetCarWorkForm(car);
        Assert.assertTrue(createCarPage.isElementPresentDOM("//*[text() = ' To much big price ']",5));
    }
}
