package cubes.logic.mihe;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private AHBottomNavigationViewPager viewPager;
    private MainViewPagerAdapter adapter;
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);

        viewPager = findViewById(R.id.view_pager);


        viewPager.setOffscreenPageLimit(4);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.feed, R.drawable.ic_settings_white_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.network, R.drawable.ic_settings_white_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.assistant, R.drawable.ic_settings_white_24dp, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.dashboard, R.drawable.ic_settings_white_24dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.settings, R.drawable.ic_settings_white_24dp, R.color.colorPrimaryDark);

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
}
