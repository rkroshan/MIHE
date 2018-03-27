package cubes.logic.mihe.Assistant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cubes.logic.mihe.Explore.Explore;
import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class assistant_base extends Fragment {

    int which_frag;
    Fragment fragment;

    public assistant_base() {
    }

    @SuppressLint("ValidFragment")
    public assistant_base(int data) {
        which_frag = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_assistant, container, false);

        fragment = new AssistantFragment_getstarted();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment)
                .addToBackStack("assistant_base")
                .commit();
        return view;

    }
}
