package com.example.radioviewer.view;

import com.example.radioviewer.model.Channel;
import com.example.radioviewer.model.MusicList;

public interface MainViewContract {
    void displayMusicList(MusicList musicList);
    void onError(String error);
    void showDetails(Channel channel);
}
