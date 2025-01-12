package okhttp;

import dto.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilits.BaseApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static utilits.PropertiesReader.getProperty;

public class GetUserCarsTestsOkHttp implements BaseApi
{
    TokenDto tokenDto;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(getProperty("login.properties","email"))
                .password(getProperty("login.properties","password"))
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            if(response.isSuccessful())
            {
                tokenDto = GSON.fromJson(response.body().string(), TokenDto.class);
                System.out.println(tokenDto);
            }

            else
            {
                System.out.println("Login wrong");
            }
        }

        catch (IOException e)
        {
            System.out.println("Login wrong, created exception");
        }
    }

    @Test
    public void getUserCarsPositiveTest()
    {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH,tokenDto.getAccessToken())
                .get()
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(response.isSuccessful())
            {
                String responseBody = response.body().string();
                CarsDto carsDto = GSON.fromJson(responseBody,CarsDto.class);
                for(CarDtoApi car: carsDto.getCars())
                {
                    System.out.println(car);
                }
                Assert.assertEquals(response.code(),200);
            }

            else
            {
                Assert.fail("RESPONSE STATUS CODE: " + response.code());
            }

        }
        catch (IOException e)
        {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

    //I can't get 400 code
    @Test
    public void getUserCarsNegativeTest_400()
    {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH,tokenDto.getAccessToken())
                .post(RequestBody.create("", JSON))
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(!response.isSuccessful())
            {
                String responseBody = response.body().string();
                ErrorMessageDto errorMessageDto = GSON.fromJson(responseBody, ErrorMessageDto.class);
                System.out.println(errorMessageDto);
                softAssert.assertEquals(response.code(),403);
                softAssert.assertAll();
            }

            else
            {
                Assert.fail("RESPONSE STATUS CODE: " + response.code());
            }

        }
        catch (IOException e)
        {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getUserCarsNegativeTest_401()
    {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH,INVALID_TOKEN)
                .get()
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(!response.isSuccessful())
            {
                String responseBody = response.body().string();
                ErrorMessageDto errorMessageDto = GSON.fromJson(responseBody, ErrorMessageDto.class);
                System.out.println(errorMessageDto.getMessage());
                System.out.println("Failed test: " + response.code());
                softAssert.assertEquals(response.code(),401);
                softAssert.assertTrue(errorMessageDto.toString().contains("must contain"));
                softAssert.assertAll();
            }

            else
            {
                Assert.fail("RESPONSE STATUS CODE: " + response.code());
            }

        }
        catch (IOException e)
        {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

// I can't get 500 code
    @Test
    public void getUserCarsNegativeTest_500()
    {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk                             &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")
                .addHeader(AUTH, tokenDto.getAccessToken() + "dd                                        &*#**(@HEISNSNKWLMSMSKKWNKE OIEOIIPIEOPEOEKSJSSSAOOAOIOIA")
                .get()
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(!response.isSuccessful())
            {
                String responseBody = response.body().string();
                ErrorMessageDto errorMessageDto = GSON.fromJson(responseBody, ErrorMessageDto.class);
                System.out.println(errorMessageDto);
                System.out.println("Failed test: " + response.code());
                softAssert.assertEquals(response.code(),401);
                softAssert.assertTrue(errorMessageDto.getError().contains("Unauthorized"));
                softAssert.assertAll();
            }

            else
            {
                Assert.fail("RESPONSE STATUS CODE: " + response.code());
            }

        }
        catch (IOException e)
        {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }
}
