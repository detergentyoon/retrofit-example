package kr.hxi.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.hxi.retrofit_example.RecyclerView.UserAdapter;
import kr.hxi.retrofit_example.RecyclerView.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitService retrofitService;

    private EditText etUsername;
    private AppCompatButton btnGet;

    private RecyclerView rvUserList;
    private UserAdapter rvUserListAdapter;

    private ArrayList<UserModel> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        btnGet = findViewById(R.id.btn_get);
        rvUserList = findViewById(R.id.rv_user_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(layoutManager);

        // Retrofit 인터페이스 구현체 가져오기
        retrofitService = RetrofitClient.getRetrofitService();

        btnGet.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            Call<UserModel> call = retrofitService.getUserData(username);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (response.isSuccessful()) {
                        UserModel user = response.body();
                        Log.d("Retrofit Status", user.toString());

                        String login = user.getLogin();
                        int id = user.getId();
                        String name = user.getName();
                        String htmlUrl = user.getHtmlUrl();
                        String location = user.getLocation();

                        user = new UserModel(login, id, name, htmlUrl, location);

                        rvUserListAdapter = new UserAdapter(userList);
                        rvUserList.setAdapter(rvUserListAdapter);

                        userList.add(user);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.non_existent_user, Toast.LENGTH_SHORT).show();
                        Log.e("Retrofit Status", "요청 실패");
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Log.e("Retrofit Status", "네트워크 오류 또는 요청 실패: " + t.getMessage());
                }
            });
        });
    } // onCreate
}