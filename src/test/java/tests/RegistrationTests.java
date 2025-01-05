package tests;

import dto.UserDtoLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.SearchPage;
import utilits.TestNGListener;

import java.util.Random;

@Listeners(TestNGListener.class)

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
        Assert.assertTrue(registrationPage.registrationMessage("You are logged in success"));
    }

    //homework 9

    @Test
    public void registrationNegativeTest_wrongName()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .name("    ") // there is a bug (user can register with only symbols or numbers)
                .lastName("Doe")
                .email(i+"bob_adk_@gmail.com")
                .password("Password123!")
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.registrationMessage("must not be blank"));
    }

    @Test
    public void registrationNegativeTest_wrongLastName()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Tanya")
                .lastName("      ")// there is a bug (user can register with only symbols or numbers)
                .email(i+"bob_adk_@gmail.com")
                .password("Password123!")
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.registrationMessage("must not be blank"));
    }

    @Test
    public void registrationNegativeTest_wrongEmail()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Tanya")
                .lastName("Fidelman")
                .email(i+"gmail.com")
                .password("Password123!")
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        Assert.assertTrue(registrationPage.registrationHighlightedFieldEmail("Wrong email format"));
    }

    @Test
    public void registrationNegativeTest_wrongPassword()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Tanya")
                .lastName("Fidelman")
                .email(i+"bob_adk_@gmail.com")
                .password("Pa")
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        Assert.assertTrue(registrationPage.registrationHighlightedFieldPassword("Password must contain minimum 8 symbols"));
    }

    @Test
    public void registrationNegativeTest_wrongPassword1()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Tanya")
                .lastName("Fidelman")
                .email(i+"bob_adk_@gmail.com")
                .password("aaaaaaaa")
                .build();
        new SearchPage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        Assert.assertTrue(registrationPage.registrationHighlightedFieldPassword1("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_unsignedCheckbox()
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
        Assert.assertTrue(registrationPage.disabledBtn());
    }
}
