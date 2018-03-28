package cubes.logic.mihe.Feed.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/23/2018.
 */

public class MotivationObject {

    private String NAME, IMAGE, LINK;

    public String getLINK() {
        return LINK;
    }

    public void setLINK(String LINK) {
        this.LINK = LINK;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

}
