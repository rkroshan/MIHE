package cubes.logic.mihe.Assistant;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/28/2018.
 */

public class assistant extends Fragment implements View.OnClickListener {

    TextView assistant_main_textview,assistant_main_no_textview;
    Button assistant_main_yes,assistant_main_no;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assistant_main,container,false);
        init(view);
        return view;
    }

    private void init(View v) {
        assistant_main_textview = v.findViewById(R.id.assistant_main_textview);
        assistant_main_no_textview = v.findViewById(R.id.assistant_main_no_textview);
        assistant_main_no_textview.setVisibility(View.GONE);
        assistant_main_yes = v.findViewById(R.id.assistant_main_yes);
        assistant_main_no = v.findViewById(R.id.assistant_main_no);

        assistant_main_yes.setOnClickListener(this);
        assistant_main_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.assistant_main_yes:
                setFragment(new assistant_chat());
                break;
            case R.id.assistant_main_no:
                assistant_main_no_textview.setVisibility(View.VISIBLE);

        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment)
                .addToBackStack(fragment.toString())
                .commit();
    }
}
