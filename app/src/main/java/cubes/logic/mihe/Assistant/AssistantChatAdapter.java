package cubes.logic.mihe.Assistant;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/28/2018.
 */

class AssistantChatAdapter extends RecyclerView.Adapter<AssistantChatAdapter.ViewHolder> {


    private HashMap<Integer, ChatModel> map = new HashMap<>();
    private ArrayList<ChatModel> history = new ArrayList<>();
    Context context;
    AssistantChatFragment chatFragment;

    public AssistantChatAdapter(ArrayList<ChatModel> history, Context context, AssistantChatFragment chat) {
        map= ChatModel.CreateData.map;
        this.history = history;
        this.context= context;
        this.chatFragment =chat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asssistant_chat_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ChatModel chatModel = history.get(position);

        holder.reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatFragment.removeAfter(chatModel);
            }
        });
        if(position!=history.size()-1) {
            holder.container.setBackgroundResource(R.drawable.chat_background_unfocused);
            holder.reload.setVisibility(View.VISIBLE);
        }
        else {
            holder.container.setBackgroundResource(R.drawable.chat_background_focused);
            holder.reload.setVisibility(View.GONE);
        }


        if(chatModel.getIMAGEDATA()!=null) {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(chatModel.getIMAGEDATA())
                    .into(holder.imageView);
        }
        else
            holder.imageView.setVisibility(View.GONE);
        holder.chat.setText(chatModel.getQUESTION());
        if(chatModel.getOPTION_YES()==null) {
            holder.main.setVisibility(View.GONE);
        }
        else {
            holder.main.setVisibility(View.VISIBLE);
            holder.yes.setVisibility(View.VISIBLE);
            holder.yes.setBackgroundResource(R.drawable.white_button);
            holder.no.setBackgroundResource(R.drawable.white_button);;
            holder.yes.setText(chatModel.getOPTION_YES());
            if(position==history.size()-1) {
                holder.yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position!=history.size()-1)
                            return;
                        int pos = chatModel.getId() * 2;
                        ChatModel chatModel1 = map.get(pos);
                        holder.yes.setBackgroundResource(R.drawable.green_button);
                        chatFragment.addToHistory(chatModel1);
                    }
                });
            }
            if(position!=history.size()-1) {
                if(history.get(position+1).getId()==2*chatModel.getId()) {
                    holder.yes.setBackgroundResource(R.drawable.green_button);
                    holder.no.setBackgroundResource(R.drawable.white_button);;
                }
                if(history.get(position+1).getId()==2*chatModel.getId()+1) {
                    holder.no.setBackgroundResource(R.drawable.green_button);
                    holder.yes.setBackgroundResource(R.drawable.white_button);
                }
            }
        }
        if(chatModel.getOPTION_NO()==null) {
            holder.no.setVisibility(View.GONE);
        }
        else {
            holder.no.setText(chatModel.getOPTION_NO());
            holder.no.setVisibility(View.VISIBLE);
            if (position == history.size() - 1) {
                holder.reload.setVisibility(View.GONE);
                holder.no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position!=history.size()-1)
                            return;
                        int pos = chatModel.getId() * 2 + 1;
                        ChatModel chatModel1 = map.get(pos);
                        holder.no.setBackgroundResource(R.drawable.green_button);
                        chatFragment.addToHistory(chatModel1);
                    }
                });
            }
        }

        if(chatModel.getOPTION1()==null) {
            holder.extra.setVisibility(View.GONE);
        }
        else {
            holder.extra.setVisibility(View.VISIBLE);
            holder.b1.setVisibility(View.VISIBLE);
            holder.b1.setText(chatModel.getOPTION1());
            holder.b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            if(chatModel.getOPTION2()!=null) {
                holder.b2.setText(chatModel.getOPTION2());
                holder.b2.setVisibility(View.VISIBLE);
                holder.b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            if(chatModel.getOPTION3()!=null) {
                holder.b3.setText(chatModel.getOPTION3());
                holder.b3.setVisibility(View.VISIBLE);
                holder.b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            if(chatModel.getOPTION4()!=null) {
                holder.b4.setText(chatModel.getOPTION4());
                holder.b4.setVisibility(View.VISIBLE);
                holder.b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            //other stuff
        }
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View container;
        ImageView imageView;
        ImageView reload;
        TextView chat;
        Button yes, no;
        LinearLayout main,extra;
        Button b1, b2, b3, b4, b5;


        public ViewHolder(View itemView) {
            super(itemView);
            container= itemView;
            reload = itemView.findViewById(R.id.chat_reload);
            imageView = itemView.findViewById(R.id.chat_imageview);
            chat = itemView.findViewById(R.id.chat_textview);
            yes = itemView.findViewById(R.id.chat_yes_button);
            no = itemView.findViewById(R.id.chat_no_button);
            b1 = itemView.findViewById(R.id.chat_extra_button1);
            b2 = itemView.findViewById(R.id.chat_extra_button2);
            b3 = itemView.findViewById(R.id.chat_extra_button3);
            b4 = itemView.findViewById(R.id.chat_extra_button4);
            b5 = itemView.findViewById(R.id.chat_extra_button5);
            main= itemView.findViewById(R.id.chat_option_layout);
            extra=itemView.findViewById(R.id.chat_extra_option_layout);
        }

    }
}
