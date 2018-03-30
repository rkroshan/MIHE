package cubes.logic.mihe.Explore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
 * Created by CREATOR on 3/30/2018.
 */

public class Courses extends Fragment {

    RecyclerView explore_recyclerview;
    FirebaseRecyclerAdapter<MotivationObject,ViewHolders.MOTIVATIONViewHolder> firebaseRecyclerAdapter;
    private int data;

    public Courses(){

    }
    @SuppressLint("ValidFragment")
    public Courses(int i) {
        data = i;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_frag,container,false);
        explore_recyclerview = view.findViewById(R.id.explore_recyclerview);
        explore_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.COURSES).child(data+"");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MotivationObject, ViewHolders.MOTIVATIONViewHolder>(MotivationObject.class
        ,R.layout.motivations_element_view, ViewHolders.MOTIVATIONViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(ViewHolders.MOTIVATIONViewHolder viewHolder, MotivationObject model, int position) {
                Log.e("MotivRef viewholder", "working");
                Glide.with(getContext()).load(model.getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.motivation_imageview);
                viewHolder.motivation_textview.setText(model.getNAME());
                viewHolder.setLink(model.getLINK(),getContext());
            }
        };

        explore_recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
