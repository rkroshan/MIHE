package cubes.logic.mihe.Assistant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/28/2018.
 */

class AssistantChat_Adapter extends RecyclerView.Adapter<AssistantChat_Adapter.ViewHolder> {

    private ArrayList<chat_model> mlist = new ArrayList<>();

    public AssistantChat_Adapter(ArrayList<chat_model> getlist) {
        mlist = getlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asssistant_chat_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView chat;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
