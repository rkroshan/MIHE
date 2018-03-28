package cubes.logic.mihe.Explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/21/2018.
 */

public class Explore extends Fragment {


    private RecyclerView explore_recyclerview;
    private DisplayMetrics displayMetrics;
    private int height;
    private static ArrayList<String> mlist = new ArrayList<>();
    static {
        mlist.add("IDEAS");
        mlist.add("PRODUCTS");
        mlist.add("MOTIVATIONS");
        mlist.add("RESOURCES");
        mlist.add("EVENTS");
        mlist.add("SCHEMES");
    }
    GridLayoutManager gridLayoutManager;
    private int width=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_frag,container,false);
        Log.e("Explore ",getActivity().getSupportFragmentManager().getFragments().toString());
       // getScreenSize();
        explore_recyclerview = view.findViewById(R.id.explore_recyclerview);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        explore_recyclerview.setLayoutManager(gridLayoutManager);
        Log.e("mlist----",mlist.get(0));
        explore_recyclerview.setAdapter(new ExploreAdapter(mlist,getContext()));
        return view;
    }

    private void getScreenSize() {
        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        Log.e("mlist----",height+"");
    }


}
