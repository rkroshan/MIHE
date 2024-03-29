package cubes.logic.mihe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import cubes.logic.mihe.Feed.Feed;
import cubes.logic.mihe.Services.FCMNotificationService;
import cubes.logic.mihe.TemporaryActivities.Website_post;
import cubes.logic.mihe.TemporaryActivities.events_post;
import cubes.logic.mihe.TemporaryActivities.ideas_post;
import cubes.logic.mihe.TemporaryActivities.motivation_post;
import cubes.logic.mihe.TemporaryActivities.product_post;
import cubes.logic.mihe.TemporaryActivities.resources_post;
import cubes.logic.mihe.TemporaryActivities.schemes_post;

public class MainActivity extends AppCompatActivity {


    private AHBottomNavigationViewPager viewPager;
    private MainViewPagerAdapter adapter;
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.temp_menu,menu);
        return true;
    }

    public void setAdapterPage(int pos,Bundle args) {
        bottomNavigation.setCurrentItem(pos);
        bottomNavigation.restoreBottomNavigation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.motivation_post:
                startActivity(new Intent(getApplicationContext(),motivation_post.class));
                break;
            case R.id.product_post:
                startActivity(new Intent(getApplicationContext(),product_post.class));
                break;
            case R.id.events_post:
                startActivity(new Intent(getApplicationContext(),events_post.class));
                break;
            case R.id.schemes_post:
                startActivity(new Intent(getApplicationContext(),schemes_post.class));
                break;
            case R.id.resources_post:
                startActivity(new Intent(getApplicationContext(),resources_post.class));
                break;
            case R.id.ideas_post:
                startActivity(new Intent(getApplicationContext(),ideas_post.class));
                break;
            case R.id.website_post:
                startActivity(new Intent(getApplicationContext(), Website_post.class));
                break;
            default:
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        String id=sharedPreferences.getString("id",null);
        FirebaseMessaging.getInstance().subscribeToTopic("notifications");

//        if(id==null) {
//            startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
//            finish();
//        }
//        else
            setupBottomNav();
    }


    private void setupBottomNav() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);

        viewPager = findViewById(R.id.view_pager);


        viewPager.setOffscreenPageLimit(4);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.feed, R.drawable.feed_icon, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.network, R.drawable.network_icon, R.color.extra_color);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.assistant, R.drawable.ic_woman_with_headset, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.dashboard, R.drawable.dashboard, R.color.colorPrimaryDark);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.resources, R.drawable.ic_resources, R.color.colorPrimaryDark);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setAccentColor(Color.rgb(100,250,250));
        bottomNavigation.setColored(true);
        bottomNavigation.addItems(bottomNavigationItems);


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position,false);
                return true;
            }
        });

        bottomNavigation.setTranslucentNavigationEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(MainActivity.class.toString(),"on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(MainActivity.class.toString(),"on resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(MainActivity.class.toString(),"on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(MainActivity.class.toString(),"on stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(MainActivity.class.toString(),"on destroy");
    }
}
