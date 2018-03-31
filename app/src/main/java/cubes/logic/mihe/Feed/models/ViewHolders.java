package cubes.logic.mihe.Feed.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/23/2018.
 */

public class ViewHolders {
    //view holder
    public static class EVENTViewHolder extends RecyclerView.ViewHolder {
        public ImageView event_imageview;
        public TextView event_name, event_time, event_bookmark, event_details;
        private String URL;
        private Context mcontext;

        public EVENTViewHolder(View itemView) {
            super(itemView);
            event_imageview = itemView.findViewById(R.id.event_imageview);
            event_name = itemView.findViewById(R.id.event_name);
            event_time = itemView.findViewById(R.id.event_time);
            event_bookmark = itemView.findViewById(R.id.event_bookmark);
            event_details = itemView.findViewById(R.id.event_details);

            event_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (URL != null) {
                        try {
                            intent.setData(Uri.parse(URL));
                            mcontext.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }

        public void setLink(String url, Context context) {
            URL = url;
            mcontext = context;
        }
    }

    //view holder
    public static class MOTIVATIONViewHolder extends RecyclerView.ViewHolder {
        public ImageView motivation_imageview;
        public TextView motivation_textview;
        String URL;
        Context mcontext;

        public MOTIVATIONViewHolder(View itemView) {
            super(itemView);
            motivation_imageview = itemView.findViewById(R.id.motivation_imageview);
            motivation_textview = itemView.findViewById(R.id.motivation_textview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (URL != null) {
                        try {
                            intent.setData(Uri.parse(URL));
                            mcontext.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }

        public void setLink(String url, Context context) {
            URL = url;
            mcontext = context;
        }
    }

    //view holder
    public static class PRODUCTViewHolder extends RecyclerView.ViewHolder {
        public ImageView product_imageview;
        public TextView product_category, product_name, product_description, product_makers;
        private String URL;
        private Context mcontext;

        public PRODUCTViewHolder(View itemView) {
            super(itemView);
            product_imageview = itemView.findViewById(R.id.product_imageview);
            product_category = itemView.findViewById(R.id.product_category);
            product_name = itemView.findViewById(R.id.product_name);
            product_description = itemView.findViewById(R.id.product_description);
            product_makers = itemView.findViewById(R.id.product_makers);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (URL != null) {
                        try {
                            intent.setData(Uri.parse(URL));
                            mcontext.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }

        public void setLink(String url, Context context) {
            URL = url;
            mcontext = context;
        }

        public void setCategory(String category, Context context) {
            switch (category.toLowerCase()) {
                case "hardware":
                    product_category.setText(category.toUpperCase());
                    product_category.setBackgroundColor(ContextCompat.getColor(context, R.color.bottom_nav));
                    break;
                case "software":
                    product_category.setText(category.toUpperCase());
                    product_category.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green));
                    break;
            }

        }
    }

    //view holder
    public static class SCHEMESViewHolder extends RecyclerView.ViewHolder {
        public ImageView scheme_image;
        String URL = null;
        Context mcontext = null;
        Button scheme_viewdetails;

        public SCHEMESViewHolder(View itemView) {
            super(itemView);
            scheme_image = itemView.findViewById(R.id.scheme_image);
            scheme_viewdetails = itemView.findViewById(R.id.scheme_viewdetails);
            scheme_viewdetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (URL != null) {
                        try {
                            intent.setData(Uri.parse(URL));
                            mcontext.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }

        public void setLink(String url, Context context) {
            URL = url;
            mcontext = context;
        }
    }

    public static class WebsiteViewHolder extends RecyclerView.ViewHolder {
        ImageView website_imageview;
        TextView website_title, website_description;
        String Link="";
        Context mcontext;

        public WebsiteViewHolder(View itemView) {
            super(itemView);
            website_imageview = itemView.findViewById(R.id.website_imageview);
            website_title = itemView.findViewById(R.id.website_title);
            website_description = itemView.findViewById(R.id.website_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (Link != null) {
                        try {
                            intent.setData(Uri.parse(Link));
                            mcontext.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }

        public void setWebsite_imageview(String url, final Context context) {
            Glide.with(context).load(url).asBitmap().placeholder(R.mipmap.ic_launcher_round)
                    .into(new BitmapImageViewTarget(website_imageview) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularDrawable.setCircular(true);
                            website_imageview.setImageDrawable(circularDrawable);
                        }
                    });
            ;
        }

        public void setWebsite_title(String data){
            website_title.setText(data);
        }

        public void setWebsite_description(String data){
            website_description.setText(data);
        }

        public void setLink(String url,Context context){
            Link = url;
            mcontext = context;
        }
    }
}
