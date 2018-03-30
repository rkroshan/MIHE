package cubes.logic.mihe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PersonActivity extends AppCompatActivity {

    RecyclerView skillsView;
    ArrayList<String> skills;
    SkillsAdapter skillsAdapter = new SkillsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        name = findViewById(R.id.name_person);
        handle = getIntent().getExtras().getString("id");
        handleText = findViewById(R.id.handle_person);
        specialisation = findViewById(R.id.specialisation_person);
        location = findViewById(R.id.location_person);
        ideas_text = findViewById(R.id.ideas_person_textview);
        products_text = findViewById(R.id.products_person_textview);
        ideas = findViewById(R.id.ideas_person_recyclerview);
        products = findViewById(R.id.products_person_recyclerview);
        image = findViewById(R.id.user_image_person);
        web = findViewById(R.id.web_button);
        mail = findViewById(R.id.mail_button);
        linkedIn = findViewById(R.id.linked_button);
        github = findViewById(R.id.github_button);
        skillsView=findViewById(R.id.skills_recyclerview);
        skillsView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        skillsView.setAdapter(skillsAdapter);
    }

    public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.skillview,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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

    ArrayList<IdeaData> userIdeas = new ArrayList<>();
    ArrayList<ProductData> userProducts = new ArrayList<>();

    TextView name, handleText, specialisation, location, ideas_text, products_text;
    ImageView image, web, mail, linkedIn, github;

    String handle;
    PersonActivity.IdeasAdapter ideasAdapter;
    PersonActivity.ProductAdapter productsAdapter;
    RecyclerView ideas, products;


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
        skills = userData.skills;
        skillsAdapter.notifyDataSetChanged();
        specialisation.setText(userData.specialisation);
        location.setText(userData.location);
        name.setText(userData.name);
        handleText.setText("@" + handle);
        Glide
                .with(PersonActivity.this)
                .load(userData.img_url)
                .asBitmap()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(new BitmapImageViewTarget(image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularDrawable = RoundedBitmapDrawableFactory.create(PersonActivity.this.getResources(), resource);
                        circularDrawable.setCircular(true);
                        image.setImageDrawable(circularDrawable);
                    }
                })
        ;

        if (userData.getWebsite() == null)
            web.setVisibility(GONE);
        if (userData.getEmail() == null)
            mail.setVisibility(GONE);
        if (userData.getLinkedin() == null)
            linkedIn.setVisibility(GONE);
        if (userData.getGithub() == null)
            github.setVisibility(GONE);
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
                i.setData(Uri.parse("mailto:" + userData.getEmail()));
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

        if (userData.ideas!=null&&userData.ideas.size() > 0) {
            ideas_text.setVisibility(VISIBLE);
            ideas.setVisibility(VISIBLE);
            ideasAdapter = new PersonActivity.IdeasAdapter();
            ideas.setAdapter(ideasAdapter);
            ideas.setLayoutManager(new LinearLayoutManager(this));

            Iterator<String> stringIterator = userData.ideas.iterator();
            while (stringIterator.hasNext())
                loadIdea(stringIterator.next());
        }

        if (userData.products!=null&&userData.products.size() > 0) {
            products_text.setVisibility(VISIBLE);
            products.setVisibility(VISIBLE);
            productsAdapter = new PersonActivity.ProductAdapter();
            products.setLayoutManager(new LinearLayoutManager(this));
            products.setAdapter(productsAdapter);
            Iterator<String> stringIterator = userData.products.iterator();
            while (stringIterator.hasNext())
                loadProduct(stringIterator.next());
        }

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
            Log.e("obBin===",userProducts.get(position).title);
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




}
