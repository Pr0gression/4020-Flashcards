package com.example.ultimaterecall.ui.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.data.DatabaseViewModel;
import com.example.ultimaterecall.databinding.FragmentCreatorBinding;

public class CreatorFragment extends Fragment {

    private Spinner spinner;
    private FragmentCreatorBinding binding;
    private DatabaseViewModel viewModel;
    protected CreatorAdapter cAdapter;
    protected RecyclerView cRecyclerView;
    protected RecyclerView.LayoutManager cLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cRecyclerView = (RecyclerView) root.findViewById(R.id.idCreatorBase);
        cLayoutManager = new LinearLayoutManager(getActivity());
        cRecyclerView.setLayoutManager(cLayoutManager);

        cAdapter = new CreatorAdapter();
        cRecyclerView.setAdapter(cAdapter);

        spinner = (Spinner) root.findViewById(R.id.idDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.flashcard_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return root;
    }
}
