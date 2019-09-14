package com.example.booktobook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editText_id;
    private EditText editText_password;
    private Button button_login;
    private Button button_signUp;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        editText_id = findViewById(R.id.editText_id);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);
        button_signUp = findViewById(R.id.button_signUp);


        //회원가입 버튼 클릭시
        //
        //만약 동일한 아이디가 있다면?
        //      회원가입 X 메세지
        //아니면
        //      아이디-패스워드로 해서 파베에 저장
        //      //일단 지금은 회원가입하고 다시 로그인하도록 하기로
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String id = editText_id.getText().toString();
                final String password = editText_password.getText().toString();

                DocumentReference documentReference = db.collection("Users").document(id);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if(documentSnapshot.exists()){//해당 도큐먼트가 이미 존재한다 => 아이디가 있다 => 새로 추가할 수가 없음
                                Log.d("LoginActivity-signUp", "already exist : "+id);
                                Toast.makeText(LoginActivity.this, "이미 존재하는 아이디입니다!", Toast.LENGTH_SHORT).show();
                            }else{//해당 도큐먼트가 존재하지 않는다 => 아이디가 없다 => 새로 추가
                                User user = new User(id,password);
                                db.collection("Users").document(id).set(user);
                                Log.d("LoginActivity-signUp", "new User made : "+user.getId());
                                Toast.makeText(LoginActivity.this, user.getId()+"님 반갑습니다!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Log.d("LoginActivity-signUp", "get failed with ", task.getException());
                        }
                    }
                });
            }
        });


        //로그인 버튼 클릭시
        //
        //만약 아이디나 패스워드 중 하나가 틀리면?
        //      다시 시도 메시지
        //아니면
        //      통과-> 메인화면으로 이동
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = editText_id.getText().toString();
                final String password = editText_password.getText().toString();

                DocumentReference documentReference = db.collection("Users").document(id);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if(documentSnapshot.exists()){//아이디가 있음
                                User user= documentSnapshot.toObject(User.class);
                                if(password.equals(user.getPassword())){//패스워드 같음
                                    Log.d("LoginActivity-login", "login success!");
                                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                    //sharedPreference에 아이디 저장
                                    SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("ID",id.toString());
                                    editor.apply();
                                    //메인화면 열기
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                                else{//패스워드 다름
                                    Log.d("LoginActivity-login", "wrong password!");
                                    Toast.makeText(LoginActivity.this, "패스워드가 잘못되었습니다!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{//아이디가 없음
                                Log.d("LoginActivity-login", "no id!");
                                Toast.makeText(LoginActivity.this, "아이디가 잘못되었습니다!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Log.d("LoginActivity-login", "get failed with ", task.getException());
                        }
                    }
                });
            }
        });



    }
}

