package com.test.testapp.fragment;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.test.testapp.R;
import com.test.testapp.room.AppDatabase;
import java.util.Calendar;

public class FragmentSend extends Fragment {

    FloatingActionButton floatingActionButton;
    EditText etData;
    AppDatabase appDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_send,null);

        etData=(EditText)view.findViewById(R.id.et_data) ;
        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.fb_button);

        ////////////////////Database Instance Creation/////////////////////////////////

        appDatabase = Room.databaseBuilder(getActivity(), AppDatabase.class, "data_db")
                .allowMainThreadQueries()
                .build();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String datas=etData.getText().toString();
                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
                Intent localIntent = new Intent("BROADCAST_TEXT");
                localIntent.putExtra("Data",datas);
                localIntent.putExtra("Time",mydate);
                localBroadcastManager.sendBroadcast(localIntent);


            }
        });

        return view;

    }


}
