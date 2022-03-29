package com.example.ultimaterecall.ui.review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.data.DatabaseViewModel;
import com.example.ultimaterecall.databinding.FragmentReviewBinding;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.PackObject;

public class ReviewFragment extends Fragment {
    private FragmentReviewBinding binding;
    private DatabaseViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentReviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        int index = getArguments().getInt("packNumber");
        CardObject[] myCards;
        PackObject p = viewModel.getDatabase().getPack(index);
        myCards = p.getCards();

        ViewPager2 viewPager2 = root.findViewById(R.id.idReviewPager);
        ReviewPageAdapter reviewPageAdapter = new ReviewPageAdapter(getActivity().getApplicationContext(),myCards);
        viewPager2.setAdapter(reviewPageAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        return root;
    }

    public void revealCard() {
        System.out.println("hi");
    }
}
