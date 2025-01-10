package okhttp;

import dto.CarDtoApi;
import dto.ResponseMessageDto;
import dto.TokenDto;
import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilits.BaseApi;

import java.io.IOException;
import java.util.Random;

import static utilits.PropertiesReader.getProperty;

public class AddNewCarTestsOkHttp implements BaseApi
{

    TokenDto tokenDto;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass //get token one time, beforemethod -> getting is every times
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
    public void addNewCarPositiveTest()
    {
        int i = new Random().nextInt(1000);
        CarDtoApi carDtoApi = CarDtoApi.builder()
                .serialNumber("number" + i)
                .manufacture("Ford")
                .model("model")
                .year("2023")
                .fuel("Electric")
                .seats(4)
                .carClass("A")
                .pricePerDay(222.12)
                .about("About")
                .city("Haifa")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(carDtoApi),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CAR)
                .addHeader(AUTH, tokenDto.getAccessToken())
                .post(requestBody)
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            System.out.println(response.isSuccessful() + " CODE: " + response.code());
            if(response.isSuccessful())
            {
                softAssert.assertEquals(response.code(), 200);
                System.out.println(response.toString());
                ResponseMessageDto responseMessageDto = GSON.fromJson(response.body().string(), ResponseMessageDto.class);
                softAssert.assertTrue(responseMessageDto.getMessage().equals("Car added successfully"));
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
        }
    }
}
