package cubes.logic.mihe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class NetworkFragment extends Fragment {


    public NetworkFragment() {
        // Required empty public constructor
    }
    ArrayList<UserData> userDataArrayList=new ArrayList<>();
    RecyclerView recyclerView;
    UserCardAdapter userCardAdapter;
    EditText search;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_network, container, false);
        userCardAdapter = new UserCardAdapter();
        recyclerView=view.findViewById(R.id.network_recyclerview);
        search=view.findViewById(R.id.network_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                userCardAdapter.applyFilter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userCardAdapter.applyFilter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        recyclerView.setAdapter(userCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.child("users").getChildren()) {
                    UserData tempUser=childSnapshot.getValue(UserData.class);

                    if(tempUser!=null&&!userDataArrayList.contains(tempUser))
                        userDataArrayList.add(tempUser);
                }
                userCardAdapter.refreshFilter();
                userCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {

        String fil="";

        public UserCardAdapter() {
            filtered = new ArrayList<>();
            for(UserData u:userDataArrayList)
                filtered.add(u);
        }

        public void applyFilter(String str) {
            filtered.clear();
            fil = str;
            for(UserData u:userDataArrayList) {
                if(u.toString().toLowerCase().contains(fil.toLowerCase())) {
                    filtered.add(u);
                }
            }

            userCardAdapter.notifyDataSetChanged();
        }
        public void refreshFilter() {
            filtered.clear();
            for(UserData u:userDataArrayList) {
                if(u.toString().toLowerCase().contains(fil.toLowerCase())) {
                    filtered.add(u);
                }
            }
            userCardAdapter.notifyDataSetChanged();
        }

        private ArrayList<UserData> filtered;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card,parent,false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final UserData userData=filtered.get(position);
            Glide.with(getActivity())
                    .load(userData.img_url)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(new BitmapImageViewTarget(holder.user){
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularDrawable = RoundedBitmapDrawableFactory.create(getContext().getResources(),resource);
                            circularDrawable.setCircular(true);
                            holder.user.setImageDrawable(circularDrawable);
                        }
                    });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),PersonActivity.class).putExtra("id",userData.getHandle()));
                }
            });
            holder.name.setText(userData.name);
            holder.institute.setText(userData.institution_name);
            holder.specialisation.setText(userData.specialisation);

        }

        @Override
        public int getItemCount() {
            return filtered.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView user;
            TextView name,institute,specialisation;
            public ViewHolder(View itemView) {
                super(itemView);
                user=itemView.findViewById(R.id.user_image_card);
                name=itemView.findViewById(R.id.user_name_card);
                institute=itemView.findViewById(R.id.institute_name_card);
                specialisation=itemView.findViewById(R.id.specialisation_card);
            }
        }
    }
}
