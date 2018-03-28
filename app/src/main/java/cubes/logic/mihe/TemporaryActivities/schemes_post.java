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

public class schemes_post extends AppCompatActivity {

    private static final int READ_EXTERNAL_STORAGE = 1;
    private static final int SELECT_PICTURE = 2;
    EditText scheme_viewdetails;
    Button scheme_post;
    ImageView scheme_image;
    private Uri uri = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schemes_post);
        init();
    }

    private void init() {
        scheme_image = findViewById(R.id.scheme_image);
        scheme_viewdetails = findViewById(R.id.scheme_viewdetails);
        scheme_post = findViewById(R.id.scheme_post);

        scheme_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission();
            }
        });

        scheme_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String link = scheme_viewdetails.getText().toString();

                if (!(link.isEmpty())&& uri!=null) {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.SCHEME_POST).push();
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(StringVariables.SCHEME_POST);
                    storageReference.child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            databaseReference.child(StringVariables.IMAGE).setValue(taskSnapshot.getDownloadUrl().toString());
                            databaseReference.child(StringVariables.LINK).setValue(link).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_SHORT).show();
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
        if(requestCode==READ_EXTERNAL_STORAGE&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
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
            Glide.with(this).load(uri).placeholder(R.mipmap.ic_launcher).into(scheme_image);
        }
    }
}
