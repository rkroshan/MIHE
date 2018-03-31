package cubes.logic.mihe.Explore.Explore_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import cubes.logic.mihe.Feed.models.ProductObject;
import cubes.logic.mihe.Feed.models.ViewHolders;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/27/2018.
 */

public class Product_explore extends AppCompatActivity {

    RecyclerView temp_recyclerview;
    FirebaseRecyclerAdapter<ProductObject,ViewHolders.PRODUCTViewHolder> firebaseRecyclerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_explore);
        temp_recyclerview = findViewById(R.id.temp_recyclerview);
        temp_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference ProductRef = FirebaseDatabase.getInstance().getReference().child(StringVariables.PRODUCT_POST);
        Log.e("ProductRef", "Set");
        ProductRef.keepSynced(true);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductObject, ViewHolders.PRODUCTViewHolder>(
                ProductObject.class, R.layout.products_element_view, ViewHolders.PRODUCTViewHolder.class, ProductRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolders.PRODUCTViewHolder viewHolder, ProductObject model, int position) {
                Log.e("ProductRef viewholder", "working");
                Glide.with(getApplicationContext()).load(model.getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.product_imageview);
                viewHolder.product_category.setText(model.getCATEGORY());
                viewHolder.product_description.setText(model.getDESCRIPTION());
                viewHolder.product_name.setText(model.getNAME());
            }
        };

        temp_recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
