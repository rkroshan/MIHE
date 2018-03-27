package cubes.logic.mihe.TemporaryActivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/22/2018.
 */

public class schemes_post extends AppCompatActivity {

    EditText scheme_name,scheme_description,scheme_viewdetails;
    Button scheme_post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schemes_post);
        init();
    }

    private void init() {
        scheme_name = findViewById(R.id.scheme_name);
        scheme_description = findViewById(R.id.scheme_description);
        scheme_viewdetails = findViewById(R.id.scheme_viewdetails);
        scheme_post = findViewById(R.id.scheme_post);

        scheme_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = scheme_name.getText().toString();
                String description = scheme_description.getText().toString();
                String link = scheme_viewdetails.getText().toString();

                if(!(name.isEmpty()&&description.isEmpty()&&link.isEmpty())){
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.SCHEME_POST).push();
                    databaseReference.child(StringVariables.NAME).setValue(name);
                    databaseReference.child(StringVariables.DESCRIPTION).setValue(description);
                    databaseReference.child(StringVariables.LINK).setValue(link).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        });
    }
}
