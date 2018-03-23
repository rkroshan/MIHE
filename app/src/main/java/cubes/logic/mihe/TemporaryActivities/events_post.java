package cubes.logic.mihe.TemporaryActivities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/22/2018.
 */

public class events_post extends AppCompatActivity {

    private static final int READ_EXTERNAL_STORAGE = 1;
    ImageView event_imageview;
    EditText event_name,event_venue,event_time,event_link;
    private int SELECT_PICTURE = 2;
    private Uri uri=null;
    Button event_post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_post);

        init();
    }

    private void init() {
        event_imageview = findViewById(R.id.event_imageview);
        event_name = findViewById(R.id.event_name);
        event_venue = findViewById(R.id.event_venue);
        event_time = findViewById(R.id.event_time);
        event_link = findViewById(R.id.event_link);
        event_post = findViewById(R.id.event_post);

        event_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission();
            }
        });

        event_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = event_name.getText().toString();
                final String venue = event_venue.getText().toString();
                final String time = event_time.getText().toString();
                final String link = event_link.getText().toString();
                if(uri!=null && !(name.isEmpty() &&venue.isEmpty() && time.isEmpty() && link.isEmpty()) ){
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.EVENT_POST).push();
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(StringVariables.EVENT_POST);
                    storageReference.child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String data = taskSnapshot.getDownloadUrl()+"";
                            databaseReference.child(StringVariables.NAME).setValue(name);
                            databaseReference.child(StringVariables.VENUE).setValue(venue);
                            databaseReference.child(StringVariables.TIME).setValue(time);
                            databaseReference.child(StringVariables.LINK).setValue(link);
                            databaseReference.child(StringVariables.IMAGE).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    });
                }
                }
        });
    }

    private void checkpermission() {
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            open_gallery();
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},READ_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==READ_EXTERNAL_STORAGE&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            open_gallery();
        }else{
            Toast.makeText(getApplicationContext(),"Access denied",Toast.LENGTH_SHORT).show();
        }
    }

    private void open_gallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PICTURE){
            uri = data.getData();
            Glide.with(this).load(uri).placeholder(R.mipmap.ic_launcher).into(event_imageview);
        }
    }
}
