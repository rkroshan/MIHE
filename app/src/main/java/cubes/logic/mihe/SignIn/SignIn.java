package cubes.logic.mihe.SignIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cubes.logic.mihe.MainActivity;
import cubes.logic.mihe.R;
import cubes.logic.mihe.StringVariables;

/**
 * Created by CREATOR on 3/29/2018.
 */

public class SignIn extends AppCompatActivity {

    Button google_login;
    private GoogleApiClient mGoogleApiClient;
    ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private int RC_SIGN_IN = 101;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPreferences;
    private String UID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences(StringVariables.USER_DATA,0);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        google_login = (Button)findViewById(R.id.google_login);
        //google_login = (SignInButton) findViewById(R.id.google_login);
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }
        });

        auth = FirebaseAuth.getInstance();

        /*FirebaseUser user = auth.getCurrentUser();
        if(user != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            Toast.makeText(getApplicationContext(),"Welcome back",Toast.LENGTH_SHORT).show();
        }*/

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Conection failed", Toast.LENGTH_SHORT).show();
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            progressDialog.setMessage("Signing you in...");
            progressDialog.show();
            //progressDialog.show(EntryPage1.this, "", "Signing you in...");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                mGoogleApiClient.clearDefaultAccountAndReconnect();

            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), " Conection failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Entry Page", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Entry Page", "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Log.w("Entry Page", "signInWithCredential", task.getException());

                            Toast.makeText(getApplicationContext(), "Something went wrong\n"+task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(), "Successful signin",
                                    Toast.LENGTH_SHORT).show();
                            //start retrieving data if exist
                            progressDialog.dismiss();
                            Random random = new Random();
                            int id = random.nextInt(10000);
                            UID  = "Roshan"+id+"";
                            check_user_signin();
                        }
                    }
                });
    }

    private void check_user_signin() {
        final DatabaseReference databaseReference1 = databaseReference.child(StringVariables.SIGNIN_USER);
        databaseReference1.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot==null){
                    create_user();
                }else {
                    getting_user_data();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void nextActivity(){
        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    private void create_user() {
        databaseReference.child(UID).setValue("ahshfshd csfahasahbch").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nextActivity();
            }
        });
    }

    private void getting_user_data() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.USERS);
        databaseReference.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editor.putString(StringVariables.ABOUT,dataSnapshot.child(StringVariables.ABOUT)+"");
                editor.putString(StringVariables.BRANCH,dataSnapshot.child(StringVariables.BRANCH)+"");
                editor.putString(StringVariables.GMAIL,dataSnapshot.child(StringVariables.GMAIL)+"");
                editor.putString(StringVariables.GITHUB,dataSnapshot.child(StringVariables.GITHUB)+"");
                editor.putString(StringVariables.HANDLE,dataSnapshot.child(StringVariables.HANDLE)+"");
                editor.putString(StringVariables.IMG_URL,dataSnapshot.child(StringVariables.IMG_URL)+"");
                editor.putString(StringVariables.INST_CODE,dataSnapshot.child(StringVariables.INST_CODE)+"");
                editor.putString(StringVariables.LINKEDIN,dataSnapshot.child(StringVariables.LINKEDIN)+"");
                editor.putString(StringVariables.LOCATION,dataSnapshot.child(StringVariables.LOCATION)+"");
                editor.putString(StringVariables.USER_NAME,dataSnapshot.child(StringVariables.USER_NAME)+"");
                editor.putString(StringVariables.SPECIALIZATION,databaseReference.child(StringVariables.SPECIALIZATION)+"");
                editor.putString(StringVariables.WEBSITE,databaseReference.child(StringVariables.WEBSITE)+"");

                String skills = "";

                for(DataSnapshot ds:dataSnapshot.child(StringVariables.SKILLS).getChildren()){
                    skills += ds.getValue()+",";
                }
                editor.putString(StringVariables.SKILLS,skills);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void onStart() {

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                // ...
            }
        };
        auth.addAuthStateListener(authStateListener);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }


}
