package com.example.ultimaterecall.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.data.DatabaseViewModel;
import com.example.ultimaterecall.data.FakeDatabase;
import com.example.ultimaterecall.databinding.FragmentCardsBinding;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.PackObject;

import java.util.ArrayList;

public class PackFragment extends Fragment {

    private FragmentCardsBinding binding;
    protected RecyclerView mRecyclerView;
    protected CardAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<CardObject> CardObjectArray = new ArrayList<>();
    private static final int DATASET_COUNT = 5;
    private DatabaseViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        initDataset(getArguments().getInt("packEditNumber"));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mRecyclerView = (RecyclerView) root.findViewById(R.id.idPackRV);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CardAdapter(CardObjectArray);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity().getApplicationContext(), mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("cardNumber",position);
                Navigation.findNavController(view).navigate(R.id.action_navigation_cards_to_reviewFragment,bundle);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initDataset(int packNumber) {
        FakeDatabase fd = viewModel.getDatabase();
        CardObjectArray = fd.getPack(packNumber).getCards();
    }

}