package tests;

import dto.UserDtoLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.SearchPage;

import java.util.Random;

public class RegistrationTests extends AppManager
{
    RegistrationPage registrationPage;

    @Test
    public void registrationPositiveTest()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Bob")
                .lastName("Doe")
                .email(i+"bob_adk_@gmail.com")
                .password("Password123!")
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isPopIpMessagePresent());
    }
}
