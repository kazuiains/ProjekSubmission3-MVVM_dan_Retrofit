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
import com.muhammad_adi_yusuf.projeksubmission3.model.TvsItem;

import java.util.List;

public class AdapterRvTv extends RecyclerView.Adapter<AdapterRvTv.gViewholder>{
    private Context conText;
    private List<TvsItem> resultsItem;

    public void setData(List<TvsItem> iTems) {
        resultsItem.addAll(iTems);
        notifyDataSetChanged();
    }

    public AdapterRvTv(Context conText, List<TvsItem> dataList) {
        this.conText = conText;
        this.resultsItem = dataList;
    }

    @NonNull
    @Override
    public AdapterRvTv.gViewholder onCreateViewHolder(@NonNull ViewGroup paRent, int viewType) {
        View itemRow = LayoutInflater.from(paRent.getContext()).inflate(R.layout.rv_item, paRent, false);
        return new AdapterRvTv.gViewholder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRvTv.gViewholder ghoLder, final int poSition) {

        final String tiTle = resultsItem.get(poSition).getName();
        final String reLease = resultsItem.get(poSition).getFirstAirDate();
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
