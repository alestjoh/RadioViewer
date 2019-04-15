package com.example.radioviewer.view;

import android.animation.ObjectAnimator;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.radioviewer.R;
import com.example.radioviewer.model.Channel;
import com.example.radioviewer.model.MusicList;
import com.example.radioviewer.presenter.MainPresenter;
import com.example.radioviewer.presenter.MainPresenterContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewContract {

    private RecyclerView recyclerView;
    private MainPresenterContract presenter;
    private boolean fragmentPresent = false;
    private String query = null;
    private FrameLayout fragmentHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fragmentHolder = findViewById(R.id.fragment_placeholder);

        presenter = new MainPresenter(this);
        presenter.initRetrofit();
        presenter.requestMusicList();

        ObjectAnimator initAnim = ObjectAnimator.ofFloat(
                fragmentHolder, "translationX", 1000f);
        initAnim.setDuration(5);
        initAnim.start();

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                fragmentPresent = false;

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                        fragmentHolder, "translationX", 1000f);
                objectAnimator.setDuration(500);
                objectAnimator.start();
            }
        });

        Intent intent = getIntent();
        handleIntent(intent);
    }

    @Override protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY).toUpperCase();
            presenter.requestMusicList();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void displayMusicList(MusicList musicList) {
        if (query != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                musicList.getChannels().removeIf(channel ->
                        !channel.getDj().toUpperCase().contains(query));
            } else {
                List<Channel> filteredList = new ArrayList<>();
                for (Channel chan : musicList.getChannels()) {
                    if (chan.getDj().toUpperCase().contains(query)) {
                        filteredList.add(chan);
                    }
                }
                musicList.setChannels(filteredList);
            }
        }
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

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                fragmentHolder, "translationX", 0f);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        //ObjectAnimator exitAnim = ObjectAnimator.ofFloat(
        //        fragmentHolder, "translationX", 600f);
        //exitAnim.setDuration(500);

        //chan.setExitTransition(exitAnim);

        //chan.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));

        fragmentManager.beginTransaction()
                .add(R.id.fragment_placeholder, chan)
                .addToBackStack(null)
                .commit();

        fragmentPresent = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        return true;
    }
}
