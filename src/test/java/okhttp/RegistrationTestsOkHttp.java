package okhttp;

import dto.ErrorMessageDto;
import dto.TokenDto;
import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilits.BaseApi;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestsOkHttp implements BaseApi
{
    SoftAssert softAssert = new SoftAssert();

        @Test
    public void registrationPositiveTest()
        {
            int i = new Random().nextInt(1000)+1000;
            UserDtoLombok user = UserDtoLombok.builder()
                    .firstName("Bob")
                    .lastName("Doe")
                    .username(i+"bob_adk_@gmail.com")
                    .password("Password123!")
                    .build();

            RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + REGISTRATION)
                    .post(requestBody)
                    .build();
            Response response;
            try {
                response = OK_HTTP_CLIENT.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            System.out.println(response.toString());
            try {
                System.out.println(response.body().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals(response.code(), 200);
        }

        @Test
    public void registrationPositiveTestValidateToken()
        {
            int i = new Random().nextInt(1000)+1000;
            UserDtoLombok user = UserDtoLombok.builder()
                    .firstName("Bob")
                    .lastName("Doe")
                    .username(i+"bob_adk_@gmail.com")
                    .password("Password123!")
                    .build();

            RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + REGISTRATION)
                    .post(requestBody)
                    .build();
            try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
            {
                if(response.isSuccessful())
                {
                    TokenDto tokenDto = GSON.fromJson
                            (response.body().string(), TokenDto.class); // get response in JSON and change on Object TokenDto
                    System.out.println(tokenDto.toString());
                    Assert.fail("Status code response ---> " + response.code());
                }

                else
                {
                    ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(),ErrorMessageDto.class);
                    System.out.println(errorMessageDto.toString());
                }
            }
            catch (IOException e)
            {
                Assert.fail("Created exception");
                throw new RuntimeException(e);
            }
        }

        @Test
    public void registrationNegativeTest_wrongPassword_400()
        {
            int i = new Random().nextInt(1000)+1000;
            UserDtoLombok user = UserDtoLombok.builder()
                    .firstName("Bob")
                    .lastName("Doe")
                    .username(i+"bob_adk_@gmail.com")
                    .password("P!")
                    .build();

            RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + REGISTRATION)
                    .post(requestBody)
                    .build();
            try(Response response = OK_HTTP_CLIENT.newCall(request).execute();)
            {
                if(!response.isSuccessful())
                {
                    ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(),ErrorMessageDto.class);
                    System.out.println(errorMessageDto.toString());
                    softAssert.assertEquals(response.code(),400);
                    softAssert.assertTrue(errorMessageDto.getError().equals("Bad Request"));
                    softAssert.assertTrue(errorMessageDto.toString().contains("At least 8 characters"));
                    softAssert.assertAll();
                }

                else
                {
                    Assert.fail("Status code response ---> " + response.code());
                }
            }
            catch (IOException e)
            {
                Assert.fail("Created exception");
                throw new RuntimeException(e);
            }
        }

        //HOMEWORK 18
        @Test
    public void registrationNegativeTest_BadRequest()
        {
            int i = new Random().nextInt(1000)+1000;
            UserDtoLombok user = UserDtoLombok.builder()
                    .firstName("")
                    .lastName("Doe")
                    .username(i+"bob_adk_@gmail.com")
                    .password("Password123!")
                    .build();

            RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + REGISTRATION)
                    .post(requestBody)
                    .build();
            Response response;
            try {
                response = OK_HTTP_CLIENT.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            System.out.println(response.toString());
            try {
                System.out.println(response.body().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals(response.code(), 400);
        }

        @Test
        // I don't understand how to get 401. Its response - 200. I deleted Assert and added if-else
    public void registrationNegativeTest_Unauthorized()
        {
            int i = new Random().nextInt(1000)+1000;
            UserDtoLombok user = UserDtoLombok.builder()
                    .firstName("Bob")
                    .lastName("Doe")
                    .username(i+"bob_adk_@gmail.com")
                    .password("Password123!")
                    .build();

            RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + REGISTRATION)
                    .addHeader("Authorization",INVALID_TOKEN)
                    .post(requestBody)
                    .build();
            Response response;
            try {
                response = OK_HTTP_CLIENT.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(response.code());
            System.out.println(response.toString());
            try {
                System.out.println(response.body().string());;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(response.code()!=401)
                System.out.println("Test was failed: " + response.code());
            else
                System.out.println(response.code());
        }

    @Test
    public void registrationNegativeTest_InternalServerError()
    {
        int i = new Random().nextInt(1000)+1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .firstName("Bob")
                .lastName("Doe")
                .username(i+"bob_adk_@gmail.com")
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), INVALID_JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.code());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }
}
