package cubes.logic.mihe.Assistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/28/2018.
 */

public class assistant_chat extends Fragment {

    RecyclerView assistant_chat_recyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assistant_chat,container,false);
        init(view);
        return view;
    }

    private void init(View v) {
        assistant_chat_recyclerview = v.findViewById(R.id.assistant_chat_recyclerview);
        assistant_chat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        assistant_chat_recyclerview.setAdapter(new AssistantChat_Adapter(chat_model.CreateData.getlist()));
    }
}
