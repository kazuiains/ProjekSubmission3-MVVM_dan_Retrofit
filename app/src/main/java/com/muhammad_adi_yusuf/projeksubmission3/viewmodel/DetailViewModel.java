package com.muhammad_adi_yusuf.projeksubmission3.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muhammad_adi_yusuf.projeksubmission3.model.MoviesItem;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvsItem;
import com.muhammad_adi_yusuf.projeksubmission3.network.ConfigRetrofit;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.muhammad_adi_yusuf.projeksubmission3.BuildConfig.DBAPIKEY;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<List<MoviesItem>> detailMovie;
    private MutableLiveData<List<TvsItem>> detailTv;

    //melakukan get data movie
    public LiveData<List<MoviesItem>> getDetailMovie(final int id_item, String laNguage) {
        if (detailMovie == null) {
            detailMovie = new MutableLiveData<>();
            loadDetailMovie(id_item, laNguage);
        }
        return detailMovie;
    }

    //melakukan set data Movie
    private void loadDetailMovie(int id_item, final String laNguage) {
        ConfigRetrofit.serVice.getDetailMovie(id_item, DBAPIKEY, laNguage).enqueue(new Callback<MoviesItem>() {

            @Override
            public void onResponse(@NonNull Call<MoviesItem> call, @NonNull Response<MoviesItem> response) {
                if (response.isSuccessful()) {
                    detailMovie.postValue(Collections.singletonList(response.body()));
                }

            }

            @Override
            public void onFailure(@NonNull Call<MoviesItem> call, @NonNull Throwable t) {
                detailMovie.setValue(null);
            }
        });
    }


    //melakukan get data tv
    public LiveData<List<TvsItem>> getDetailTv(final int id_item, String laNguage) {
        if (detailTv == null) {
            detailTv = new MutableLiveData<>();
            loadDetailTv(id_item, laNguage);
        }
        return detailTv;
    }

    //melakukan set data tv
    private void loadDetailTv(int id_item, final String laNguage) {
        ConfigRetrofit.serVice.getDetailTv(id_item, DBAPIKEY, laNguage).enqueue(new Callback<TvsItem>() {

            @Override
            public void onResponse(@NonNull Call<TvsItem> call, @NonNull Response<TvsItem> response) {
                if (response.isSuccessful()) {
                    detailTv.postValue(Collections.singletonList(response.body()));
                }

            }

            @Override
            public void onFailure(@NonNull Call<TvsItem> call, @NonNull Throwable t) {
                detailTv.setValue(null);
            }
        });
    }
}
