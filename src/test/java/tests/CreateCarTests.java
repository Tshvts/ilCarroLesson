package tests;

import data_provider.DPCreateCar;
import dto.CreateCarDto;
import dto.UserDtoLombok;
import lombok.Getter;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CreateCarPage;
import pages.LoginPage;
import pages.SearchPage;


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
        loginPage.clickBtnOk();
    }

    @Test(dataProviderClass = DPCreateCar.class, dataProvider = "createNewCarPositive")
    public void createCarPositiveTest(CreateCarDto createCarDto) //without address. This field doesn't work
    {
        searchPage.clickBtnLetTheCarWork();
        createCarPage.typeFormCreatingCar(createCarDto);
    }
}
