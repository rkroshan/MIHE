package cubes.logic.mihe.Feed.Explore;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/21/2018.
 */

class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<String> list = new ArrayList<>();
    private int height=0,width=0;

    public ExploreAdapter(ArrayList<String> mlist, Context c) {
        list = mlist;
        context = c;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explorer_element_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      //  holder.explorer_layout.setLayoutParams(width/2,height/3);
        holder.explorer_layout.setBackgroundColor(ContextCompat.getColor(context,getColor(position)));
        holder.explorer_element_text.setText(list.get(position));
    }

    private int getColor(int i){
        int data = R.color.color1;
        switch (i){
            case 0:
                data = R.color.color1;
                break;
            case 1:
                data = R.color.color2;
                break;
            case 2:
                data = R.color.color3;
                break;
            case 3:
                data = R.color.color4;
                break;
            case 4:
                data = R.color.color5;
                break;
            case 5:
                data = R.color.color6;
                break;
        }
        return data;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout explorer_layout;
        TextView explorer_element_text;
        public ViewHolder(View itemView) {
            super(itemView);
            explorer_layout = itemView.findViewById(R.id.explorer_layout);
            explorer_element_text = itemView.findViewById(R.id.explorer_element_text);
        }
    }
}
