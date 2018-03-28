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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cubes.logic.mihe.Feed.models.EventObject;
import cubes.logic.mihe.Feed.models.SchemeObject;
import cubes.logic.mihe.Feed.models.ViewHolders;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/27/2018.
 */

public class Scheme_explore extends Fragment {

    RecyclerView temp_recyclerview;
    FirebaseRecyclerAdapter<SchemeObject,ViewHolders.SCHEMESViewHolder> firebaseRecyclerAdapter;

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

        DatabaseReference SchemeRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.SCHEME_POST);
        Log.e("SchemeRef", "Set");
        SchemeRef.keepSynced(true);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SchemeObject, ViewHolders.SCHEMESViewHolder>(
                SchemeObject.class, R.layout.schemes_element_view, ViewHolders.SCHEMESViewHolder.class, SchemeRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.SCHEMESViewHolder viewHolder, SchemeObject model, int position) {
                Log.e("SchemeRef viewholder", "working");
                Glide.with(getContext()).load(model.getIMAGE()).placeholder(R.mipmap.ic_launcher).into(viewHolder.scheme_image);
                viewHolder.setLink(model.getLINK(),getContext());
               /* viewHolder.event_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(link));
                        startActivity(i);
                    }
                });*/
            }
        };

        temp_recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
