package cubes.logic.mihe.Explore.Explore_Fragments.ideas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class ideas extends AppCompatActivity {

    Switch idea_switch;
    RecyclerView public_ides_recyclerview, sponsered_ides_recyclerview;
    FirebaseRecyclerAdapter<ideaObject, PublicIdeaViewholder> publicIdeaViewholderFirebaseRecyclerAdapter;
    FirebaseRecyclerAdapter<ideaObject, SponseredIdeaViewholder> sponseredIdeaViewholderFirebaseRecyclerAdapter;
    DatabaseReference publicidea_dataref, sponseredidea_dataref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ideas_frag);
        publicidea_dataref = FirebaseDatabase.getInstance().getReference().child(StringVariables.IDEAS).child(StringVariables.PUBLIC_IDEAS);
        sponseredidea_dataref = FirebaseDatabase.getInstance().getReference().child(StringVariables.IDEAS).child(StringVariables.COMPETITIVE_IDEAS);
        init();
    }


    private void init() {
        idea_switch = findViewById(R.id.idea_switch);
        public_ides_recyclerview = findViewById(R.id.public_ides_recyclerview);
        sponsered_ides_recyclerview = findViewById(R.id.sponsered_ides_recyclerview);
        idea_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    public_ides_recyclerview.setVisibility(View.GONE);
                    sponsered_ides_recyclerview.setVisibility(View.VISIBLE);
                } else {
                    public_ides_recyclerview.setVisibility(View.VISIBLE);
                    sponsered_ides_recyclerview.setVisibility(View.GONE);
                }
            }
        });

        public_ides_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        sponsered_ides_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        publicIdeaViewholderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ideaObject, PublicIdeaViewholder>(
                ideaObject.class, R.layout.publicidea_elementview, PublicIdeaViewholder.class, publicidea_dataref
        ) {
            @Override
            protected void populateViewHolder(PublicIdeaViewholder viewHolder, ideaObject model, int position) {
                try {
                    viewHolder.setIdea_name(model.getIDEA_NAME());
                } catch (Exception e) {
                }
                viewHolder.setIdea_description(model.getIDEA_DESCRIPTION());
            }
        };
        public_ides_recyclerview.setAdapter(publicIdeaViewholderFirebaseRecyclerAdapter);

        sponseredIdeaViewholderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ideaObject, SponseredIdeaViewholder>(
                ideaObject.class, R.layout.publicidea_elementview, SponseredIdeaViewholder.class, sponseredidea_dataref
        ) {
            @Override
            protected void populateViewHolder(SponseredIdeaViewholder viewHolder, ideaObject model, int position) {
                viewHolder.setIdea_name(model.getIDEA_NAME());
                viewHolder.setIdea_description(model.getIDEA_DESCRIPTION());
            }
        };
        sponsered_ides_recyclerview.setAdapter(sponseredIdeaViewholderFirebaseRecyclerAdapter);
    }

    public static class PublicIdeaViewholder extends RecyclerView.ViewHolder {
        TextView idea_name, idea_description;
        View publicidea_view;

        public PublicIdeaViewholder(View itemView) {
            super(itemView);
            idea_name = itemView.findViewById(R.id.idea_name);
            idea_name.setVisibility(View.GONE);
            idea_description = itemView.findViewById(R.id.idea_description);
            publicidea_view = itemView.findViewById(R.id.publicidea_view);
            publicidea_view.setVisibility(View.GONE);
        }

        private void setIdea_name(String name) {
            idea_name.setText(name);
        }

        private void setIdea_description(String desc) {
            idea_description.setText(desc);
        }
    }

    public static class SponseredIdeaViewholder extends RecyclerView.ViewHolder {
        TextView idea_name, idea_description;

        public SponseredIdeaViewholder(View itemView) {
            super(itemView);
            idea_name = itemView.findViewById(R.id.idea_name);
            idea_description = itemView.findViewById(R.id.idea_description);
        }

        private void setIdea_name(String name) {
            idea_name.setText(name);
        }

        private void setIdea_description(String desc) {
            idea_description.setText(desc);
        }
    }
}
