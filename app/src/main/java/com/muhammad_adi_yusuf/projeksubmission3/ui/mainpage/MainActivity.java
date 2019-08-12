package com.muhammad_adi_yusuf.projeksubmission3.ui.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.muhammad_adi_yusuf.projeksubmission3.R;
import com.muhammad_adi_yusuf.projeksubmission3.adapter.AdapterVpager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi scroll toolbar
        Toolbar tbScroll = findViewById(R.id.tb_activity_main);
        setSupportActionBar(tbScroll);

        //tablayout and view pager
        TabLayout tabLayout = findViewById(R.id.tl_activity_main);
        ViewPager viePager = findViewById(R.id.vp_activity_main);

        //deklarasi adabter view pager beserta listnya
        AdapterVpager adapTer = new AdapterVpager(getSupportFragmentManager());
        adapTer.AddFragment(new MovieFragment(), getString(R.string.tab1));
        adapTer.AddFragment(new TvFragment(), getString(R.string.tab2));

        //set adapter viewpager dan set view pager
        viePager.setAdapter(adapTer);
        tabLayout.setupWithViewPager(viePager);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_language, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.change_language) {
            Intent newIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(newIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
