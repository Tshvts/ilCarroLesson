package tests;

import dto.UserDtoLombok;
import lombok.Getter;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.SearchPage;

import java.util.Random;

public class LoginTests extends AppManager
{
        LoginPage loginPage;
        RegistrationPage registrationPage;
        SearchPage searchPage;

        @Getter
        private String email, password;

    @BeforeMethod
    public void registrationPositiveTest()
    {
        int i = new Random().nextInt(1000)+1000;
        email = i+"bob_adk_@gmail.com";
        password = "Password123!";
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Bob")
                .lastName("Doe")
                .email(email)
                .password(password)
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        registrationPage.clickBtnYalla();
    }

        @Test
    public void loginPositiveTest()
        {
            UserDtoLombok user = UserDtoLombok.builder()
                    .email(email)
                    .password(password)
                    .build();

            loginPage = new LoginPage(getDriver());
            loginPage.clickBtnOk();
            searchPage = new SearchPage(getDriver());
            searchPage.clickBtnLogOut();
            searchPage.clickBtnLogIn();
            loginPage.typeLoginForm(user);
            loginPage.clickBtnYalla();
            Assert.assertTrue(loginPage.isPopUpMessagePresentLogin());
        }

        @Test
        public void loginNegativeTest()
        {
            UserDtoLombok user = UserDtoLombok.builder()
                    .email("dddddddddd@f")
                    .password(password)
                    .build();
            loginPage = new LoginPage(getDriver());
            loginPage.clickBtnOk();
            searchPage = new SearchPage(getDriver());
            searchPage.clickBtnLogOut();
            searchPage.clickBtnLogIn();
            loginPage.typeLoginForm(user);
            loginPage.clickBtnYalla();
            Assert.assertTrue(loginPage.isMistakeMessagePresentLogin());
        }
}
