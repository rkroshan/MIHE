package cubes.logic.mihe.Feed.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/23/2018.
 */

public class ViewHolders {
    //view holder
    public static class EVENTViewHolder extends RecyclerView.ViewHolder {
        public ImageView event_imageview;
        public TextView event_name, event_time, event_bookmark, event_details;

        public EVENTViewHolder(View itemView) {
            super(itemView);
            event_imageview = itemView.findViewById(R.id.event_imageview);
            event_name = itemView.findViewById(R.id.event_name);
            event_time = itemView.findViewById(R.id.event_time);
            event_bookmark = itemView.findViewById(R.id.event_bookmark);
            event_details = itemView.findViewById(R.id.event_details);
        }

        public void setLink(){

        }
    }

    //view holder
    public static class MOTIVATIONViewHolder extends RecyclerView.ViewHolder {
        public ImageView motivation_imageview;
        public TextView motivation_textview;

        public MOTIVATIONViewHolder(View itemView) {
            super(itemView);
            motivation_imageview = itemView.findViewById(R.id.motivation_imageview);
            motivation_textview = itemView.findViewById(R.id.motivation_textview);
        }
    }

    //view holder
    public static class PRODUCTViewHolder extends RecyclerView.ViewHolder {
        public ImageView product_imageview;
        public TextView product_category, product_name, product_description;

        public PRODUCTViewHolder(View itemView) {
            super(itemView);
            product_imageview = itemView.findViewById(R.id.product_imageview);
            product_category = itemView.findViewById(R.id.product_category);
            product_name = itemView.findViewById(R.id.product_name);
            product_description = itemView.findViewById(R.id.product_description);
        }
    }

    //view holder
    public static class SCHEMESViewHolder extends RecyclerView.ViewHolder {
        public TextView scheme_name, scheme_description, scheme_viewdetails, event_details;

        public SCHEMESViewHolder(View itemView) {
            super(itemView);
            scheme_name = itemView.findViewById(R.id.scheme_name);
            scheme_description = itemView.findViewById(R.id.scheme_description);
            scheme_viewdetails = itemView.findViewById(R.id.scheme_viewdetails);
            event_details = itemView.findViewById(R.id.event_details);
        }
    }
}