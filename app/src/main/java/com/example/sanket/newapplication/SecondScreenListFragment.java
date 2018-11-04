package com.example.sanket.newapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondScreenListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Button fragmentToggleButton = getActivity().findViewById(R.id.changeFragmentButton);
        fragmentToggleButton.setText("View Blue");
        return inflater.inflate(
                R.layout.second_screen_list_fragment,
                container,
                false);
    }
}
