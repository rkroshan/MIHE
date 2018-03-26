package cubes.logic.mihe.Explore.Explore_Fragments.resouces;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class Resources extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<ResourcesObject,ResourcesViewHolder> firebaseRecyclerAdapter;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resources_frag,container,false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.RESOURCES);
        recyclerView = view.findViewById(R.id.resouces_recylcerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ResourcesObject, ResourcesViewHolder>(
                ResourcesObject.class,R.layout.resources_elementview,ResourcesViewHolder.class,databaseReference
        ) {
            @Override
            protected void populateViewHolder(ResourcesViewHolder viewHolder, ResourcesObject model, int position) {
                viewHolder.setResouces_imageview(model.getIMAGE());
                viewHolder.setResouces_name(model.getNAME());
                viewHolder.setResouces_author(model.getAUTHOR());
                viewHolder.setResouces_download(model.getDOWNLOAD_URL());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ResourcesViewHolder extends RecyclerView.ViewHolder {
        ImageView resouces_imageview,resouces_download;
        TextView resouces_name,resouces_author;
        String Link=null;
        public ResourcesViewHolder(final View itemView) {
            super(itemView);
            resouces_imageview = itemView.findViewById(R.id.resouces_imageview);
            resouces_download = itemView.findViewById(R.id.resouces_download);
            resouces_name = itemView.findViewById(R.id.resouces_name);
            resouces_author = itemView.findViewById(R.id.resouces_author);

            resouces_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Link!=null){
                       // Download starts
                    }
                }
            });
        }

        private void setResouces_download(String url){Link = url;}
        private void setResouces_imageview(String data){
            Glide.with(itemView.getContext()).load(data).placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(resouces_imageview);

            Log.e("Image link",data);
        }
        private void setResouces_name(String name){resouces_name.setText(name);}
        private void setResouces_author(String name){resouces_author.setText(name);}
    }
}
