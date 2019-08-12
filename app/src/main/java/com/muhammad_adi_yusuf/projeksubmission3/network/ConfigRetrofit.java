package com.muhammad_adi_yusuf.projeksubmission3.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.muhammad_adi_yusuf.projeksubmission3.BuildConfig.BASE_URL;

public class ConfigRetrofit {
    private static Retrofit retRofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static MoviedbService serVice = retRofit.create(MoviedbService.class);
}
