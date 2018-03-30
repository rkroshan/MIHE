package cubes.logic.mihe.Assistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

import cubes.logic.mihe.CompetitionSubmissionActivity;
import cubes.logic.mihe.ContestActivity;
import cubes.logic.mihe.MainActivity;
import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/28/2018.
 */

public class AssistantChatFragment extends Fragment {

    RecyclerView assistant_chat_recyclerview;
    Map<Integer,ChatModel> map;
    ArrayList<ChatModel> chatModels = new ArrayList<>();
    AssistantChatAdapter assistantChat_adapter;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assistant_chat, container, false);
        map = ChatModel.CreateData.map;
        init(view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("chatFragment", Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();

        String past = sharedPreferences.getString("past","1");
        for(String s: past.split(","))
            chatModels.add(map.get(Integer.parseInt(s)));
        assistantChat_adapter.notifyDataSetChanged();
        return view;
    }

    void addToHistory(ChatModel chatModel) {
        chatModels.add(chatModel);
        assistantChat_adapter.notifyItemInserted(chatModels.size()-1);
        assistantChat_adapter.notifyItemChanged(chatModels.size()-2);
        StringBuilder str=new StringBuilder();
        for(ChatModel c: chatModels)
            str.append(c.getId()+",");
        str.deleteCharAt(str.length()-1);
        editor.putString("past",str.toString());
        assistant_chat_recyclerview.smoothScrollToPosition(chatModels.size()-1);
        editor.apply();
    }

    void removeFrom(int n) {
        int removed=chatModels.size()-n;
        for(int i=n;i<chatModels.size();)
            chatModels.remove(chatModels.get(i));
        assistantChat_adapter.notifyItemRangeRemoved(n,removed);
        assistantChat_adapter.notifyItemChanged(n-1);
        StringBuilder str=new StringBuilder();
        for(ChatModel c: chatModels)
            str.append(c.getId()+",");
        str.deleteCharAt(str.length()-1);
        editor.putString("past",str.toString());
        editor.apply();
        assistant_chat_recyclerview.scrollToPosition(chatModels.size()-1);
    }
    void removeAfter(ChatModel chatModel) {
        removeFrom(chatModels.indexOf(chatModel)+1);
    }

    private void init(View v) {
        assistant_chat_recyclerview = v.findViewById(R.id.assistant_chat_recyclerview);
        assistant_chat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        assistantChat_adapter = new AssistantChatAdapter(chatModels, getActivity(),AssistantChatFragment.this);
        assistant_chat_recyclerview.setAdapter(assistantChat_adapter);
    }

    void loadStuff(String str) {
        switch (str) {
            case ChatModel.business_model:
                ((MainActivity)getActivity()).setAdapterPage(0,null);
                break;
            case ChatModel.contest:
                ((MainActivity)getActivity()).startActivity(new Intent(getActivity(),ContestActivity.class));
                break;
            case ChatModel.hiring:
                ((MainActivity)getActivity()).setAdapterPage(0,null);
                break;
            case ChatModel.insights:
                ((MainActivity)getActivity()).setAdapterPage(0,null);
                break;
            case ChatModel.network:
                ((MainActivity)getActivity()).setAdapterPage(1,null);
                break;
            case ChatModel.products:
                ((MainActivity)getActivity()).setAdapterPage(0,null);
                break;
            case ChatModel.resources:
                ((MainActivity)getActivity()).setAdapterPage(0,null);
                break;
            case ChatModel.skills:
                ((MainActivity)getActivity()).setAdapterPage(0,null);
                break;
        }
    }
}
