package utilits;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface BaseApi
{
    String BASE_URL = "https://ilcarro-backend.herokuapp.com/";

    String REGISTRATION = "v1/user/registration/usernamepassword";

    String INVALID_TOKEN = "Bearer NoAuth";

    String LOGIN = "v1/user/login/usernamepassword";

    String ADD_NEW_CAR = "v1/cars";

    String AUTH = "Authorization";

    String GET_USER_CARS = "v1/cars/my";

    String DELETE_CAR = "v1/cars/";

    Gson GSON = new Gson();

    MediaType JSON = MediaType.get("application/json");

    MediaType INVALID_JSON = MediaType.get("application/js");

    OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

}
