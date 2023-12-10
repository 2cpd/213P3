package com.example.photos.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.photos.R;
import com.example.photos.databinding.FragmentSlideshowBinding;
import com.example.photos.shared.SharedViewModel;
import com.example.photos.ui.results.ResultsViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

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

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner newTypeSpinner = (Spinner) root.findViewById(R.id.newTagSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> newTypeAdapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.tagChoiceArray,
                android.R.layout.simple_spinner_item
        );
        newTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newTypeSpinner.setAdapter(newTypeAdapter);

        //@tools:sample/backgrounds/scenic[0]

        ImageView displayedImage = (ImageView) root.findViewById(R.id.imageSlideshow);
        displayedImage.setImageResource(R.drawable.ic_menu_gallery); //test image; TO BE DELETED
        //displayedImage.setImageURI();

        Button prevButton = (Button) root.findViewById(R.id.prevPhotoButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPrevPhoto();
            }
        });

        Button nextButton = (Button) root.findViewById(R.id.nextPhotoButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextPhoto();
            }
        });

        Button addTagButton = (Button) root.findViewById(R.id.addTagButton);
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText newTagTextInput = (TextInputEditText) root.findViewById(R.id.newTagTextInput);
                Spinner newTagTypeSpinner = (Spinner) root.findViewById(R.id.newTagSpinner);

                String newTagName = newTagTextInput.getText().toString();
                String newTagType = newTagTypeSpinner.getSelectedItem().toString();

                addTag(newTagName, newTagType);
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

    public void toNextPhoto() {
        //TODO: Fill in method
    }

    public void toPrevPhoto() {
        //TODO: Fill in method
    }

    public void addTag(String name, String type) {
        if (name.equals("")) {
            //error
            return;
        }
        if (type.equals("Location")) {
            //thisPhoto.setLocation(name);
            //TODO: implement way to determine properties of this photo
        }
        else { //person
            //thisPhoto.addPerson(name);
        }
    }
}