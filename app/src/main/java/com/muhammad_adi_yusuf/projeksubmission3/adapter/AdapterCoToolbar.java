package com.muhammad_adi_yusuf.projeksubmission3.adapter;

import android.app.Activity;
import android.content.Context;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.muhammad_adi_yusuf.projeksubmission3.R;

public class AdapterCoToolbar {
    private Context conText;

    public AdapterCoToolbar(Context conText){
        this.conText = conText;
    }

    public void setColl(final String tiTle) {

        final CollapsingToolbarLayout coolToolbar = ((Activity)conText).findViewById(R.id.ctbl_collapsing);
        coolToolbar.setTitle(" ");
        AppBarLayout appBlayout = ((Activity)conText).findViewById(R.id.al_activity_detail);
        appBlayout.setExpanded(true);

        appBlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBlayout, int verticalValue) {
                if (scrollRange == -1) {
                    scrollRange = appBlayout.getTotalScrollRange();
                }
                if (scrollRange + verticalValue == 0) {
                    coolToolbar.setTitle(tiTle);
                    isShow = true;
                } else if (isShow) {
                    coolToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}
