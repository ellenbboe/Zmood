package com.example.s.zmood;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment = new Fragment();
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment mainFragment, mainFragment1,mainFragment2,mainFragment3,mainFragment4,mainFragment5;

    @Override//显示menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "you click add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "you click setting", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WelcomeActivity.instance.finish();//关掉欢迎页

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Toolbar toolbar = findViewById(R.id.activitymaintoolbar);
        setSupportActionBar(toolbar);
        mainFragment = new MainFragment();

        drawerLayout = findViewById(R.id.activitymaindrawerlayout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        fragmentTransaction.replace(R.id.fragmentmain, mainFragment);
        fragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.acticle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.acticle:
                        if (mainFragment == null) {
                            mainFragment = new MainFragment();
                        }
                        showFragment(mainFragment);
                        break;
                    case R.id.acticle2:
                        if (mainFragment1 == null) {
                            mainFragment1 = new MainSecondFragment();
                        }
                        showFragment(mainFragment1);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });


    }

    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                fragmentTransaction.replace(R.id.fragmentmain, fragment).show(fragment).commit();
            } else {
                fragmentTransaction.show(fragment).commit();
            }
        }
    }
    // TODO: 18-12-25 这里的本地是随机的 每次show一个页面就会随机数据
}
