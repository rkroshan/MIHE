package cubes.logic.mihe.Feed;

import android.content.Intent;
import android.net.Uri;
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
import cubes.logic.mihe.Feed.models.MotivationObject;
import cubes.logic.mihe.Feed.models.ProductObject;
import cubes.logic.mihe.Feed.models.SchemeObject;
import cubes.logic.mihe.Feed.models.ViewHolders;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/21/2018.
 */

public class Feed extends Fragment {

    RecyclerView motivation_recyclerview, product_recyclerciew, events_recyclerview, schemes_recyclerview;
    private FirebaseRecyclerAdapter<MotivationObject, ViewHolders.MOTIVATIONViewHolder> MotivationAdapter;
    private FirebaseRecyclerAdapter<ProductObject, ViewHolders.PRODUCTViewHolder> ProductAdapter;
    private FirebaseRecyclerAdapter<EventObject, ViewHolders.EVENTViewHolder> EventAdapter;
    private FirebaseRecyclerAdapter<SchemeObject, ViewHolders.SCHEMESViewHolder> SchemeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(Feed.class.toString(), "on create");
        View view = inflater.inflate(R.layout.feed_frag, container, false);

        motivation_recyclerview = view.findViewById(R.id.motivation_recyclerview);
        product_recyclerciew = view.findViewById(R.id.product_recyclerciew);
        events_recyclerview = view.findViewById(R.id.events_recyclerview);
        schemes_recyclerview = view.findViewById(R.id.schemes_recyclerview);

        AdaptersInit();

        motivation_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        product_recyclerciew.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        events_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        schemes_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(Feed.class.toString(), "on start");
        motivation_recyclerview.setAdapter(MotivationAdapter);
        product_recyclerciew.setAdapter(ProductAdapter);
        events_recyclerview.setAdapter(EventAdapter);
        schemes_recyclerview.setAdapter(SchemeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(Feed.class.toString(), "on resume");
        // main_feed_recyclerview.setAdapter(new MainFeedAdapter(getContext()));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(Feed.class.toString(), "on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(Feed.class.toString(), "on stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(Feed.class.toString(), "on destroy");
    }

    private void AdaptersInit() {
        Log.e("MainFeedAdapter---", "AdapterInit");
        DatabaseReference MotivRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.Motivation_Post);
        Log.e("MotivRef", "Set");
        MotivRef.keepSynced(true);
        MotivationAdapter = new FirebaseRecyclerAdapter<MotivationObject, ViewHolders.MOTIVATIONViewHolder>(
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
                viewHolder.setLink(model.getLINK(),getContext());
            }
        };

        DatabaseReference ProductRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.PRODUCT_POST);
        Log.e("ProductRef", "Set");
        ProductRef.keepSynced(true);
        ProductAdapter = new FirebaseRecyclerAdapter<ProductObject, ViewHolders.PRODUCTViewHolder>(
                ProductObject.class, R.layout.products_element_view, ViewHolders.PRODUCTViewHolder.class, ProductRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.PRODUCTViewHolder viewHolder, ProductObject model, int position) {
                Log.e("ProductRef viewholder", "working");
                Glide.with(getContext()).load(model.getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.product_imageview);
                viewHolder.setCategory(model.getCATEGORY(),getContext());
                viewHolder.product_description.setText(model.getDESCRIPTION());
                viewHolder.product_name.setText(model.getNAME());
                viewHolder.product_makers.setText(model.getMAKERS());
                viewHolder.setLink(model.getLINK(),getContext());
            }
        };

        DatabaseReference EventRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.EVENT_POST);
        Log.e("EventRef", "Set");
        EventRef.keepSynced(true);
        EventAdapter = new FirebaseRecyclerAdapter<EventObject, ViewHolders.EVENTViewHolder>(
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
                viewHolder.setLink(model.getLINK(),getContext());
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

        DatabaseReference SchemeRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.SCHEME_POST);
        Log.e("SchemeRef", "Set");
        SchemeRef.keepSynced(true);
        SchemeAdapter = new FirebaseRecyclerAdapter<SchemeObject, ViewHolders.SCHEMESViewHolder>(
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

    }
}
