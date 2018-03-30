package cubes.logic.mihe.Explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cubes.logic.mihe.Explore.Explore_Fragments.ideas.ideas;
/*import cubes.logic.mihe.Explore.Explore_Fragments.resouces.resource_fragment.business_plan;
import cubes.logic.mihe.Explore.Explore_Fragments.resouces.resource_fragment.funding;
import cubes.logic.mihe.Explore.Explore_Fragments.resouces.resource_fragment.legal_foundation;
import cubes.logic.mihe.Explore.Explore_Fragments.resouces.resource_fragment.market_exploration;*/
import cubes.logic.mihe.Explore.Explore_Fragments.resouces.Resources;
import cubes.logic.mihe.Explore.Explore_Fragments.resouces.resource_fragment.skill_training;
import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/30/2018.
 */

public class ResourceActivities extends AppCompatActivity {


    ViewPager resource_activity_viewpager;
    private int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_activity);
        i = getIntent().getIntExtra("data", 0);
        Log.e("data",i+"");
        resource_activity_viewpager = findViewById(R.id.resource_activity_viewpager);
        setupViewPager(resource_activity_viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        switch (i) {
            case 1:

                adapter.addFragment(new Resources(i), "e_books");
                adapter.addFragment(new Courses(i), "Courses");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                adapter.addFragment(new Resources(i), "e_books");
                adapter.addFragment(new Websites(i), "Websites");
                break;
        }
        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
