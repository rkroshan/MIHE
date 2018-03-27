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

import cubes.logic.mihe.Feed.models.EventObject;
import cubes.logic.mihe.Feed.models.ProductObject;
import cubes.logic.mihe.Feed.models.ViewHolders;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/27/2018.
 */

public class Event_explore extends Fragment {

    RecyclerView temp_recyclerview;
    FirebaseRecyclerAdapter<EventObject,ViewHolders.EVENTViewHolder> firebaseRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temp_explore,container,false);
        temp_recyclerview = view.findViewById(R.id.temp_recyclerview);
        temp_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference EventRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.EVENT_POST);
        Log.e("EventRef", "Set");
        EventRef.keepSynced(true);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EventObject, ViewHolders.EVENTViewHolder>(
                EventObject.class, R.layout.events_element_view, ViewHolders.EVENTViewHolder.class, EventRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.EVENTViewHolder viewHolder, EventObject model, int position) {
                Log.e("EventRef viewholder", "working");
                final String link = model.getLINK();
                Glide.with(getContext()).load(model.getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.event_imageview);
                viewHolder.event_name.setText(model.getNAME());
                /*viewHolder.event_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(link));
                        startActivity(i);
                    }
                });*/
                viewHolder.event_time.setText(model.getTIME());
            }
        };

        temp_recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
