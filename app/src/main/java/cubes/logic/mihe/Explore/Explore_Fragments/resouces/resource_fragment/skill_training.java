package cubes.logic.mihe.Explore.Explore_Fragments.resouces.resource_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/30/2018.
 */

public class skill_training extends Fragment {

    RecyclerView explore_recyclerview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_frag,container,false);
        explore_recyclerview = view.findViewById(R.id.explore_recyclerview);
        explore_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
