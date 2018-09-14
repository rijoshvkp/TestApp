package com.test.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.test.testapp.model.DataModel;
import com.test.testapp.R;
import java.util.Collections;
import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyHolder> {

    Context context;
    List<DataModel> dataModels;


    public DataAdapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
        Collections.reverse(dataModels);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_rowitem, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvData.setText(dataModels.get(position).getDataName());
        holder.tvTimeStamp.setText(dataModels.get(position).getDataTimestamp());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvData, tvTimeStamp;

        public MyHolder(View itemView) {

            super(itemView);

            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tv_timestamp);
        }
    }

}

