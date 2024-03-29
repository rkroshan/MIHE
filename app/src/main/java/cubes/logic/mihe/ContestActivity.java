package cubes.logic.mihe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContestActivity extends AppCompatActivity {

    Button participate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_contest);
        participate=findViewById(R.id.participate_button);
        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContestActivity.this,CompetitionSubmissionActivity.class));
            }
        });
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("competition").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Competition competition=dataSnapshot.getValue(Competition.class);
//                updateActivity(competition);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }

    private void updateActivity(Competition competition) {

    }

    public class Competition {
        String description,enabled,image,rules,time_range,title;

        public Competition() {

        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public String getTime_range() {
            return time_range;
        }

        public void setTime_range(String time_range) {
            this.time_range = time_range;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
