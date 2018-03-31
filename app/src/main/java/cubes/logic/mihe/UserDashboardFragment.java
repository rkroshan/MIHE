package cubes.logic.mihe;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import cubes.logic.mihe.TemporaryActivities.events_post;
import cubes.logic.mihe.TemporaryActivities.ideas_post;
import cubes.logic.mihe.TemporaryActivities.product_post;

public class UserDashboardFragment extends Fragment {

    ArrayList<IdeaData> userIdeas = new ArrayList<>();
    ArrayList<ProductData> userProducts = new ArrayList<>();
    ArrayList<SubmissionData> userSubmissions = new ArrayList<>();
    ArrayList<SubmissionData> ecellSubmissions = new ArrayList<>();
    ArrayList<EventsData> userEvents = new ArrayList<>();

    TextView name, specialisation, location, submissions_text, ideas_text, products_text, events_text;
    ImageView image, web, mail, linkedIn, github,fb,add_event,add_product,add_idea,add_submission;

    String handle = "-L8T12XjiZfa7twNecYg";
    SubmissionAdapter submissionsAdapter;
    EcellSubmissionAdapter ecellSubmissionsAdapter;
    IdeasAdapter ideasAdapter;
    ProductAdapter productsAdapter;
    EventAdapter eventAdapter;
    RecyclerView submissions, ideas, products, events;
    RecyclerView skillsView;
    SkillsAdapter skillsAdapter= new SkillsAdapter();
    ArrayList<String> skills;

    int type;

    public UserDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        type = sharedPreferences.getInt("type",StringVariables.user);
        type = StringVariables.ecell;
        name = view.findViewById(R.id.name_dashboard);
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
        web = view.findViewById(R.id.web);
        mail = view.findViewById(R.id.mail);
        linkedIn = view.findViewById(R.id.linked);
        github = view.findViewById(R.id.github);
        add_event=view.findViewById(R.id.add_event);
        add_submission=view.findViewById(R.id.add_submission);
        add_product=view.findViewById(R.id.add_product);
        add_idea=view.findViewById(R.id.add_idea);
        fb = view.findViewById(R.id.fb);
        add_idea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),ideas_post.class));
            }
        });
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),product_post.class));
            }
        });
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),events_post.class));
            }
        });
        add_submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ContestActivity.class));
            }
        });
        if(type==StringVariables.ecell) {
            ideas.setVisibility(View.GONE);
            products.setVisibility(View.GONE);
            submissions_text.setText("Submissions to approve");

        }
        if(type == StringVariables.user) {
            add_event.setVisibility(View.GONE);
        }
        skillsView=view.findViewById(R.id.skills_recyclerview);
        skillsView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        skillsView.setAdapter(skillsAdapter);
        return view;
    }
    public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

        @Override
        public SkillsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SkillsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.skillview,parent,false));
        }

        @Override
        public void onBindViewHolder(SkillsAdapter.ViewHolder holder, int position) {
            holder.textView.setText(skills.get(position));
        }

        @Override
        public int getItemCount() {
            return skills==null?0:skills.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.skill_textview);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(type==StringVariables.user) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(handle);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserData userData = dataSnapshot.getValue(UserData.class);
                    Log.d("userData", userData.toString());
                    updateUserData(userData);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else if(true||type==StringVariables.ecell){
            Log.d("test","in loading xyz");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ecells").child(handle);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    EcellData ecellData = dataSnapshot.getValue(EcellData.class);
                    Log.e("ecellData",ecellData.toString());
                    updateEcellData(ecellData);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void updateEcellData(final EcellData ecellData) {
        if(ecellData.getImage()!=null)
        Glide
                .with(getActivity())
                .load(ecellData.getImage())
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
        name.setText(ecellData.getInstituteName());
        specialisation.setText(ecellData.getAbout());
        location.setVisibility(View.GONE);
        if(ecellData.getWebsite()==null)
            web.setVisibility(View.GONE);
        if(ecellData.getEmail()==null)
            mail.setVisibility(View.GONE);
        if(ecellData.getLinkedin()==null)
            linkedIn.setVisibility(View.GONE);
        github.setVisibility(View.GONE);
        if(ecellData.getFb()==null)
            fb.setVisibility(View.GONE);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ecellData.getWebsite()));
                getActivity().startActivity(i);

            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("mailto:"+ecellData.getEmail()));
                getActivity().startActivity(i);
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ecellData.getLinkedin()));
                getActivity().startActivity(i);

            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ecellData.getFb()));
                getActivity().startActivity(i);
            }
        });

        if(ecellData.getSubmissions()!=null&&ecellData.getSubmissions().size()>0) {
            submissions_text.setVisibility(View.VISIBLE);
            submissions.setVisibility(View.VISIBLE);
            ecellSubmissionsAdapter = new EcellSubmissionAdapter(getActivity());
            submissions.setAdapter(ecellSubmissionsAdapter);
            submissions.setLayoutManager(new LinearLayoutManager(getActivity()));
            Iterator<String> stringIterator = ecellData.getSubmissions().iterator();
            while (stringIterator.hasNext())
                loadEcellSubmission(stringIterator.next());
        }


    }

    private void loadEcellSubmission(final String next) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("submissions").child(next);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SubmissionData submissionData = dataSnapshot.getValue(SubmissionData.class);
                submissionData.handle=next;
                if(userSubmissions!=null&&userSubmissions.size()>=1&&userSubmissions.get(0).title.equals(submissionData.title))
                    ecellSubmissions.set(0,submissionData);
                else if (submissionData != null && !ecellSubmissions.contains(submissionData)) {
                    ecellSubmissions.add(submissionData);
                }

                ecellSubmissionsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void updateUserData(final UserData userData) {
        skills = userData.skills;
        skillsAdapter.notifyDataSetChanged();
        specialisation.setText(userData.specialisation);
        location.setText(userData.location);
        name.setText(userData.name);
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
        if(userData.getFb()==null)
            fb.setVisibility(View.GONE);
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
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(userData.getFb()));
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
        if (userData.ideas!=null&&userData.ideas.size() > 0) {
            ideas_text.setVisibility(View.VISIBLE);
            ideas.setVisibility(View.VISIBLE);
            ideasAdapter = new IdeasAdapter();
            ideas.setAdapter(ideasAdapter);
            ideas.setLayoutManager(new LinearLayoutManager(getActivity()));

            Iterator<String> stringIterator = userData.ideas.iterator();
            while (stringIterator.hasNext())
                loadIdea(stringIterator.next());
        }
        if (userData.products!=null&&userData.products.size() > 0) {
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
                    eventAdapter.notifyDataSetChanged();
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
                    productsAdapter.notifyDataSetChanged();
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
                if(userSubmissions!=null&&userSubmissions.size()>=1&&userSubmissions.get(0).title.equals(submissionData.title))
                    userSubmissions.set(0,submissionData);
                else if (submissionData != null && !userSubmissions.contains(submissionData)) {
                    userSubmissions.add(submissionData);
                }

                submissionsAdapter.notifyDataSetChanged();
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
                    ideasAdapter.notifyDataSetChanged();
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
        public void onBindViewHolder(final ViewHolder holder,final int position) {
            final EventsData eventsData = userEvents.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://:"+eventsData.getLink()));
                    startActivity(i);
                }
            });
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
            String temp = userSubmissions.get(position).getStatus().trim().equals("-1")?"Rejected : ":userSubmissions.get(position).getStatus().trim().equals("1")?"Approved : ":"Pending : ";
            holder.status.setText(temp+userSubmissions.get(position).review);
            holder.status.setTextColor(userSubmissions.get(position).getStatus().trim().equals("-1")?Color.rgb(240,0,0):userSubmissions.get(position).getStatus().trim().equals("1")?Color.rgb(0,181,0):Color.rgb(0,0,0));
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
    public class EcellSubmissionAdapter extends RecyclerView.Adapter<EcellSubmissionAdapter.ViewHolder> {

        Context context;
        public EcellSubmissionAdapter(Context context) {
            this.context=context;
        }
        @Override
        public EcellSubmissionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new EcellSubmissionAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.submission_approval_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(final EcellSubmissionAdapter.ViewHolder holder, final int position) {
            holder.title.setText(ecellSubmissions.get(position).title);
            holder.department.setText("Category : "+ecellSubmissions.get(position).department);
            holder.details.setText(ecellSubmissions.get(position).details);
            holder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ecellSubmissions.get(position).getLink()!=null){
                        // Download starts
                        Toast.makeText(context,"Downloading File",Toast.LENGTH_SHORT).show();
                        DownloadManager mManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(ecellSubmissions.get(position).getLink());
                        DownloadManager.Request mrequest = new DownloadManager.Request(uri);
                        mrequest.setTitle("Abstract "+ecellSubmissions.get(position).title);
                        mrequest.setDescription("File Downloading...");
                        mrequest.allowScanningByMediaScanner();
                        mrequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        mrequest.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS,"Abstract "+ecellSubmissions.get(position).title);
                        mManager.enqueue(mrequest);
                    }
                }
            });
            holder.approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("submissions").child(ecellSubmissions.get(position).handle);
                    databaseReference.child("status").setValue("1");
                    databaseReference.child("review").setValue(holder.review.getText().toString());
                }
            });
            holder.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("submissions").child(ecellSubmissions.get(position).handle);
                    databaseReference.child("status").setValue("-1");
                    databaseReference.child("review").setValue(holder.review.getText().toString());


                }
            });
        }

        @Override
        public int getItemCount() {
            return ecellSubmissions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView department,title, details;
            Button download,approve,reject;
            EditText review;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.submission_title_ecell);
                department = itemView.findViewById(R.id.submission_department_ecell);
                details = itemView.findViewById(R.id.submission_details_ecell);
                download = itemView.findViewById(R.id.download_abstract);
                approve = itemView.findViewById(R.id.approve_submission);
                reject = itemView.findViewById(R.id.reject_submission);
                review = itemView.findViewById(R.id.remarks_submission);
            }
        }
    }
}
