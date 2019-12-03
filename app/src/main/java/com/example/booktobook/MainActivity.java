package com.example.booktobook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private EnrollFragment enrollFragment = new EnrollFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ShowFragment showFragment = new ShowFragment();
    private AlertFragment alertFragment = new AlertFragment();
    private ShelfFragment shelfFragment = new ShelfFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        //첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,showFragment).commitAllowingStateLoss();

        //bottomNavigationView 의 아이템이 선택될 떄 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.navigation_enroll:{
                        transaction.replace(R.id.frame_layout,enrollFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_search:{
                        transaction.replace(R.id.frame_layout,searchFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_show:{
                        transaction.replace(R.id.frame_layout,showFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_alert:{
                        transaction.replace(R.id.frame_layout,alertFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.navigation_shelf:{
                        transaction.replace(R.id.frame_layout,shelfFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
