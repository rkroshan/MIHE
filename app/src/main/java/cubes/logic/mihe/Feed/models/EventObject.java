package cubes.logic.mihe.Feed.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cubes.logic.mihe.R;

/**
 * Created by CREATOR on 3/23/2018.
 */

public class EventObject {

    private String NAME, VENUE, TIME, LINK, IMAGE;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getVENUE() {
        return VENUE;
    }

    public void setVENUE(String VENUE) {
        this.VENUE = VENUE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getLINK() {
        return LINK;
    }

    public void setLINK(String LINK) {
        this.LINK = LINK;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

}
