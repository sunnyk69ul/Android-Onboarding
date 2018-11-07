package com.example.sanket.newapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SecondScreenAlphaFragment extends Fragment {

    ImageView profileIconImageView;
    Integer iconImageID;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.second_screen_alpha_fragment,
                container,
                false);
        profileIconImageView = rootView.findViewById(R.id.profileImageView);
        
        Button fragmentToggleButton = getActivity().findViewById(R.id.changeFragmentButton);
        fragmentToggleButton.setText("View List");
        return rootView;
    }
}
