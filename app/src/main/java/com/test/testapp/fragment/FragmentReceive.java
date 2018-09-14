package com.test.testapp.fragment;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.test.testapp.adapter.DataAdapter;
import com.test.testapp.model.DataModel;
import com.test.testapp.R;
import com.test.testapp.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;


public class FragmentReceive extends Fragment {

    AppDatabase appDatabase;
    DataModel dataModel;
    List<DataModel> dataModelArrayList;
    RecyclerView rvData;
    DataAdapter dataAdapter;
    public static int checkData;
    TextView tvNodata;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receive, null);

        rvData = (RecyclerView) view.findViewById(R.id.rv_data);
        tvNodata = (TextView) view.findViewById(R.id.tv_nodata);

//////////////////////Database Instance Creation/////////////////////////////

        appDatabase = Room.databaseBuilder(getActivity(), AppDatabase.class, "data_db")
                .allowMainThreadQueries()
                .build();


/////////////////////////Send Data to Recyclerview Adapter///////////////////////

        List<DataModel> checkArraylist = appDatabase.daoAccess().fetchData();
        if (checkArraylist.size() != 0) {
            tvNodata.setVisibility(View.GONE);

            dataAdapter = new DataAdapter(getActivity(), checkArraylist);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            rvData.setLayoutManager(mLayoutManager);
            rvData.setItemAnimator(new DefaultItemAnimator());
            rvData.setAdapter(dataAdapter);

        } else {

            tvNodata.setVisibility(View.VISIBLE);

        }

    //////////////////////////////////////////////////////////////////////////

        return view;

    }

    ///////////////////////////Receive Broadcast Data///////////////////////////

    private final BroadcastReceiver mYourBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String receiveData = null;
            try {
                receiveData = intent.getStringExtra("Data");
                Toast.makeText(context,"Received :"+receiveData,Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
            String time = intent.getStringExtra("Time");
            Log.e("Check", receiveData);

            dataModel = new DataModel();
            dataModelArrayList = new ArrayList<>();
            dataModel.setDataName(receiveData);
            dataModel.setDataTimestamp(time);
            dataModelArrayList.add(dataModel);
            appDatabase.daoAccess().insertData(dataModelArrayList);

        }
    };

//////////////////////////////////////////////////////////////////////////


    @Override
    public void onResume() {
        super.onResume();
        if (checkData != 1) {

            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mYourBroadcastReceiver,
                    new IntentFilter("BROADCAST_TEXT"));
            Fragment fragment = new FragmentSend();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commit();
        }
        checkData = 1;

    }


}
