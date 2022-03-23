package com.example.ultimaterecall.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.databinding.FragmentCardsBinding;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    protected RecyclerView mRecyclerView;
    protected PackAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;
    private static final int DATASET_COUNT = 60;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CardsViewModel cardsViewModel =
                new ViewModelProvider(this).get(CardsViewModel.class);

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mRecyclerView = (RecyclerView) root.findViewById(R.id.idPackRV);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PackAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }
}