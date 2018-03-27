package cubes.logic.mihe.Explore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cubes.logic.mihe.Assistant.AssistantFragment_getstarted;
import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class explore_base extends Fragment {

    Fragment fragment;

    public explore_base() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_assistant, container, false);

        fragment = new Explore();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment)
                .addToBackStack("explore_base")
                .commit();
        return view;

    }
}
