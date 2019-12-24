package com.example.booktobook.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.booktobook.Fragment.EnrollFragment;
import com.example.booktobook.Fragment.ProfileFragment;
import com.example.booktobook.Model.Alert;
import com.example.booktobook.R;
import com.example.booktobook.Fragment.SearchFragment;
import com.example.booktobook.Fragment.ShelfFragment;
import com.example.booktobook.Fragment.ShowFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private EnrollFragment enrollFragment = new EnrollFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ShowFragment showFragment = new ShowFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private ShelfFragment shelfFragment = new ShelfFragment();
    Boolean flag;
    private Context context = this;

    private Intent serviceIntent;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
        final String ID = preferences.getString("ID", "");
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Users")
                .document(ID)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                flag = documentSnapshot.getBoolean("flag");
                if (flag) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("BookToBook");
                    builder.setMessage("책을 교환하였습니까?");
                    builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            flag = false;


                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            final String[] receiver = new String[1];
                            final String[] location = new String[1];
                            final String[] time = new String[1];
                            final String[]  room_id = new String[1];
                            final String[] book_name = new String[1];

                            //get location,time,receiver,room_id
                            db.collection("meet")
                                    .whereEqualTo("sender",""+ID)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {

                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Log.d("get_data_meet", document.getId() + " => " + document.getData());
                                                    receiver[0] = document.get("receiver").toString();
                                                    location[0] = document.get("location").toString();
                                                    time[0] = document.get("time").toString();
                                                    room_id[0] = document.get("room_id").toString();
                                                    Log.d("get_data_meet_time",time[0]);
                                                }









                                                //get name from room_id
                                                db.collection("room")
                                                        .document(""+room_id[0])
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                if (task.isSuccessful()) {
                                                                    DocumentSnapshot document = task.getResult();
                                                                    if (document.exists()) {
                                                                        Log.d("get_name_from_room_id", "DocumentSnapshot data: " + document.getData());
                                                                        book_name[0] = document.get("name").toString();
                                                                        book_name[0] = book_name[0].substring(book_name[0].lastIndexOf("/")+1);







                                                                        //get book and update master
                                                                        db.collection("Books")
                                                                                .whereEqualTo("title",""+book_name[0])
                                                                                .get()
                                                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                                Log.d("get book and change mas", document.getId() + " => " + document.getData());

                                                                                                db.collection("Books")
                                                                                                        .document(""+document.getId())
                                                                                                        .update("haver",receiver[0]);
                                                                                            }






                                                                                            //add point
                                                                                            db.collection("Users")
                                                                                                    .document(""+receiver[0])
                                                                                                    .update("borrowed_book_count", FieldValue.increment(1)
                                                                                                            ,"point",FieldValue.increment(-1));



                                                                                            //add alert
                                                                                            //양 계정에 알림을 추가
                                                                                            DocumentReference documentReference = db.collection("Users")
                                                                                                    .document(receiver[0]);
                                                                                            documentReference.update("alert", FieldValue.arrayUnion(
                                                                                                    new Alert(
                                                                                                            location[0],
                                                                                                            time[0],
                                                                                                            "빌려줌",
                                                                                                            book_name[0],
                                                                                                            ID
                                                                                                    )
                                                                                            ));

                                                                                            documentReference = db.collection("Users")
                                                                                                    .document(ID);
                                                                                            documentReference.update("alert", FieldValue.arrayUnion(
                                                                                                    new Alert(
                                                                                                            location[0],
                                                                                                            time[0],
                                                                                                            "빌림",
                                                                                                            book_name[0],
                                                                                                            receiver[0]
                                                                                                    )
                                                                                            ));









                                                                                        } else {
                                                                                            Log.d("get book and change mas", "Error getting documents: ", task.getException());
                                                                                        }
                                                                                    }
                                                                                });

















                                                                    } else {
                                                                        Log.d("get_name_from_room_id", "No such document");
                                                                    }
                                                                } else {
                                                                    Log.d("get_name_from_room_id", "get failed with ", task.getException());
                                                                }
                                                            }
                                                        });















                                            } else {
                                                Log.d("get_receiver", "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });












                        }
                    });
                    builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            flag = false;


                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        //첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, showFragment).commitAllowingStateLoss();

        //bottomNavigationView 의 아이템이 선택될 떄 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_enroll: {
                        transaction.replace(R.id.frame_layout, enrollFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_search: {
                        transaction.replace(R.id.frame_layout, searchFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_show: {
                        transaction.replace(R.id.frame_layout, showFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_profile: {
                        transaction.replace(R.id.frame_layout, profileFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_shelf: {
                        transaction.replace(R.id.frame_layout, shelfFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {

                String newToken = instanceIdResult.getToken();
                Log.d("qwe", "새 토큰" + newToken);

                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
                final String ID = preferences.getString("ID", "");
                Log.d("id", ID);
                DocumentReference documentReference = db.collection("Users")
                        .document(ID);
                documentReference.update("token", newToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("dd", "Sucessful update");
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }


}
