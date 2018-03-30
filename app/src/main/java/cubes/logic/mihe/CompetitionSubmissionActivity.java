package cubes.logic.mihe;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CompetitionSubmissionActivity extends AppCompatActivity {

    private static final int READ_EXTERNAL_STORAGE = 1;
    private static final int SELECT_PICTURE =2 ;
    EditText title,details;
    AppCompatSpinner category;
    Button submit,upload;
    private Uri uri;
    ArrayList<String> departments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        departments.add("Agriculture");
        departments.add("Internet");
        departments.add("Transport");
        setContentView(R.layout.activity_competition_submission);
        title=findViewById(R.id.submission_edit_title);
        details=findViewById(R.id.submission_edit_details);
        category=findViewById(R.id.submission_category_spinner);
        upload=findViewById(R.id.submission_upload_abstract);
        submit=findViewById(R.id.submission_finish_button);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpermission();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = title.getText().toString();
                String desc = details.getText().toString();
                String ecell = "";
//                String status = 0;
//                ArrayList<String> submitters = new ArrayList<String>("i");
//                String dept = departments.get(category.getSelectedItemPosition()));
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
        }
    }
}
