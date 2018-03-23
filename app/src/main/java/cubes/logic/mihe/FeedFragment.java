package cubes.logic.mihe;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cubes.logic.mihe.Feed.Explore.Explore;
import cubes.logic.mihe.Feed.Feed;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    public FeedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(FeedFragment.class.toString(),"on create");
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ViewPager feed_viewPager = view.findViewById(R.id.feed_viewPager);
        setupViewPager(feed_viewPager);
        TabLayout feed_tabs = view.findViewById(R.id.feed_tabs);
        feed_tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        feed_tabs.setupWithViewPager(feed_viewPager);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(FeedFragment.class.toString(),"on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(FeedFragment.class.toString(),"on resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(FeedFragment.class.toString(),"on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(FeedFragment.class.toString(),"on stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(FeedFragment.class.toString(),"on destroy");
    }
    private void setupViewPager(ViewPager viewPager) {


        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new Feed(), "Feed");
        adapter.addFragment(new Explore(), "Explore");
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
