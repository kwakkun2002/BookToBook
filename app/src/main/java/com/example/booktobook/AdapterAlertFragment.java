package com.example.booktobook;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class AdapterAlertFragment extends RecyclerView.Adapter<AdapterAlertFragment.ViewHolder> {

    private ArrayList<AlertData> alertDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView isBorrowed;
        public TextView title_haver;
        public TextView when_place;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isBorrowed = itemView.findViewById(R.id.item_alert_textView_borrowed);
            title_haver = itemView.findViewById(R.id.item_alert_textView_title_haver);
            when_place = itemView.findViewById(R.id.item_alert_textView_when_place);
        }
    }

    public AdapterAlertFragment(ArrayList<AlertData> dataset){
        alertDataSet = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alert, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.isBorrowed.setText(alertDataSet.get(position).isBorrowed);
        holder.title_haver.setText(
                        "["+alertDataSet.get(position).title+"]을 [" +
                        alertDataSet.get(position).name+"]에게서");

        holder.when_place.setText(
                        alertDataSet.get(position).year+"/"+
                        alertDataSet.get(position).month+"/"+
                        alertDataSet.get(position).day+"  ["+
                        alertDataSet.get(position).place+"]에서");

    }

    @Override
    public int getItemCount() {
        return alertDataSet.size();
    }


}


class AlertData{
    public int year;
    public int month;
    public int day;
    public String isBorrowed;
    public String title;
    public String name;
    public String place;

    public AlertData(String isBorrowed,int year,int month,int day,
                 String title,String name,String place){
        this.isBorrowed = isBorrowed;
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.name = name;
        this.place = place;
    }

}