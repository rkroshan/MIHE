package cubes.logic.mihe;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CREATOR on 3/23/2018.
 */

public class mihe extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
    }
}
