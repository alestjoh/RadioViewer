package com.example.radioviewer.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radioviewer.R;
import com.example.radioviewer.model.Channel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChannelDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelDetails extends Fragment {
    private static final String CHANNEL = "channel";

    private Channel channel;

    public ChannelDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param channel
     * @return A new instance of fragment ChannelDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelDetails newInstance(Channel channel) {
        ChannelDetails fragment = new ChannelDetails();
        Bundle args = new Bundle();
        args.putParcelable(CHANNEL, channel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            channel = getArguments().getParcelable(CHANNEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channel_details, container, false);
    }
}
