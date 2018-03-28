package cubes.logic.mihe.TemporaryActivities;

import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import cubes.logic.mihe.Manifest;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/22/2018.
 */

public class motivation_post extends AppCompatActivity{

    private static final int READ_EXTERNAL_STORAGE = 1;
    ImageView motivation_post_imageview;
    EditText motivation_post_textview,motivation_post_textview_link;
    Button motivation_post_button;
    private int SELECT_PICTURE = 2;
    private Uri uri=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motivation_post);

        init();
    }

    private void init() {
        motivation_post_imageview = findViewById(R.id.motivation_post_imageview);
        motivation_post_textview = findViewById(R.id.motivation_post_textview);
        motivation_post_button = findViewById(R.id.motivation_post_button);
        motivation_post_textview_link = findViewById(R.id.motivation_post_textview_link);

        motivation_post_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission();
            }
        });

        motivation_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = motivation_post_textview.getText().toString();
                final String link = motivation_post_textview_link.getText().toString();
                if(uri!=null && !(name.isEmpty()) && !(link.isEmpty())){
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.Motivation_Post).push();
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(StringVariables.Motivation_Post);
                    storageReference.child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String data = taskSnapshot.getDownloadUrl()+"";
                            databaseReference.child(StringVariables.NAME).setValue(name);
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
            Glide.with(this).load(uri).placeholder(R.mipmap.ic_launcher).into(motivation_post_imageview);
        }
    }
}
