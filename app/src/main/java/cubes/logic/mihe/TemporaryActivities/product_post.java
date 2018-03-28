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

public class product_post extends AppCompatActivity {

    private static final int READ_EXTERNAL_STORAGE = 1;
    private int SELECT_PICTURE = 2;
    private Uri uri = null;
    ImageView product_imageview;
    EditText product_category, product_name, product_description, product_makers;
    Button product_post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_post);
        init();
    }

    private void init() {
        product_imageview = findViewById(R.id.product_imageview);
        product_category = findViewById(R.id.product_category);
        product_name = findViewById(R.id.product_name);
        product_description = findViewById(R.id.product_description);
        product_post = findViewById(R.id.product_post);
        product_makers = findViewById(R.id.product_makers);

        product_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission();
            }
        });

        product_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String category = product_category.getText().toString();
                final String name = product_name.getText().toString();
                final String description = product_description.getText().toString();
                final String makers = product_makers.getText().toString();
                if (uri != null && !(name.isEmpty() && category.isEmpty() && description.isEmpty())) {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.PRODUCT_POST).push();
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(StringVariables.PRODUCT_POST);
                    storageReference.child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String data = taskSnapshot.getDownloadUrl() + "";
                            databaseReference.child(StringVariables.NAME).setValue(name);
                            databaseReference.child(StringVariables.CATEGORY).setValue(category);
                            databaseReference.child(StringVariables.DESCRIPTION).setValue(description);
                            databaseReference.child(StringVariables.MAKERS).setValue(makers);
                            databaseReference.child(StringVariables.IMAGE).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
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
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            open_gallery();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            open_gallery();
        } else {
            Toast.makeText(getApplicationContext(), "Access denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void open_gallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE) {
            uri = data.getData();
            Glide.with(this).load(uri).placeholder(R.mipmap.ic_launcher).into(product_imageview);
        }
    }
}
