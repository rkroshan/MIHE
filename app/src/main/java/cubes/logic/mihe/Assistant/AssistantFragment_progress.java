package cubes.logic.mihe.Assistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class AssistantFragment_progress extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assistantfragment_progress,container,false);
        Bundle args = getArguments();
        String data = args.getString("IDEA");
        Log.e("DATA---",data);
        return view;
    }
}
