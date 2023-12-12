package kr.hxi.retrofit_example;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://api.github.com/";

    public static RetrofitService getRetrofitService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder() // retrofit 객체 생성
                    .baseUrl(BASE_URL) // 어떤 서버(BASE_URL)로 네트워크 통신을 할 것인지 설정
                    .addConverterFactory(GsonConverterFactory.create()) // 통신 완료 후 어떤 Converter로 데이터를 파싱할 것인지
                    .build(); // 통신하여 데이터를 파싱한 retrofit 객체 생성
        }
        return retrofit.create(RetrofitService.class);
    }

}
