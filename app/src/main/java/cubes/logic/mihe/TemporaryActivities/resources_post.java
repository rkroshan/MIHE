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
 * Created by CREATOR on 3/26/2018.
 */

public class resources_post extends AppCompatActivity {

    private static final int READ_EXTERNAL_STORAGE = 1;
    private static final int SELECT_PICTURE = 2,SELECT_BOOK=3;
    ImageView resouces_imageview,resouces_book;
    EditText resouces_name,resouces_author;
    Button resource_post;
    String title,author,link;
    private Uri uri,book_uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resouces_post);

        init();
    }

    private void init() {
        resouces_imageview = findViewById(R.id.resouces_imageview);
        resouces_book = findViewById(R.id.resouces_book);
        resouces_name = findViewById(R.id.resouces_name);
        resouces_author = findViewById(R.id.resouces_author);
        resource_post = findViewById(R.id.resource_post);

        resouces_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission();
            }
        });
        resouces_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_file(SELECT_BOOK);
            }
        });

        resource_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = resouces_name.getText().toString();
                author = resouces_author.getText().toString();
                if(!author.isEmpty()&& !title.isEmpty() && uri!=null && book_uri!=null){
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.RESOURCES).push();
                    final StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(StringVariables.RESOURCES);
                    storageReference.child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            databaseReference.child(StringVariables.BOOK_URL).setValue(taskSnapshot.getDownloadUrl().toString());

                            storageReference.child(book_uri.getLastPathSegment()).putFile(book_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    databaseReference.child(StringVariables.IMAGE).setValue(taskSnapshot.getDownloadUrl().toString());
                                    databaseReference.child(StringVariables.NAME).setValue(title);
                                    databaseReference.child(StringVariables.AUTHOR).setValue(author).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                                }
                            });

                        }
                    });
                                    }else {
                    Toast.makeText(getApplicationContext(),"Fill all details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void open_file(int selectBook) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),selectBook);
    }

    private void checkpermission() {
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            open_gallery(SELECT_PICTURE);
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
            open_gallery(SELECT_PICTURE);
        }else{
            Toast.makeText(getApplicationContext(),"Access denied",Toast.LENGTH_SHORT).show();
        }
    }

    private void open_gallery(int request_code) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PICTURE){
            uri = data.getData();
            Glide.with(this).load(uri).placeholder(R.mipmap.ic_launcher).into(resouces_imageview);
        }else if(requestCode==SELECT_BOOK){
            book_uri = data.getData();
            Glide.with(this).load(book_uri).placeholder(R.mipmap.ic_launcher).into(resouces_book);
        }
    }
}
