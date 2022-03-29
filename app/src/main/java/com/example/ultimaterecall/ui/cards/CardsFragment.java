package com.example.ultimaterecall.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.data.FakeDatabase;
import com.example.ultimaterecall.databinding.FragmentCardsBinding;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.PackObject;
import com.example.ultimaterecall.objects.TextCard;
import com.example.ultimaterecall.data.DatabaseViewModel;

import java.util.ArrayList;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    protected RecyclerView mRecyclerView;
    protected PackAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<PackObject> PackObjectArray = new ArrayList<>();
    private static final int DATASET_COUNT = 5;
    private DatabaseViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        initDataset();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mRecyclerView = (RecyclerView) root.findViewById(R.id.idPackRV);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PackAdapter(PackObjectArray);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity().getApplicationContext(), mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                System.out.println("Hey there from " + position);
            }

            @Override
            public void onLongClick(View view, int position) {
                System.out.println("Hey there");
            }
        }));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initDataset() {
        FakeDatabase fd = viewModel.getDatabase();
        PackObjectArray = fd.getPacks();
    }

    public void editClicked() {

    }

}