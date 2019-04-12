package com.example.radioviewer.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.radioviewer.R;
import com.example.radioviewer.model.Channel;
import com.example.radioviewer.model.MusicList;
import com.example.radioviewer.presenter.MainPresenter;
import com.example.radioviewer.presenter.MainPresenterContract;

public class MainActivity extends AppCompatActivity implements MainViewContract {

    private RecyclerView recyclerView;
    private MainPresenterContract presenter;
    private boolean fragmentPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new MainPresenter(this);
        presenter.initRetrofit();
        presenter.requestMusicList();

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0)
                fragmentPresent = false;
        });


        //ActionBar actionBar = getSupportActionBar();
    }

    @Override
    public void displayMusicList(MusicList musicList) {
        recyclerView.setAdapter(new RadioRecyclerAdapter(musicList, this));
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(Channel channel) {
        if (fragmentPresent) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        ChannelDetails chan = ChannelDetails.newInstance(channel);

        fragmentManager.beginTransaction()
                .add(R.id.fragment_placeholder, chan)
                .addToBackStack(null)
                .commit();

        fragmentPresent = true;
    }
}
