package cubes.logic.mihe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class UserDashboardFragment extends Fragment {

    ArrayList<IdeaData> userIdeas = new ArrayList<>();
    ArrayList<ProductData> userProducts = new ArrayList<>();
    ArrayList<SubmissionData> userSubmissions = new ArrayList<>();
    ArrayList<EventsData> userEvents = new ArrayList<>();

    TextView name, handleText, specialisation, location, submissions_text, ideas_text, products_text, events_text;
    ImageView image, web, mail, linkedIn, github;

    String handle = "-L8S3DxEUZsQ_pVodMer";
    SubmissionAdapter submissionsAdapter;
    IdeasAdapter ideasAdapter;
    ProductAdapter productsAdapter;
    EventAdapter eventAdapter;
    RecyclerView submissions, ideas, products, events;

    public UserDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        name = view.findViewById(R.id.name_dashboard);
        handleText = view.findViewById(R.id.handle_dashboard);
        specialisation = view.findViewById(R.id.specialisation_dashboard);
        location = view.findViewById(R.id.location_dashboard);
        submissions_text = view.findViewById(R.id.submissions_dashboard_textview);
        ideas_text = view.findViewById(R.id.ideas_dashboard_textview);
        products_text = view.findViewById(R.id.products_dashboard_textview);
        events_text = view.findViewById(R.id.events_dashboard_textview);
        submissions = view.findViewById(R.id.submissions_dashboard_recyclerview);
        events = view.findViewById(R.id.events_dashboard_recyclerview);
        ideas = view.findViewById(R.id.ideas_dashboard_recyclerview);
        products = view.findViewById(R.id.products_dashboard_recyclerview);
        image = view.findViewById(R.id.user_image_dashboard);
        web = view.findViewById(R.id.web_button);
        mail = view.findViewById(R.id.mail_button);
        linkedIn = view.findViewById(R.id.linked_button);
        github = view.findViewById(R.id.github_button);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(handle);
        databaseReference.addValueEventListener(new ValueEventListener() {
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

    private void updateData(final UserData userData) {
        specialisation.setText(userData.specialisation);
        location.setText(userData.location);
        name.setText(userData.name);
        handleText.setText("@" + handle);
        eventAdapter = new EventAdapter();
        events.setAdapter(eventAdapter);
        events.setLayoutManager(new LinearLayoutManager(getActivity()));
        Glide
                .with(getActivity())
                .load(userData.img_url)
                .asBitmap()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(new BitmapImageViewTarget(image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularDrawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularDrawable.setCircular(true);
                        image.setImageDrawable(circularDrawable);
                    }
                })
        ;

        if(userData.getWebsite()==null)
            web.setVisibility(View.GONE);
        if(userData.getEmail()==null)
            mail.setVisibility(View.GONE);
        if(userData.getLinkedin()==null)
            linkedIn.setVisibility(View.GONE);
        if(userData.getGithub()==null)
            github.setVisibility(View.GONE);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(userData.getWebsite()));
                startActivity(i);

            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("mailto:"+userData.getEmail()));
                startActivity(i);
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(userData.getLinkedin()));
                startActivity(i);

            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(userData.getGithub()));
                startActivity(i);

            }
        });

        if (userData.inst_code != null) {
            loadEventCodes(userData.inst_code);
        }
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

    private void loadEventCodes(String inst_code) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ecells").child(inst_code);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.child("events").getChildren()) {
                    loadEvents(((String) childSnapshot.getValue()).trim());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadEvents(String eventKey) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("events").child(eventKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EventsData eventsData = dataSnapshot.getValue(EventsData.class);
                if (eventsData != null && !userEvents.contains(eventsData)) {
                    userEvents.add(eventsData);
                    events.setVisibility(View.VISIBLE);
                    events_text.setVisibility(View.VISIBLE);
                    eventAdapter.notifyItemInserted(userEvents.size() - 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadProduct(String next) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(next);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductData productData = dataSnapshot.getValue(ProductData.class);
                if (productData != null && !userProducts.contains(productData)) {
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SubmissionData submissionData = dataSnapshot.getValue(SubmissionData.class);
                if (submissionData != null && !userSubmissions.contains(submissionData)) {
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IdeaData ideaData = dataSnapshot.getValue(IdeaData.class);
                if (ideaData != null && !userIdeas.contains(ideaData)) {
                    userIdeas.add(ideaData);
                    ideasAdapter.notifyItemInserted(userIdeas.size() - 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_element_view, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            EventsData eventsData = userEvents.get(position);
            holder.name.setText("Event name: " + eventsData.title);
            holder.venue.setText("Venue: " + eventsData.venue);
            holder.time.setText("Time: " + eventsData.time);
            holder.organiser.setText("Organiser: " + eventsData.institute_name);
            Glide.with(getActivity())
                    .load(eventsData.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return userEvents.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView name, venue, time, organiser;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.event_name);
                venue = itemView.findViewById(R.id.event_venue);
                time = itemView.findViewById(R.id.event_time);
                organiser = itemView.findViewById(R.id.event_organiser);
                imageView = itemView.findViewById(R.id.event_imageview);
            }
        }
    }

    public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.ViewHolder> {


        public IdeasAdapter() {
        }

        @Override
        public IdeasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_dashboard, parent, false));
        }

        @Override
        public void onBindViewHolder(IdeasAdapter.ViewHolder holder, int position) {
            holder.title.setText("• "+userIdeas.get(position).title);
        }

        @Override
        public int getItemCount() {
            return userIdeas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.idea_title);
            }
        }
    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


        public ProductAdapter() {
        }

        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_dashboard, parent, false));
        }

        @Override
        public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
            holder.title.setText("• "+userProducts.get(position).title);
        }

        @Override
        public int getItemCount() {
            return userProducts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.product_title);
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
            holder.title.setText("• "+userSubmissions.get(position).title);
            String temp = userSubmissions.get(position).getStatus().trim().equals("-1")?"Rejected : ":userSubmissions.get(position).getStatus().trim().equals(1)?"Approved : ":"Pending : ";
            holder.status.setText(temp+userSubmissions.get(position).review);
            holder.status.setTextColor(userSubmissions.get(position).getStatus().trim().equals("0")?Color.rgb(6,0,1):userSubmissions.get(position).getStatus().trim().equals("-1")? Color.rgb(240,0,0):Color.rgb(0,181,0));
        }

        @Override
        public int getItemCount() {
            return userSubmissions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, status;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.submission_title);
                status = itemView.findViewById(R.id.submission_status);
            }
        }
    }
}
