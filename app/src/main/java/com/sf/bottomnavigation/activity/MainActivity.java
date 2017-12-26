package com.sf.bottomnavigation.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sf.bottomnavigation.helper.BottomNavigationBehavior;
import com.sf.bottomnavigation.fragment.CardFragment;
import com.sf.bottomnavigation.fragment.GiftsFragment;
import com.sf.bottomnavigation.fragment.ProfileFragment;
import com.sf.bottomnavigation.R;
import com.sf.bottomnavigation.fragment.StoreFragment;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        toolbar.setTitle("Shop Page");
        loadFragment(new StoreFragment());
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle("Shop Page");
                    loadFragment(new StoreFragment());
                    return true;
                case R.id.navigation_gifts:
                    toolbar.setTitle("My Gifts Page");
                    loadFragment(new GiftsFragment());
                    return true;
                case R.id.navigation_cart:
                    toolbar.setTitle("Cart Page");
                    loadFragment(new CardFragment());
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile Page");
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

}
