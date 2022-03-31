package com.example.ultimaterecall.ui.creation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ultimaterecall.data.DatabaseViewModel;
import com.example.ultimaterecall.databinding.FragmentCreatorBinding;

public class CreatorFragment extends Fragment {

    private FragmentCreatorBinding binding;
    private DatabaseViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
    }
}
