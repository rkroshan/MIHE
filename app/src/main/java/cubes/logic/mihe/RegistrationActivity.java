package cubes.logic.mihe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Bundle bundle = getIntent().getExtras();
//        String id=bundle.getString("id");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        setValue(null);
        Ecell();
        Investor();
        Submission();
        Event();
        Idea();
        Product();

    }

    void setValue(View view) {
        String phone = "", inst_code = "", website = "", location = "", linkedin = "", about = "", gplus = "", fb = "", institution_name = "", twitter = "", specialisation = "", email = "", name = "", branch = "", github = "",img_url="",handle="";
        ArrayList<String> skills, ideas, submissions, followed, starred_ideas, followers, starred_products, products;
        skills = new ArrayList();
        skills.add("test");
        skills.add("random");
        ideas = new ArrayList();
        ideas.add("ranodm");
        submissions = new ArrayList();
        submissions.add("random");
        followed = new ArrayList();
        followed.add("random");
        starred_ideas = new ArrayList();
        starred_ideas.add("random");
        followers = new ArrayList();
        followers.add("random");
        starred_products = new ArrayList();
        starred_products.add("randmo");
        products = new ArrayList();
        products.add("random");
        UserData userData;;
        userData = new UserData(skills, ideas, phone, inst_code, website, location, linkedin, about, submissions, gplus, followed, fb, starred_ideas, institution_name, twitter, specialisation, followers, email, name, starred_products, branch, products, github,img_url,handle);
        databaseReference.push().setValue(userData);


    }

    void Ecell() {
        EcellData ecellData;
        String institute_name = "NIT PATNA", email = "test@ecell.com", phone = "", website = "ecell.com", fb = "fb.com/ecell", twitter = "twitter.com", gplus = "plus.google.com", linkedin = "linkedin.com", about = "Official e-cell for NIT PATNA";
        ArrayList<String> events, followers, submissions;
        events = new ArrayList<>();
        events.add("test");
        followers = new ArrayList<>();
        followers.add("test");
        submissions = new ArrayList<>();
        submissions.add("test");
        String image="";
        ecellData = new EcellData(image, institute_name, email, phone, website, fb, twitter, gplus, linkedin, about, events, followers, submissions);
        databaseReference.child("ecells").push().setValue(ecellData);
    }

    void Investor() {
        String name = "", email = "", phone = "", website = "", fb = "", twitter = "", gplus = "", linkedin = "", about = "", verified = "";
        ArrayList<String> starred_products, starred_ideas, followed, followers;
        starred_products = new ArrayList<>();
        starred_ideas = new ArrayList<>();
        followed = new ArrayList<>();
        followers = new ArrayList<>();
        starred_ideas.add("");
        starred_products.add("");
        followed.add("");
        followers.add("");
        String image="";
        InvestorData investorData = new InvestorData(image, name, email, phone, website, fb, twitter, gplus, linkedin, about, starred_products, starred_ideas, followed, followers);
        databaseReference.child("investors").push().setValue(investorData);

    }

    void Product() {
        ProductData productData;
        String title = "", details = "", link = "", image = "", category = "";
        ArrayList<String> makers, inv_stars, user_stars;
        makers = new ArrayList<>();
        inv_stars = new ArrayList<>();
        user_stars = new ArrayList<>();
        makers.add("ranodom");
        inv_stars.add("random");
        user_stars.add("random");
        productData = new ProductData(title, details, link, image, category, makers, inv_stars, user_stars);
        databaseReference.child("products").push().setValue(productData);

    }

    void Idea() {
        IdeaData ideaData;
        String title = "", details = "", link = "", thinker = "";
        ArrayList<String> inv_stars, user_stars;
        inv_stars = new ArrayList<>();
        user_stars = new ArrayList<>();
        ideaData = new IdeaData(title, details, link, thinker, inv_stars, user_stars);
        databaseReference.child("ideas").push().setValue(ideaData);
    }

    void Submission() {
        SubmissionData submissionData;
        String title = "", ecellcode = "", details = "", link = "", status = "", review = "";
        ArrayList<String> submitters = new ArrayList<>();
        submitters.add("");
        submissionData = new SubmissionData(title, ecellcode, details, link, status, review, submitters);
        databaseReference.child("submissions").push().setValue(submissionData);
    }

    void Event() {
        String title = "", details = "", image = "", link = "", time = "", venue = "", ecellcode = "";
        EventsData eventsData = new EventsData(title, details, image, link, time, venue, ecellcode,"");
        databaseReference.child("events").push().setValue(eventsData);
    }

}
