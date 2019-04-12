package com.example.radioviewer.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radioviewer.R;
import com.example.radioviewer.model.Channel;
import com.example.radioviewer.model.MusicList;
import com.squareup.picasso.Picasso;

public class RadioRecyclerAdapter extends
        RecyclerView.Adapter<RadioRecyclerAdapter.RadioViewHolder> {

    private MusicList data;

    public RadioRecyclerAdapter(MusicList data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RadioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recycler_item, viewGroup, false);

        return new RadioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RadioViewHolder radioViewHolder, int i) {
        Channel ch = data.getChannels().get(i);
        radioViewHolder.description.setText(ch.getDescription());
        radioViewHolder.title.setText(ch.getTitle());
        radioViewHolder.dj.setText(ch.getDj());

        Picasso.get().load(ch.getImage()).into(radioViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.getChannels().size();
    }

    class RadioViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, dj, description;

        public RadioViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_image_preview_item);
            title = itemView.findViewById(R.id.tv_title_item);
            dj = itemView.findViewById(R.id.tv_dj_item);
            description = itemView.findViewById(R.id.tv_description_item);
        }
    }
}
