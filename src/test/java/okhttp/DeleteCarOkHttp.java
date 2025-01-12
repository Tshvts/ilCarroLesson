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

import static utilits.PropertiesReader.getProperty;

public class DeleteCarOkHttp implements BaseApi
{
    TokenDto tokenDto;
    String id;
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

        Request request1 = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH,tokenDto.getAccessToken())
                .get()
                .build();
        try(Response response1 = OK_HTTP_CLIENT.newCall(request1).execute();)
        {
            if(response1.isSuccessful())
            {
                String responseBody1 = response1.body().string();
                CarsDto carsDto = GSON.fromJson(responseBody1,CarsDto.class);
                for (CarDtoApi cars: carsDto.getCars())
                {
                    id = cars.getSerialNumber();
                    System.out.println(id);
                    break;
                }

            }

            else
            {
                System.out.println("Not found");
            }

        }
        catch (IOException e)
        {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteCarPositiveTest()
    {

        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR + id)
                .addHeader(AUTH,tokenDto.getAccessToken())
                .delete()
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(response.isSuccessful())
            {
                String responseBody = response.body().string();
                ResponseMessageDto responseMessageDto = GSON.fromJson(responseBody,ResponseMessageDto.class);
                System.out.println(responseMessageDto.getMessage());

               softAssert.assertEquals(response.code(),200);
               softAssert.assertTrue(responseMessageDto.getMessage().contains("Car deleted successfully"));
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
    public void deleteCarNegativeTest_400()
    {

        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR+3)
                .addHeader(AUTH,tokenDto.getAccessToken())
                .delete()
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(!response.isSuccessful())
            {
                String responseBody = response.body().string();
                ErrorMessageDto errorMessageDto = GSON.fromJson(responseBody, ErrorMessageDto.class);
                System.out.println(errorMessageDto);

               softAssert.assertEquals(response.code(),400);
               softAssert.assertTrue(errorMessageDto.getError().contains("Bad Request"));
               softAssert.assertTrue(errorMessageDto.toString().contains("Car with serial number"));
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
    public void deleteCarNegativeTest_401()
    {

        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR+id)
                .addHeader(AUTH,tokenDto.getAccessToken()+"skksk")
                .delete()
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(!response.isSuccessful())
            {
                String responseBody = response.body().string();
                ErrorMessageDto errorMessageDto = GSON.fromJson(responseBody, ErrorMessageDto.class);
                System.out.println(errorMessageDto);

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

    @Test
    public void deleteCarNegativeTest_500()
    {

        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR)
                .addHeader(AUTH,tokenDto.getAccessToken())
                .delete()
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
        {
            if(!response.isSuccessful())
            {
                String responseBody = response.body().string();
                ErrorMessageDto errorMessageDto = GSON.fromJson(responseBody, ErrorMessageDto.class);
                System.out.println(errorMessageDto);

               softAssert.assertEquals(response.code(),500);
               softAssert.assertTrue(errorMessageDto.getError().contains("Internal Server Error"));
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
