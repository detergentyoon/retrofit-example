package kr.hxi.retrofit_example.RecyclerView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import kr.hxi.retrofit_example.RetrofitClient;
import kr.hxi.retrofit_example.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<UserModel>> userListLiveData = new MutableLiveData<>();
    private final RetrofitService retrofitService = RetrofitClient.getRetrofitService();

    public LiveData<ArrayList<UserModel>> getUserListLiveData() {
        // LiveData를 통해 데이터 읽기
        return userListLiveData;
    }

    public void getUserData(String username) {
        Call<UserModel> call = retrofitService.getUserData(username);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel user = response.body();
                    /**
                     * response.body()는 Nullable이기 때문에 반환 값이 null이어도 통과되지만
                     * 이후에 response.body()의 값을 활용하여 어떤 데이터를 만들 때,
                     * 해당 데이터의 조건이 not null 인 경우에는 Crash(비정상 종료)가 발생할 수 있다.
                     *
                     * 따라서 response.body()의 값을 활용하는 데이터를 설계하는 경우,
                     * 해당 데이터를 구성함에 있어서 null 방어에 대한 대응이 반드시 필요하다.
                     */

                    ArrayList<UserModel> userList = userListLiveData.getValue();
                    if (user != null) {
                        if (userList == null) {
                            userList = new ArrayList<>();
                        }
                        userList.add(user);
                        userListLiveData.setValue(userList);
                    }
                } else {
                    // 요청 실패
                    userListLiveData.setValue(null); // 반환받은 곳에서 에러 처리
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                userListLiveData.setValue(null); // 반환받은 곳에서 에러 처리
            }
        });
    }
}
