package com.example.radioviewer.presenter;

import com.example.radioviewer.model.ApiInterface;
import com.example.radioviewer.model.MusicList;
import com.example.radioviewer.view.MainViewContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainPresenterContract, Callback<MusicList> {

    ApiInterface api;
    MainViewContract view;

    public MainPresenter(MainViewContract view) {
        this.view = view;
    }

    @Override
    public void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiInterface.class);
    }

    @Override
    public void requestMusicList() {
        if (api == null) {
            initRetrofit();
        }
        api.getRadioStations().enqueue(this);
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<MusicList> call, Response<MusicList> response) {
        view.displayMusicList(response.body());
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<MusicList> call, Throwable t) {
        view.onError("Failed to connect to database");
    }
}
