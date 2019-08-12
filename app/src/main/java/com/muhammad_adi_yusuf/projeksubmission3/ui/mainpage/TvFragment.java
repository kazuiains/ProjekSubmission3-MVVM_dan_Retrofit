package com.muhammad_adi_yusuf.projeksubmission3.ui.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muhammad_adi_yusuf.projeksubmission3.R;
import com.muhammad_adi_yusuf.projeksubmission3.adapter.AdapterRvTv;
import com.muhammad_adi_yusuf.projeksubmission3.listener.IcsRecyclerView;
import com.muhammad_adi_yusuf.projeksubmission3.model.TvsItem;
import com.muhammad_adi_yusuf.projeksubmission3.ui.detailpage.DetailActivity;
import com.muhammad_adi_yusuf.projeksubmission3.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TvFragment extends Fragment {

    private AdapterRvTv adaPter;
    private ProgressBar progressBar;
    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private ArrayList<TvsItem> tvsItem = new ArrayList<>();

    public TvFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup conTainer, @Nullable Bundle savedInstanceState) {
        View viEw = inflater.inflate(R.layout.fragment_rv_home, conTainer, false);
        progressBar = viEw.findViewById(R.id.progressBar);
        recyclerView = viEw.findViewById(R.id.rv_home);
        return viEw;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getTv(getString(R.string.idlanguage)).observe((LifecycleOwner) Objects.requireNonNull(getContext()), getData);

        adaPter = new AdapterRvTv(getContext(),tvsItem);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adaPter);

        IcsRecyclerView.addTo(recyclerView).setOnItemClickListener(new IcsRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int poSition, View v) {

                TvsItem iTem = tvsItem.get(poSition);
                int id_item = iTem.getId();
                Intent intentJump = new Intent(getContext(), DetailActivity.class);
                intentJump.putExtra("id_item", id_item);
                intentJump.putExtra("type", getString(R.string.type2));
                startActivity(intentJump);

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
    }

    private Observer<List<TvsItem>> getData = new Observer <List<TvsItem>>() {
        @Override
        public void onChanged(List<TvsItem> tvsItem) {
            if (tvsItem != null) {
                progressBar.setVisibility(View.GONE);
                adaPter.setData(tvsItem);
            }else {
                Toast.makeText(getContext(), getString(R.string.error_ms), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }

    };
}
