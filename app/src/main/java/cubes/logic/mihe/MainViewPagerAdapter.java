package cubes.logic.mihe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import cubes.logic.mihe.Assistant.assistant_base;
import cubes.logic.mihe.Explore.explore_base;
import cubes.logic.mihe.Feed.Feed;

/**
 * Created by cogito on 3/20/18.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(new Feed());
        fragments.add(new NetworkFragment());
        fragments.add(new assistant_base());
        fragments.add(new DashboardFragment());
        fragments.add(new explore_base());
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("fragments.get(position)",fragments.get(position)+"");
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
