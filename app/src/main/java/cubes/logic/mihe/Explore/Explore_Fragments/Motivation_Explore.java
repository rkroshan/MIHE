package cubes.logic.mihe.Explore.Explore_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cubes.logic.mihe.Feed.models.MotivationObject;
import cubes.logic.mihe.Feed.models.ViewHolders;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/27/2018.
 */

public class Motivation_Explore extends Fragment {

    RecyclerView temp_recyclerview;
    FirebaseRecyclerAdapter<MotivationObject,ViewHolders.MOTIVATIONViewHolder> firebaseRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temp_explore,container,false);
        temp_recyclerview = view.findViewById(R.id.temp_recyclerview);
        temp_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("MainFeedAdapter---", "AdapterInit");
        DatabaseReference MotivRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.Motivation_Post);
        Log.e("MotivRef", "Set");
        MotivRef.keepSynced(true);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MotivationObject, ViewHolders.MOTIVATIONViewHolder>(
                MotivationObject.class, R.layout.motivations_element_view, ViewHolders.MOTIVATIONViewHolder.class, MotivRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.MOTIVATIONViewHolder viewHolder, MotivationObject model, int position) {
                Log.e("MotivRef viewholder", "working");
                Glide.with(getContext()).load(model.getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.motivation_imageview);
                viewHolder.motivation_textview.setText(model.getNAME());
            }
        };

        temp_recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
