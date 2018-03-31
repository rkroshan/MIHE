package cubes.logic.mihe.Explore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
 * Created by CREATOR on 3/30/2018.
 */

public class Websites extends Fragment {
    RecyclerView explore_recyclerview;
    FirebaseRecyclerAdapter<WebsiteModel,ViewHolders.WebsiteViewHolder> firebaseRecyclerAdapter;
    private int data;

    public Websites(){

    }
    @SuppressLint("ValidFragment")
    public Websites(int i) {
        data = i;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_frag,container,false);
        explore_recyclerview = view.findViewById(R.id.explore_recyclerview);
        explore_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.WEBSITES).child(data+"");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WebsiteModel, ViewHolders.WebsiteViewHolder>(WebsiteModel.class
                ,R.layout.website_element_view, ViewHolders.WebsiteViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(ViewHolders.WebsiteViewHolder viewHolder, WebsiteModel model, int position) {
                viewHolder.setWebsite_imageview(model.getIMAGE(),getContext());
                viewHolder.setWebsite_description(model.getDESCRIPTION());
                viewHolder.setWebsite_title(model.getNAME());
                viewHolder.setLink(model.getLINK(),getContext());
            }
        };

        explore_recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
