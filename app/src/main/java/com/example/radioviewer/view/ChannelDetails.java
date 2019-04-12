package com.example.radioviewer.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radioviewer.R;
import com.example.radioviewer.model.Channel;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChannelDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelDetails extends Fragment {
    private static final String CHANNEL = "channel";

    private Channel channel;
    private TextView title, dj, djEmail, listeners, genre;
    private ImageView imageView;

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
        View view = inflater.inflate(R.layout.fragment_channel_details, container, false);

        title = view.findViewById(R.id.tv_title_details);
        dj = view.findViewById(R.id.tv_dj_details);
        djEmail = view.findViewById(R.id.tv_dj_email_details);
        listeners = view.findViewById(R.id.tv_num_listeners_details);
        genre = view.findViewById(R.id.tv_genre_details);
        imageView = view.findViewById(R.id.iv_image_details);

        title.setText(channel.getTitle());
        dj.setText(channel.getDj());
        djEmail.setText(channel.getDjmail());
        listeners.setText(channel.getListeners());
        genre.setText(channel.getGenre());

        Picasso.get().load(channel.getXlimage()).into(imageView);

        view.setOnClickListener(v -> playStation());

        return view;
    }

    private void playStation() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(channel.getPlaylists().get(0).getUrl()));
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
