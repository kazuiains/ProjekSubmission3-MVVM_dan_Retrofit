package com.muhammad_adi_yusuf.projeksubmission3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.muhammad_adi_yusuf.projeksubmission3.R;
import com.muhammad_adi_yusuf.projeksubmission3.model.MoviesItem;

import java.util.List;

public class AdapterRvMovie extends RecyclerView.Adapter<AdapterRvMovie.gViewholder> {
    private Context conText;
    private List<MoviesItem> resultsItem;

    public void setData(List<MoviesItem> iTems) {
        resultsItem.addAll(iTems);
        notifyDataSetChanged();
    }

    public AdapterRvMovie(Context conText, List<MoviesItem> dataList) {
        this.conText = conText;
        this.resultsItem = dataList;
    }

    @NonNull
    @Override
    public gViewholder onCreateViewHolder(@NonNull ViewGroup paRent, int viewType) {
        View itemRow = LayoutInflater.from(paRent.getContext()).inflate(R.layout.rv_item, paRent, false);
        return new gViewholder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final gViewholder ghoLder, final int poSition) {

        final String tiTle = resultsItem.get(poSition).getTitle();
        final String reLease = resultsItem.get(poSition).getReleaseDate();
        final double raTe = resultsItem.get(poSition).getVoteAverage();
        final String imaGe = "https://image.tmdb.org/t/p/w185" + resultsItem.get(poSition).getPosterPath();

        ghoLder.dataTitle.setText(tiTle);
        ghoLder.dataRelease.setText(reLease);
        ghoLder.dataRate.setText(String.valueOf(raTe));

        Glide.with(conText)
                .load(imaGe)
                .apply(new RequestOptions().override(185, 278))
                .into(ghoLder.dataImage);
    }

    @Override
    public int getItemCount() {
        return resultsItem.size();
    }

    class gViewholder extends RecyclerView.ViewHolder {
        ImageView dataImage;
        TextView dataTitle, dataRelease, dataRate;

        gViewholder(@NonNull View itemView) {
            super(itemView);
            dataImage = itemView.findViewById(R.id.iv_image);
            dataTitle = itemView.findViewById(R.id.tv_title);
            dataRelease = itemView.findViewById(R.id.tv_release);
            dataRate = itemView.findViewById(R.id.tv_rating);
        }
    }
}
