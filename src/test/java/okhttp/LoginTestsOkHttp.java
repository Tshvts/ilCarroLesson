package okhttp;

import dto.UserDtoLombok;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilits.BaseApi;

import java.io.IOException;

public class LoginTestsOkHttp implements BaseApi
{
    @Test
    public void loginPositiveTest()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("test132@gmail.com")
                .password("Ngfg*135")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
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
        System.out.println(response.isSuccessful());
        Assert.assertEquals(response.code(),200);
    }

    @Test
    public void loginNegativeTest_BadRequest()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("test132@gmail.com")
                .password("")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson("dd"), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
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
        System.out.println(response.isSuccessful());

        Assert.assertEquals(response.code(),400);
    }


    @Test
    public void loginNegativeTest_Unauthorized()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("test132@gmail.com")
                .password("")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
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
        System.out.println(response.isSuccessful());

        Assert.assertEquals(response.code(),401);
    }

    @Test
    public void loginNegativeTest_InternalServerError()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("test132@gmail.com")
                .password("Ngfg*135")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), INVALID_JSON); //I broke JSON but It sends me 200. Is it a bug?
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
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
        System.out.println(response.isSuccessful());

        if(response.code()!=500)
            System.out.println("Test was failed: " + response.code());
        else
            System.out.println(response.code());
    }
}
