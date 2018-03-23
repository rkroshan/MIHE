/*
package cubes.logic.mihe.Feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import cubes.logic.mihe.Feed.models.EventObject;
import cubes.logic.mihe.Feed.models.MotivationObject;
import cubes.logic.mihe.Feed.models.ProductObject;
import cubes.logic.mihe.Feed.models.SchemeObject;
import cubes.logic.mihe.R;

*/
/**
 * Created by CREATOR on 3/22/2018.
 *//*


class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.ViewHolder> {

    private Context mcontext;
    private int featureNo;
    private ArrayList<MotivationObject> motivationObjects = new ArrayList<>();
    private ArrayList<ProductObject> productObjects = new ArrayList<>();
    private ArrayList<EventObject> eventObjects = new ArrayList<>();
    private ArrayList<SchemeObject> schemeObjects = new ArrayList<>();

    private ProductObject productObject = new ProductObject();
    private EventObject eventObject = new EventObject();
    private SchemeObject schemeObject = new SchemeObject();


    public NestedAdapter(Context context, int i, ArrayList<MotivationObject> mo, ArrayList<ProductObject> pr, ArrayList<EventObject> ev, ArrayList<SchemeObject> sc) {
        mcontext = context;
        featureNo = i;
        switch (i){
            case 0:
                this.motivationObjects = mo;
                break;
            case 1:
                this.productObjects = pr;
                break;
            case 2:
                this.eventObjects = ev;
                break;
            case 3:
                this.schemeObjects = sc;
                break;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (featureNo){
            case 0:
                Log.e("onCreateViewHolder","Set 0");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.motivations_element_view,parent,false);
                break;
            case 1:
                Log.e("onCreateViewHolder","Set 1");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_element_view,parent,false);
                break;
            case 2:
                Log.e("onCreateViewHolder","Set 2");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_element_view,parent,false);
                break;
            case 3:
                Log.e("onCreateViewHolder","Set 3");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schemes_element_view,parent,false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.motivations_element_view,parent,false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (featureNo){
            case 0:
                Log.e("onBindViewHolder","Set");
                Log.e("onBindViewHolder pos",position +"");
                String name = motivationObjects.get(position).getNAME();
                Log.e("onBindViewHolder name",name);
                String image = motivationObjects.get(position).getIMAGE();
                Log.e("onBindViewHolder image",image);
                Glide.with(mcontext).load(image)
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.motivation_imageview);
                holder.motivation_textview.setText(name);
                break;
            case 1:
                Glide.with(mcontext).load(productObjects.get(position).getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.product_imageview);
                holder.product_name.setText(productObjects.get(position).getNAME());
                holder.product_category.setText(productObjects.get(position).getCATEGORY());
                holder.product_description.setText(productObjects.get(position).getDESCRIPTION());
                break;
            case 2:
                Glide.with(mcontext).load(eventObjects.get(position).getIMAGE())
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.event_imageview);
                holder.event_name.setText(eventObjects.get(position).getNAME());
                holder.event_time.setText(eventObjects.get(position).getTIME());
                holder.event_venue.setText(eventObjects.get(position).getVENUE());
                break;
            case 3:
                holder.scheme_name.setText(schemeObjects.get(position).getNAME());
                holder.scheme_description.setText(schemeObjects.get(position).getDESCRIPTION());
                break;

        }
    }

    @Override
    public int getItemCount() {
        switch (featureNo){
            case 0:
                Log.e("motivationObjects",motivationObjects.size()+"");
                return motivationObjects.size();
            case 1: return productObjects.size();
            case 2: return eventObjects.size();
            case 3: return schemeObjects.size();
            default: return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView motivation_imageview,product_imageview,event_imageview;
        TextView motivation_textview,product_category,product_name,product_description
                ,event_name,event_time,event_details,event_bookmark,event_venue
                ,scheme_name,scheme_description,scheme_viewdetails;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("ViewHolder","Set");
            switchingFeature();
        }

        private void switchingFeature() {
            switch (featureNo){
                case 0:
                    Log.e("ViewHolder","Motivation_init");
                    Motivation_init();
                    break;
                case 1:
                    Product_init();
                    break;
                case 2:
                    Events_init();
                    break;
                case 3:
                    Schemes_init();
                    break;
                default:
                    Motivation_init();
            }
        }

        private void Motivation_init() {
             motivation_imageview = itemView.findViewById(R.id.motivation_imageview);
             motivation_textview = itemView.findViewById(R.id.motivation_textview);
        }

        private void Product_init() {
             product_imageview = itemView.findViewById(R.id.product_imageview);
             product_category = itemView.findViewById(R.id.product_category);
             product_name = itemView.findViewById(R.id.product_name);
             product_description = itemView.findViewById(R.id.product_description);
        }

        private void Events_init() {
             event_imageview = itemView.findViewById(R.id.event_imageview);
             event_name = itemView.findViewById(R.id.event_name);
             event_time = itemView.findViewById(R.id.event_time);
             event_bookmark = itemView.findViewById(R.id.event_bookmark);
             event_details = itemView.findViewById(R.id.event_details);
            event_venue = itemView.findViewById(R.id.event_venue);
        }

        private void Schemes_init() {
             scheme_name = itemView.findViewById(R.id.scheme_name);
             scheme_description = itemView.findViewById(R.id.scheme_description);
             scheme_viewdetails = itemView.findViewById(R.id.scheme_viewdetails);
        }
    }

}
*/
