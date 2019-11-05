package com.example.booktobook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("SetTextI18n")
public class AdapterAlertFragment extends RecyclerView.Adapter<AdapterAlertFragment.ViewHolder> {

    private ArrayList<Alert> alertDataSet;
    private static String id;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView status;
        public TextView title_haver;
        public TextView when_place;
        public ImageButton alert_button;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.item_alert_textView_borrowed);
            title_haver = itemView.findViewById(R.id.item_alert_textView_title_haver);
            when_place = itemView.findViewById(R.id.item_alert_textView_when_place);
            alert_button = itemView.findViewById(R.id.item_alert_button);


            alert_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //확인을 눌렀으니 리사이클러뷰에서 알림을 삭제하고 1.
                    //서버에서의 알림도 삭제 2.
                    //  카운트를 1 증가시키고 2-1.
                    //abled_count가 2이면:
                    //  그 카운트를 0으로 만들고 3.
                    //  그 책을 현주인책장에서 삭제 4.
                    //  그 책을 새로운주인책장에 추가 5.


                    //1.
                    alertDataSet.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),alertDataSet.size());

                    //2.
                    final FirebaseFirestore db = FirebaseFirestore.getInstance();
                    final DocumentReference alertDocumentReference = db.collection("Users")
                            .document(id);
                    alertDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Alert alert = documentSnapshot.toObject(Alert.class);
                            alertDocumentReference.update("alert",FieldValue.arrayRemove(alert));
                        }
                    });

                    
                    //2-1
                    alertDocumentReference.update("able_count",FieldValue.increment(1));















                }
            });
        }
    }


    public AdapterAlertFragment(ArrayList<Alert> dataset,String id){
        alertDataSet = dataset;
        AdapterAlertFragment.id = id;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.status.setText(alertDataSet.get(position).getStatus().toString());
        holder.title_haver.setText(
                        "["+alertDataSet.get(position).getBook_title().toString()+"]을 [" +
                        alertDataSet.get(position).getWho().toString()+"]에게서");

        holder.when_place.setText(
                        alertDataSet.get(position).getTime()+" "+alertDataSet.get(position).getPlace()+"]에서");

    }

    @Override
    public int getItemCount() {
        return alertDataSet.size();
    }


}


