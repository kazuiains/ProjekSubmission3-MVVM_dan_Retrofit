package com.muhammad_adi_yusuf.projeksubmission3.network;

import com.muhammad_adi_yusuf.projeksubmission3.model.MovieResponse;
import com.muhammad_adi_yusuf.projeksubmission3.model.MoviesItem;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvResponse;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvsItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviedbService {

    @GET("discover/movie")
    Call<MovieResponse> getMovieList(
                                            @Query("api_key") String api_key,
                                            @Query("language") String language);

    @GET("movie/{id_item}")
    Call<MoviesItem> getDetailMovie(
                                            @Path("id_item") int id,
                                            @Query("api_key") String api_key,
                                            @Query("language") String language);

    @GET("discover/tv")
    Call<TvResponse> getTvList(
                                        @Query("api_key") String api_key,
                                        @Query("language") String language);

    @GET("tv/{id_item}")
    Call<TvsItem> getDetailTv(
                                        @Path("id_item") int id,
                                        @Query("api_key") String api_key,
                                        @Query("language") String language);

}
