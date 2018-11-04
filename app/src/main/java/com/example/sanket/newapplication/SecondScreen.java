package com.example.sanket.newapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SecondScreen extends FragmentActivity {


    private Button fragmentToggleButton;

    private enum CurrentFragment{
        CURRENT_FRAGMENT_ALPHA,CURRENT_FRAGMENT_LIST;

        public static CurrentFragment value = CURRENT_FRAGMENT_LIST;

        public Fragment fragmentForCurrentState() {
            switch (CurrentFragment.value){
                case CURRENT_FRAGMENT_ALPHA:
                    CurrentFragment.value = CURRENT_FRAGMENT_LIST;
                    return new SecondScreenAlphaFragment();
                case CURRENT_FRAGMENT_LIST:
                    CurrentFragment.value = CURRENT_FRAGMENT_ALPHA;
                    return new SecondScreenListFragment();
                default:
                    return null;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second_screen);


        //setup change fragment button
        setupChangeFragmentButton();

        // Setup firebase and send data
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.NAME_MESSAGE);
        String password = intent.getStringExtra(MainActivity.PASSWORD_MESSAGE);
        setupFireBaseDatabase(name, password);

        try {
            readData(name);
        } catch (Exception e) {
         // No-op
        }
    }


    private void setupChangeFragmentButton() {
        fragmentToggleButton = findViewById(R.id.changeFragmentButton);
        fragmentToggleButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        toggleFragment(CurrentFragment.value);
                    }
                }
        );
    }

    private void toggleFragment(CurrentFragment currentFragment) {
        if (currentFragment == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.secondScreenFragmentContainer, currentFragment.fragmentForCurrentState());
        fragmentTransaction.commit();
    }

    private void setupFireBaseDatabase(String name, String password) {
        if (name.isEmpty()) {
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("details"+ "-" + name);
        dbRef.child("name").setValue(name);
        dbRef.child("password").setValue(password);
    }

    private void readData(String name) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("details"+ "-" + name);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
