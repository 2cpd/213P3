package com.example.photos.ui.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.photos.R;
import com.example.photos.databinding.FragmentPhotosBinding;
import com.example.photos.shared.SharedViewModel;
import com.example.photos.ui.home.HomeFragment;
import com.example.photos.ui.results.ResultsViewModel;

public class PhotosFragment extends Fragment {

    private FragmentPhotosBinding binding;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResultsViewModel searchViewModel =
                new ViewModelProvider(this).get(ResultsViewModel.class);

        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button temp_toSlideshowButton = root.findViewById(R.id.toSlideshowButton);
        temp_toSlideshowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View viewone) {
                NavHostFragment.findNavController(PhotosFragment.this).navigate(R.id.action_nav_photos_to_nav_slideshow);
            }
        });

        //final TextView textView = binding.textSearch;
        //searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}