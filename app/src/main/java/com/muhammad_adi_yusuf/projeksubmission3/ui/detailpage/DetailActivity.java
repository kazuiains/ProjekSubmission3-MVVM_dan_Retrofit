package com.muhammad_adi_yusuf.projeksubmission3.ui.detailpage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.muhammad_adi_yusuf.projeksubmission3.R;
import com.muhammad_adi_yusuf.projeksubmission3.adapter.AdapterCoToolbar;
import com.muhammad_adi_yusuf.projeksubmission3.model.MoviesItem;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvsItem;
import com.muhammad_adi_yusuf.projeksubmission3.viewmodel.DetailViewModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private ConstraintLayout constraintLayout;
    private DetailViewModel viewModel;
    private String titleToolbar;
    TextView dataTitle, dataTitle2, dataRelease, dataOverview, dataLanguage, dataRate;
    ImageView dataImage, dataBackimage;
    private AdapterCoToolbar collapsingToolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //inisialisasi
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        progressBar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.ll_error);
        constraintLayout = findViewById(R.id.cl_progressbar);
        collapsingToolbar = new AdapterCoToolbar(this);
        iniTialize();
        Toolbar toolBar = findViewById(R.id.tb_activity_detail);
        setSupportActionBar(toolBar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int id_item = getIntent().getIntExtra("id_item", 0);
        String type = getIntent().getStringExtra("type");

        assert type != null;
        if (type.equals(getString(R.string.type1))) {
            loadDetailMovie(id_item, getString(R.string.idlanguage));

        } else if (type.equals(getString(R.string.type2))) {
            loadDetailTv(id_item, getString(R.string.idlanguage));
        }

    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void loadDetailMovie(final int id_item, String lanGuage) {
        viewModel.getDetailMovie(id_item, lanGuage).observe(this, new Observer<List<MoviesItem>>() {
            @Override
            public void onChanged(List<MoviesItem> detailMovie) {
                if (detailMovie != null) {
                    dataTitle.setText(detailMovie.get(0).getTitle());
                    dataTitle2.setText(detailMovie.get(0).getTitle());
                    dataRelease.setText(detailMovie.get(0).getReleaseDate());
                    dataOverview.setText(detailMovie.get(0).getOverview());
                    dataLanguage.setText(detailMovie.get(0).getOriginalLanguage());
                    dataRate.setText(String.valueOf(detailMovie.get(0).getVoteAverage()));

                    String link_gambar1 = "https://image.tmdb.org/t/p/w185" + detailMovie.get(0).getPosterPath();
                    Glide.with(getBaseContext())
                            .load(link_gambar1)
                            .apply(new RequestOptions().override(185, 278))
                            .into(dataImage);

                    String link_gambar2 = "https://image.tmdb.org/t/p/w500" + detailMovie.get(0).getPosterPath();
                    Glide.with(getBaseContext())
                            .load(link_gambar2)
                            .apply(new RequestOptions().override(185, 278))
                            .into(dataBackimage);

                    titleToolbar = detailMovie.get(0).getTitle();

                    collapsingToolbar.setColl(titleToolbar);
                    progressBar.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_ms), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void loadDetailTv(final int id_item, String lanGuage) {
        viewModel.getDetailTv(id_item, lanGuage).observe(this, new Observer<List<TvsItem>>() {
            @Override
            public void onChanged(List<TvsItem> detailTv) {
                if (detailTv != null) {
                    dataTitle.setText(detailTv.get(0).getName());
                    dataTitle2.setText(detailTv.get(0).getName());
                    dataRelease.setText(detailTv.get(0).getFirstAirDate());
                    dataOverview.setText(detailTv.get(0).getOverview());
                    dataLanguage.setText(detailTv.get(0).getOriginalLanguage());
                    dataRate.setText(String.valueOf(detailTv.get(0).getVoteAverage()));

                    String link_gambar1 = "https://image.tmdb.org/t/p/w185" + detailTv.get(0).getPosterPath();
                    Glide.with(getBaseContext())
                            .load(link_gambar1)
                            .apply(new RequestOptions().override(185, 278))
                            .into(dataImage);

                    String link_gambar2 = "https://image.tmdb.org/t/p/w500" + detailTv.get(0).getPosterPath();
                    Glide.with(getBaseContext())
                            .load(link_gambar2)
                            .apply(new RequestOptions().override(185, 278))
                            .into(dataBackimage);

                    titleToolbar = detailTv.get(0).getName();

                    collapsingToolbar.setColl(titleToolbar);
                    progressBar.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_ms), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void iniTialize(){
        dataTitle = findViewById(R.id.tv_detail_title);
        dataTitle2 = findViewById(R.id.tv_Dtitle);
        dataRelease = findViewById(R.id.tv_Drelease);
        dataOverview = findViewById(R.id.tv_Doverview);
        dataLanguage = findViewById(R.id.tv_Dlanguage);
        dataRate = findViewById(R.id.tv_detail_rate);

        dataImage = findViewById(R.id.iv_detail_image);
        dataBackimage = findViewById(R.id.iv_backimage);
    }
}
