package cubes.logic.mihe;

import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */

public class IdeaData {
    String title,details,link,thinker;
    ArrayList<String>inv_stars,user_stars;

    public IdeaData() {

    }

    public IdeaData(String title, String details, String link, String thinker, ArrayList<String> inv_stars, ArrayList<String> user_stars) {
        this.title = title;
        this.details = details;
        this.link = link;
        this.thinker = thinker;
        this.inv_stars = inv_stars;
        this.user_stars = user_stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdeaData ideaData = (IdeaData) o;

        if (title != null ? !title.equals(ideaData.title) : ideaData.title != null) return false;
        if (details != null ? !details.equals(ideaData.details) : ideaData.details != null)
            return false;
        if (link != null ? !link.equals(ideaData.link) : ideaData.link != null) return false;
        if (thinker != null ? !thinker.equals(ideaData.thinker) : ideaData.thinker != null)
            return false;
        if (inv_stars != null ? !inv_stars.equals(ideaData.inv_stars) : ideaData.inv_stars != null)
            return false;
        return user_stars != null ? user_stars.equals(ideaData.user_stars) : ideaData.user_stars == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (thinker != null ? thinker.hashCode() : 0);
        result = 31 * result + (inv_stars != null ? inv_stars.hashCode() : 0);
        result = 31 * result + (user_stars != null ? user_stars.hashCode() : 0);
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThinker() {
        return thinker;
    }

    public void setThinker(String thinker) {
        this.thinker = thinker;
    }

    public ArrayList<String> getInv_stars() {
        return inv_stars;
    }

    public void setInv_stars(ArrayList inv_stars) {
        this.inv_stars = inv_stars;
    }

    public ArrayList<String> getUser_stars() {
        return user_stars;
    }

    public void setUser_stars(ArrayList user_stars) {
        this.user_stars = user_stars;
    }
}
