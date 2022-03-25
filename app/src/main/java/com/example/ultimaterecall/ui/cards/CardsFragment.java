package com.example.ultimaterecall.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.databinding.FragmentCardsBinding;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.PackObject;
import com.example.ultimaterecall.objects.TextCard;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    protected RecyclerView mRecyclerView;
    protected PackAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<PackObject> PackObjectArray = new ArrayList<>();
    private static final int DATASET_COUNT = 5;


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

        mAdapter = new PackAdapter(PackObjectArray);
        mRecyclerView.setAdapter(mAdapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initDataset() {
        TextCard pack1card1 = new TextCard("Card1","Konnichiwa","Hello");
        TextCard pack1card2 = new TextCard("Card2","Sayonara","Goodbye");
        CardObject pack1[] = {pack1card1,pack1card2};

        for (int i = 0; i < DATASET_COUNT; i++) {
            PackObject p = new PackObject("Test Pack #" + i,10,"This is a new pack!",pack1);
            PackObjectArray.add(p);
        }
    }

}