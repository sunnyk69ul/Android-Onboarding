package com.example.sanket.newapplication;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class SecondScreenListFragment extends Fragment {

    public interface ListItemInteractionInterface {
        void profileListItemClicked(String name, Integer imageID);
    }

    String[] namesArray = {
            "Bhau",
            "Tai",
            "Anna",
            "Bhau",
            "Tai",
            "Anna",
            "Bhau",
            "Tai",
            "Anna",
            "Bhau",
            "Tai",
            "Anna"
    };

    Integer[] imageIDArray = {
            R.drawable.boy0,
            R.drawable.girl0,
            R.drawable.man0,
            R.drawable.boy0,
            R.drawable.girl0,
            R.drawable.man0,
            R.drawable.boy0,
            R.drawable.girl0,
            R.drawable.man0,
            R.drawable.boy0,
            R.drawable.girl0,
            R.drawable.man0,
    };

    ListView listView;
    ListItemInteractionInterface itemInteractionInterface;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Button fragmentToggleButton = getActivity().findViewById(R.id.changeFragmentButton);
        fragmentToggleButton.setText("View Blue");

        CustomListAdaptor customListAdaptor = new CustomListAdaptor(
                getActivity(),
                namesArray,
                imageIDArray);
        View rootView = inflater.inflate(
                R.layout.second_screen_list_fragment,
                container,
                false);
        listView = rootView.findViewById(R.id.contentListView);
        if (listView != null) {
            listView.setAdapter(customListAdaptor);
            listView.setOnItemClickListener(mOnClickListener);
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            itemInteractionInterface = (ListItemInteractionInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (itemInteractionInterface == null) {
                return;
            }
            itemInteractionInterface.profileListItemClicked(namesArray[position], imageIDArray[position]);
        }
    };
}
