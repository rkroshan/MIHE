package cubes.logic.mihe.Explore.Explore_Fragments.resouces;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

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
    private int data ;

    public Resources(){

    }

    @SuppressLint("ValidFragment")
    public Resources(int i) {
        data = i;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resources_frag,container,false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.RESOURCES);
        databaseReference.keepSynced(true);
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
                viewHolder.setResources_url(model.getIMAGE());
                viewHolder.setResouces_imageview(model.getBOOK_URL());
                viewHolder.setResouces_name(model.getNAME());
                viewHolder.setResouces_author(model.getAUTHOR());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ResourcesViewHolder extends RecyclerView.ViewHolder {
        ImageView resouces_imageview,resouces_download;
        TextView resouces_name,resouces_author;
        String Link=null;
        String Resource_name = null;
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
                        Toast.makeText(itemView.getContext(),"Downloading File",Toast.LENGTH_SHORT).show();
                        DownloadManager mManager = (DownloadManager) itemView.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(Link);
                        DownloadManager.Request mrequest = new DownloadManager.Request(uri);
                        mrequest.setTitle(Resource_name);
                        mrequest.setDescription("File Downloading...");
                        mrequest.allowScanningByMediaScanner();
                        mrequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        mrequest.setDestinationInExternalFilesDir(itemView.getContext(), Environment.DIRECTORY_DOWNLOADS,Resource_name);
                        mManager.enqueue(mrequest);
                    }
                }
            });
        }

        private void setResouces_imageview(String data){
            Glide.with(itemView.getContext()).load(data).placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(resouces_imageview);

            Log.e("Image link",data);
        }
        private void setResources_url(String data){
            Link = data;
        }
        private void setResouces_name(String name){
            Resource_name = name;
            resouces_name.setText(name);}
        private void setResouces_author(String name){resouces_author.setText(name);}
    }
}
