package cubes.logic.mihe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class DashboardFragment extends Fragment {

    ArrayList<IdeaData> userIdeas = new ArrayList<>();
    ArrayList<ProductData> userProducts = new ArrayList<>();
    ArrayList<SubmissionData> userSubmissions = new ArrayList<>();

    TextView name,handleText,specialisation, location, submissions_text, ideas_text, products_text;
    ImageView image, web, fb, mail, linkedIn, twitter, gplus, github, more;

    String handle="-L8S3DxEUZsQ_pVodMer";
    SubmissionAdapter submissionsAdapter;
    IdeasAdapter ideasAdapter;
    ProductAdapter productsAdapter;
    RecyclerView submissions, ideas, products;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        name=view.findViewById(R.id.name_dashboard);
        handleText=view.findViewById(R.id.handle_dashboard);
        specialisation = view.findViewById(R.id.specialisation_dashboard);
        location = view.findViewById(R.id.location_dashboard);
        submissions_text = view.findViewById(R.id.submissions_dashboard_textview);
        ideas_text = view.findViewById(R.id.ideas_dashboard_textview);
        products_text = view.findViewById(R.id.products_dashboard_textview);
        submissions = view.findViewById(R.id.submissions_dashboard_recyclerview);
        ideas = view.findViewById(R.id.ideas_dashboard_recyclerview);
        products = view.findViewById(R.id.products_dashboard_recyclerview);
        image=view.findViewById(R.id.user_image_dashboard);
        web = view.findViewById(R.id.web_button);
        fb = view.findViewById(R.id.fb_button);
        mail = view.findViewById(R.id.mail_button);
        linkedIn = view.findViewById(R.id.linked_button);
        twitter = view.findViewById(R.id.twitter_button);
        gplus = view.findViewById(R.id.gplus_button);
        github = view.findViewById(R.id.github_button);
        more = view.findViewById(R.id.more_button);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(handle);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData userData = dataSnapshot.getValue(UserData.class);
                Log.d("userData", userData.toString());
                updateData(userData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateData(UserData userData) {
        specialisation.setText(userData.specialisation);
        location.setText(userData.location);
        name.setText(userData.name);
        handleText.setText("@"+handle);
        Glide
                .with(getActivity())
                .load(userData.img_url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);

        if (userData.submissions.size() > 0) {
            submissions_text.setVisibility(View.VISIBLE);
            submissions.setVisibility(View.VISIBLE);
            submissionsAdapter = new SubmissionAdapter();
            submissions.setAdapter(submissionsAdapter);
            submissions.setLayoutManager(new LinearLayoutManager(getActivity()));
            Iterator<String> stringIterator = userData.submissions.iterator();
            while (stringIterator.hasNext())
                loadSubmission(stringIterator.next());
        }
        if (userData.ideas.size() > 0) {
            ideas_text.setVisibility(View.VISIBLE);
            ideas.setVisibility(View.VISIBLE);
            ideasAdapter = new IdeasAdapter();
            ideas.setAdapter(ideasAdapter);
            ideas.setLayoutManager(new LinearLayoutManager(getActivity()));

            Iterator<String> stringIterator = userData.ideas.iterator();
            while (stringIterator.hasNext())
                loadIdea(stringIterator.next());
        }
        if (userData.products.size() > 0) {
            products_text.setVisibility(View.VISIBLE);
            products.setVisibility(View.VISIBLE);
            productsAdapter = new ProductAdapter();
            products.setAdapter(productsAdapter);
            products.setLayoutManager(new LinearLayoutManager(getActivity()));
            Iterator<String> stringIterator = userData.products.iterator();
            while (stringIterator.hasNext())
                loadProduct(stringIterator.next());
        }
    }

    private void loadProduct(String next) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(next);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductData productData=dataSnapshot.getValue(ProductData.class);
                if (productData!=null&&!userProducts.contains(productData)) {
                    userProducts.add(productData);
                    productsAdapter.notifyItemInserted(userProducts.size() - 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadSubmission(String next) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("submissions").child(next);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SubmissionData submissionData=dataSnapshot.getValue(SubmissionData.class);
                if (submissionData!=null&&!userSubmissions.contains(submissionData)) {
                    userSubmissions.add(submissionData);
                    submissionsAdapter.notifyItemInserted(userProducts.size() - 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadIdea(String next) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ideas").child(next);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IdeaData ideaData=dataSnapshot.getValue(IdeaData.class);
                if (ideaData!=null&&!userIdeas.contains(ideaData)) {
                    userIdeas.add(ideaData);
                    ideasAdapter.notifyItemInserted(userIdeas.size() - 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.ViewHolder> {


        public IdeasAdapter() {
        }

        @Override
        public IdeasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_dashboard,parent,false));
        }

        @Override
        public void onBindViewHolder(IdeasAdapter.ViewHolder holder, int position) {
            holder.title.setText(userIdeas.get(position).title);
        }

        @Override
        public int getItemCount() {
            return userIdeas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            public ViewHolder(View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.idea_title);
            }
        }
    }
    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


        public ProductAdapter() {
        }

        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_dashboard,parent,false));
        }

        @Override
        public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
            holder.title.setText(userProducts.get(position).title);
        }

        @Override
        public int getItemCount() {
            return userProducts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            public ViewHolder(View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.product_title);
            }
        }
    }
    public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.ViewHolder> {


        @Override
        public SubmissionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SubmissionAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.submission_dashboard, parent, false));
        }

        @Override
        public void onBindViewHolder(SubmissionAdapter.ViewHolder holder, int position) {
            holder.title.setText(userSubmissions.get(position).title);
            holder.status.setText(userSubmissions.get(position).status);
        }

        @Override
        public int getItemCount() {
            return userSubmissions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title,status;
            public ViewHolder(View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.submission_title);
                status=itemView.findViewById(R.id.submission_status);
            }
        }
    }
}
