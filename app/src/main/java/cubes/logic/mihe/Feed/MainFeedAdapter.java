/*
package cubes.logic.mihe.Feed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cubes.logic.mihe.Feed.models.EventObject;
import cubes.logic.mihe.Feed.models.MotivationObject;
import cubes.logic.mihe.Feed.models.ProductObject;
import cubes.logic.mihe.Feed.models.SchemeObject;
import cubes.logic.mihe.Feed.models.ViewHolders;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

*/
/**
 * Created by CREATOR on 3/22/2018.
 *//*


class MainFeedAdapter extends RecyclerView.Adapter<MainFeedAdapter.ViewHolder> {

    private Context mcontext;
    private int NoOfElements = 4;
    private ArrayList<MotivationObject> motivationObjects = new ArrayList<>();
    private ArrayList<ProductObject> productObjects = new ArrayList<>();
    private ArrayList<EventObject> eventObjects = new ArrayList<>();
    private ArrayList<SchemeObject> schemeObjects = new ArrayList<>();
    private boolean datacollected = false;


    public MainFeedAdapter(Context context) {
        // AdaptersInit();
        // CheckingData();
        mcontext = context;
    }

    */
/*private void CheckingData() {
        DatabaseReference MotivRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.Motivation_Post);
        MotivRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("MotivRef","onDataChange");
                for(DataSnapshot datasnap: dataSnapshot.getChildren()){
                    Log.e("datasnap",datasnap.child(StringVariables.NAME).getValue()+"");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*//*


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("onCreateViewHolder main","Set");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_feed_recylcer_elementview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinearLayoutManager nestedlinearLayoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false);
        holder.nested_recyclerview_main_feed.setLayoutManager(nestedlinearLayoutManager);

        switch (position) {
            case 0:
                holder.nested_recyclerview_main_feed.setAdapter(new NestedAdapter(mcontext, 0, motivationObjects,productObjects,eventObjects,schemeObjects));
                Log.e("MotivationAdapter", "Set");
                break;
            case 2:
                holder.nested_recyclerview_main_feed.setAdapter(new NestedAdapter(mcontext, 1, motivationObjects,productObjects,eventObjects,schemeObjects));
                Log.e("EventAdapter", "Set");
                break;
            case 1:
                holder.nested_recyclerview_main_feed.setAdapter(new NestedAdapter(mcontext, 2, motivationObjects,productObjects,eventObjects,schemeObjects));
                Log.e("ProductAdapter", "Set");
                break;
            case 3:
                holder.nested_recyclerview_main_feed.setAdapter(new NestedAdapter(mcontext, 3, motivationObjects,productObjects,eventObjects,schemeObjects));
                Log.e("SchemeAdapter", "Set");
                break;
        }

    }

    */
/*private void AdaptersInit() {
        Log.e("MainFeedAdapter---","AdapterInit");
        DatabaseReference MotivRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.Motivation_Post);
        Log.e("MotivRef","Set");
        MotivRef.keepSynced(true);
        MotivationAdapter = new FirebaseRecyclerAdapter<MotivationObject, ViewHolders.MOTIVATIONViewHolder>(
                MotivationObject.class, R.layout.motivations_element_view, ViewHolders.MOTIVATIONViewHolder.class, MotivRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.MOTIVATIONViewHolder viewHolder, MotivationObject model, int position) {
                Log.e("MotivRef viewholder","working");
                Glide.with(mcontext).load(model.getIMAGE()).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.motivation_imageview);

                viewHolder.motivation_textview.setText(model.getNAME());
            }
        };

        DatabaseReference ProductRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.PRODUCT_POST);
        Log.e("ProductRef","Set");
        ProductRef.keepSynced(true);
        ProductAdapter = new FirebaseRecyclerAdapter<ProductObject, ViewHolders.PRODUCTViewHolder>(
                ProductObject.class, R.layout.products_element_view, ViewHolders.PRODUCTViewHolder.class, ProductRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.PRODUCTViewHolder viewHolder, ProductObject model, int position) {
                Log.e("ProductRef viewholder","working");
                Glide.with(mcontext).load(model.getIMAGE()).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.product_imageview);
                viewHolder.product_category.setText(model.getCATEGORY());
                viewHolder.product_description.setText(model.getDESCRIPTION());
                viewHolder.product_name.setText(model.getNAME());
            }
        };

        DatabaseReference EventRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.EVENT_POST);
        Log.e("EventRef","Set");
        EventRef.keepSynced(true);
        EventAdapter = new FirebaseRecyclerAdapter<EventObject, ViewHolders.EVENTViewHolder>(
                EventObject.class,R.layout.events_element_view,ViewHolders.EVENTViewHolder.class,EventRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.EVENTViewHolder viewHolder, EventObject model, int position) {
                Log.e("EventRef viewholder","working");
                final String link = model.getLINK();
                Glide.with(mcontext).load(model.getIMAGE()).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.event_imageview);
                viewHolder.event_name.setText(model.getNAME());
               *//*
*/
/* viewHolder.event_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(link));
                        mcontext.startActivity(i);
                    }
                });*//*
*/
/*
                viewHolder.event_time.setText(model.getTIME());
            }
        };

        DatabaseReference SchemeRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.SCHEME_POST);
        Log.e("SchemeRef","Set");
        SchemeRef.keepSynced(true);
        SchemeAdapter = new FirebaseRecyclerAdapter<SchemeObject, ViewHolders.SCHEMESViewHolder>(
                SchemeObject.class,R.layout.schemes_element_view,ViewHolders.SCHEMESViewHolder.class,SchemeRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.SCHEMESViewHolder viewHolder, SchemeObject model, int position) {
                Log.e("SchemeRef viewholder","working");
                final String link = model.getLINK();
                viewHolder.scheme_name.setText(model.getNAME());
                viewHolder.scheme_description.setText(model.getDESCRIPTION());
                *//*
*/
/*viewHolder.event_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(link));
                        mcontext.startActivity(i);
                    }
                });*//*
*/
/*
            }
        };

    }*//*


    @Override
    public int getItemCount() {
        return NoOfElements;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView nested_recyclerview_main_feed;

        public ViewHolder(View itemView) {
            super(itemView);
            nested_recyclerview_main_feed = itemView.findViewById(R.id.nested_recyclerview_main_feed);
        }
    }

    private boolean collectingDataFromDatabase() {
        DatabaseReference MotivRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.Motivation_Post);
        MotivRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("MotivRef", "onDataChange");
                String name;
                MotivationObject motObject = new MotivationObject();
                for (DataSnapshot datasnap : dataSnapshot.getChildren()) {
                    name = datasnap.child(StringVariables.NAME).getValue() + "";
                    motObject.setNAME(name);
                    Log.e("Name ----", name);
                    motObject.setIMAGE(datasnap.child(StringVariables.IMAGE).getValue() + "");
                    motivationObjects.add(motObject);

                }

                DatabaseReference ProductRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.PRODUCT_POST);
                ProductRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("ProductRef", "onDataChange");
                        String name;
                        ProductObject proObject = new ProductObject();
                        for (DataSnapshot datasnap : dataSnapshot.getChildren()) {
                            name = datasnap.child(StringVariables.NAME).getValue() + "";
                            proObject.setNAME(name);
                            Log.e("Name ----", name);
                            proObject.setIMAGE(datasnap.child(StringVariables.IMAGE).getValue() + "");
                            proObject.setCATEGORY(datasnap.child(StringVariables.CATEGORY).getValue() + "");
                            proObject.setDESCRIPTION(datasnap.child(StringVariables.DESCRIPTION).getValue() + "");
                            productObjects.add(proObject);
                        }

                        DatabaseReference EventRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.EVENT_POST);
                        EventRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Log.e("EventRef", "onDataChange");
                                String name;
                                EventObject EveObject = new EventObject();
                                for (DataSnapshot datasnap : dataSnapshot.getChildren()) {
                                    name = datasnap.child(StringVariables.NAME).getValue() + "";
                                    EveObject.setNAME(name);
                                    Log.e("Name ----", name);
                                    EveObject.setIMAGE(datasnap.child(StringVariables.IMAGE).getValue() + "");
                                    EveObject.setVENUE(datasnap.child(StringVariables.VENUE).getValue() + "");
                                    EveObject.setTIME(datasnap.child(StringVariables.TIME).getValue() + "");
                                    EveObject.setLINK(datasnap.child(StringVariables.LINK).getValue() + "");
                                    eventObjects.add(EveObject);

                                }

                                DatabaseReference SchemeRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.SCHEME_POST);
                                SchemeRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Log.e("SchemeRef", "onDataChange");
                                        String name;
                                        SchemeObject schemeObject = new SchemeObject();
                                        for (DataSnapshot datasnap : dataSnapshot.getChildren()) {
                                            name = datasnap.child(StringVariables.NAME).getValue() + "";
                                            schemeObject.setNAME(name);
                                            Log.e("Name ----", name);
                                            schemeObject.setDESCRIPTION(datasnap.child(StringVariables.DESCRIPTION).getValue() + "");
                                            schemeObject.setLINK(datasnap.child(StringVariables.LINK).getValue() + "");
                                            schemeObjects.add(schemeObject);
                                        }
                                        datacollected = true;
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return datacollected;

    }
}

*/
