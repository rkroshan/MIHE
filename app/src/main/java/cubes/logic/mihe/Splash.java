package cubes.logic.mihe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by CREATOR on 3/31/2018.
 */

public class Splash extends AppCompatActivity {

    Thread thread;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        thread = new Thread() {
            @Override
            public void run() {
                //super.run();
                try {
                    thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        };
        thread.start();
    }
}
