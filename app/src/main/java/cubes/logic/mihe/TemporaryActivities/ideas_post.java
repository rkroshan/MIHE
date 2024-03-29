package cubes.logic.mihe.TemporaryActivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class ideas_post extends AppCompatActivity {

    Switch idea_switch;
    EditText idea_name, idea_description;
    Button post_button;
    String idea_type = StringVariables.PUBLIC_IDEAS;
    String thinker_name, handle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_post);
        thinker_name = getIntent().getExtras().getString("name");
        handle =getIntent().getExtras().getString("id");
        init();
    }

    private void init() {
        idea_switch = findViewById(R.id.idea_switch);
        idea_name = findViewById(R.id.idea_name);
        idea_name.setVisibility(View.GONE);
        idea_description = findViewById(R.id.idea_description);
        post_button = findViewById(R.id.post_button);

        idea_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    idea_type = StringVariables.COMPETITIVE_IDEAS;
                    idea_name.setVisibility(View.VISIBLE);

                } else {
                    idea_type = StringVariables.PUBLIC_IDEAS;
                    idea_name.setVisibility(View.GONE);
                }
            }
        });

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "";
                try {
                    name = idea_name.getText().toString();
                } catch (Exception e) {
                    name = "";
                }
                String desc = idea_description.getText().toString();
                if (!(desc.isEmpty())) {
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.IDEAS).child(idea_type).push();
                    databaseReference.child(StringVariables.IDEA_NAME).setValue(name);
                    databaseReference.child(StringVariables.THINKER).setValue(thinker_name);
                    final String key = databaseReference.getKey();
                    databaseReference.child(StringVariables.IDEA_DESCRIPTION).setValue(desc).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            addToIdeas(databaseReference.getKey());
                        }
                    });

                }
            }
        });
    }
    void addToIdeas(String str) {
        Log.d("str",str);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(handle).child("ideas").push();
        databaseReference.setValue(str);
        finish();
    }
}
