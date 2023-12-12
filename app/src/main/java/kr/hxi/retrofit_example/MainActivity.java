package kr.hxi.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitService retrofitService;

    private ImageView ivAvatar;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivAvatar = findViewById(R.id.iv_avatar);
        tvResponse = findViewById(R.id.tv_response);

        // Retrofit 인터페이스 구현체 가져오기
        retrofitService = RetrofitClient.getRetrofitService();

        String username = "detergentyoon";

        Call<UserModel> call = retrofitService.getUserData(username);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel user = response.body();

                    setUserData(user);
                } else {
                    Log.e("Retrofit Status", "요청 실패");
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("Retrofit Status", "네트워크 오류 또는 요청 실패: " + t.getMessage());
            }
        });

    } // onCreate

    private void setUserData(UserModel user) {
        String userData =
                "Login: " + user.getLogin() +
                "\nID: " + user.getId() +
                "\nhtml_url: " + user.getHtmlUrl() +
                "\nName: " + user.getName() +
                "\nLocation: " + user.getLocation();
        tvResponse.setText(userData);

        String avatarUrl = user.getAvatarUrl();
        Glide.with(this).load(avatarUrl).into(ivAvatar);
    }
}