package com.example.photos.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.photos.R;
import com.example.photos.databinding.FragmentSlideshowBinding;
import com.example.photos.shared.SharedViewModel;
import com.example.photos.ui.results.ResultsViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                if (newTagName.equals("")) {
                    Context context = getContext();
                    Toast.makeText(context, "Tag entry cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    if (newTagType.equals("Location")) {
                        //writeNewLocationToFile();
                    }
                    else { //Person

                    }
                }
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

        //{URI:pathToImageFile,Location:place,People:[person1,person2],Album:albumName}{URI:pathToImageFile2,Location:place2,People:[person3,person4],Album:albumName2}
        public void writeNewLocationToFile(String file, String path, String newLocation) {
            // Pattern to find the photo data with the specified URI path
            // The regex now accounts for an empty Location field
            String regex = "\\{URI:" + Pattern.quote(path) + ",Location:([^,]*),People:\\[([^\\]]*)\\],Album:([^\\}]+)\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file);

            // Check if the pattern is found
            if (matcher.find()) {
                // Replace the old location (which may be empty) with the new location
                String updatedSegment = matcher.group(0).replaceFirst("Location:[^,]*", "Location:" + newLocation);

                // Replace the old segment with the updated segment in the original data
                file.replace(matcher.group(0), updatedSegment);
            }
        }
}