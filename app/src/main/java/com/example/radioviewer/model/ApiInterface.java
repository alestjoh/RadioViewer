package com.example.radioviewer.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("jvanaria/jvanaria.github.io/master/channels.json")
    Call<MusicList> getRadioStations();
}
