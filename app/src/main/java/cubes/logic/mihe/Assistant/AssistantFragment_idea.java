package cubes.logic.mihe.Assistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/25/2018.
 */

public class AssistantFragment_idea extends Fragment implements View.OnClickListener {

    Button yes_idea, idea_no, submit_idea;
    TextView query_idea_textview;
    EditText enter_idea;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //container.removeAllViews();
        View view = inflater.inflate(R.layout.assistantfragment_idea, container, false);
        init(view);
//        startAnimation();
        return view;
    }

    private void init(View v) {
        query_idea_textview = v.findViewById(R.id.query_idea_textview);
        yes_idea = v.findViewById(R.id.yes_idea);
        idea_no = v.findViewById(R.id.idea_no);
        enter_idea = v.findViewById(R.id.enter_idea);
        submit_idea = v.findViewById(R.id.submit_idea);

        yes_idea.setOnClickListener(this);
        submit_idea.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        startAnimation();
    }

    private void startAnimation() {
        YoYo.with(Techniques.SlideInUp)
                .duration(1500)
                .playOn(query_idea_textview);
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(yes_idea);
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(idea_no);
    }

    private void fadeOutAnimation() {
        YoYo.with(Techniques.FadeOut)
                .duration(1000)
                .playOn(yes_idea);
        yes_idea.setVisibility(View.GONE);

        YoYo.with(Techniques.FadeOut)
                .duration(1000)
                .playOn(idea_no);
        idea_no.setVisibility(View.GONE);

        YoYo.with(Techniques.FadeOut)
                .duration(1000)
                .playOn(query_idea_textview);
        query_idea_textview.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yes_idea:
                fadeOutAnimation();
                enter_idea.setVisibility(View.VISIBLE);
                submit_idea.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .playOn(enter_idea);
                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .playOn(enter_idea);
                break;
            case R.id.idea_no:
                fadeOutAnimation();
                break;

            case R.id.submit_idea:
                String idea = enter_idea.getText().toString();
                Log.e("ideas",idea);
                if (idea.isEmpty()) {
                    Toast.makeText(getContext(), "Please Give Your Idea", Toast.LENGTH_SHORT).show();
                } else {
                    Fragment fragment = new AssistantFragment_progress();
                    Bundle args = new Bundle();
                    args.putString("IDEA",idea);
                    fragment.setArguments(args);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment)
                            .addToBackStack("AssistantFragment_progress")
                            .commit();
                }
                break;
        }
    }
}
