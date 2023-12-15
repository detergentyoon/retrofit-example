package kr.hxi.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
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
import kr.hxi.retrofit_example.RecyclerView.UserViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    private EditText etUsername;
    private AppCompatButton btnGet;

    private RecyclerView rvUserList;
    private UserAdapter rvUserListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        btnGet = findViewById(R.id.btn_get);
        rvUserList = findViewById(R.id.rv_user_list);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(layoutManager);

        btnGet.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            userViewModel.getUserData(username);
        });

        observeViewModel();
    } // onCreate

    private void observeViewModel() {
        userViewModel.getUserListLiveData().observe(this, userList -> {
            if (userList != null && !userList.isEmpty()) {
                rvUserListAdapter = new UserAdapter(userList);
                rvUserList.setAdapter(rvUserListAdapter);
            } else {
                Log.e("Retrofit Status", "요청 실패");
                Toast.makeText(MainActivity.this, R.string.non_existent_user, Toast.LENGTH_SHORT).show();
            }
        });
    }
}