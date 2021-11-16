package com.example.appmucsic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.appmucsic.Adapter.MainViewPagerAdapter;
import com.example.appmucsic.Fragment.Fragment_Ca_Nhan;
import com.example.appmucsic.Fragment.Fragment_Thu_Vien;
import com.example.appmucsic.Fragment.Fragment_Tim_Kiem;
import com.example.appmucsic.Fragment.Fragment_Trang_Chu;
import com.example.appmucsic.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
   ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     anhxa();
        init();
    }
    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Thu_Vien(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Ca_Nhan(),"");
          viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.search);
        tabLayout.getTabAt(2).setIcon(R.drawable.thuvien2);
        tabLayout.getTabAt(3).setIcon(R.drawable.personal);
    }
    private void anhxa(){
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager=findViewById(R.id.myViewPager);
    }
}