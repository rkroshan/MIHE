package cubes.logic.mihe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_network, container, false);
        recyclerView=view.findViewById(R.id.network_recyclerview);
        recyclerView.setAdapter(new UserCardAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userCardAdapter=new UserCardAdapter();
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
                userCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            UserData userData=userDataArrayList.get(position);
            Glide.with(getActivity())
                    .load(userData.img_url)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.user);
            holder.name.setText(userData.name);
            holder.institute.setText(userData.institution_name);
            holder.specialisation.setText(userData.specialisation);

        }

        @Override
        public int getItemCount() {
            return userDataArrayList.size();
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
