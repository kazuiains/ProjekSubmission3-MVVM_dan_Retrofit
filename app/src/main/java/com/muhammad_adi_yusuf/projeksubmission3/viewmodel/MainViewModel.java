package com.muhammad_adi_yusuf.projeksubmission3.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muhammad_adi_yusuf.projeksubmission3.model.MovieResponse;
import com.muhammad_adi_yusuf.projeksubmission3.model.MoviesItem;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvResponse;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvsItem;
import com.muhammad_adi_yusuf.projeksubmission3.network.ConfigRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.muhammad_adi_yusuf.projeksubmission3.BuildConfig.DBAPIKEY;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<MoviesItem>> listMovie;
    private MutableLiveData<List<TvsItem>> listTv;

    //melakukan get data movie
    public LiveData<List<MoviesItem>> getMovie(final String laNguage) {
        if (listMovie == null) {
            listMovie = new MutableLiveData<>();
            loadMovie(laNguage);
        }
        return listMovie;
    }

    //melakukan set data Movie
    private void loadMovie(final String laNguage) {
        ConfigRetrofit.serVice.getMovieList(DBAPIKEY, laNguage).enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    listMovie.postValue(response.body().getResults());
                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                listMovie.setValue(null);
            }
        });
    }

    //melakukan get data Tv
    public LiveData<List<TvsItem>> getTv(final String laNguage) {
        if (listTv == null) {
            listTv = new MutableLiveData<>();
            loadTv(laNguage);
        }
        return listTv;
    }

    //melakukan set data Tv
    private void loadTv(final String laNguage) {
        ConfigRetrofit.serVice.getTvList(DBAPIKEY, laNguage).enqueue(new Callback<TvResponse>() {

            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    listTv.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                listTv.setValue(null);
            }
        });
    }

}
