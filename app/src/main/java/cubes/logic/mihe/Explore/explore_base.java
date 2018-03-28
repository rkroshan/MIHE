package cubes.logic.mihe.Explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/26/2018.
 */

public class explore_base extends Fragment {
    public static final String TAG = "----explore_base-------";
    Fragment fragment;

    public explore_base() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_explore, container, false);
        Log.e(TAG,"onCreate");

        fragment = new Explore();
//        Log.e("Explore_base ",getActivity().getSupportFragmentManager().getFragments().toString());

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.explore_container, fragment)
                .addToBackStack("explore_base")
                .commit();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
