package cubes.logic.mihe.Assistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cubes.logic.mihe.R;


public class AssistantFragment_getstarted extends Fragment {

    Button get_started;


    public AssistantFragment_getstarted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = container;
        Log.e("Assistan_getstarted","onCreate");
        View view =  inflater.inflate(R.layout.fragment_assistant, container, false);
        get_started = view.findViewById(R.id.get_started);

        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AssistantFragment_idea();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//              getActivity().getSupportFragmentManager().popBackStackImmediate(FragmentManager.POP_BACK_STACK_INCLUSIVE,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.container,fragment);
                fragmentTransaction.addToBackStack("AssistantFragment_getstarted");
                fragmentTransaction.commit();
            }
        });
        return view;
    }

   /* @Override
    public void onDestroyView() {
        Log.e("AssistantFrag started","onDestroyView");
        ViewGroup viewGroup = (ViewGroup) getActivity().findViewById(R.id.AssistantFragment_getstarted);
        viewGroup.removeAllViews();
        super.onDestroyView();
    }*/
}
