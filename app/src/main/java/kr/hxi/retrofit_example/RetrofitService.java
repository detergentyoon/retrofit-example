package kr.hxi.retrofit_example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("users/{username}") // 동적으로 username 적용
    Call<UserModel> getUserData(
            @Path("username") String username
    );
}
