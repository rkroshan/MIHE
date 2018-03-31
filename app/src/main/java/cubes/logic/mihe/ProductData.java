package cubes.logic.mihe;

import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */

public class ProductData {
    String NAME;
    String details;
    String link;
    String image;

    int upvotes;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;
    ArrayList<String> makers, inv_stars, user_stars;

    public ProductData() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductData that = (ProductData) o;

        if (NAME != null ? !NAME.equals(that.NAME) : that.NAME != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null)
            return false;
        if (makers != null ? !makers.equals(that.makers) : that.makers != null) return false;
        if (inv_stars != null ? !inv_stars.equals(that.inv_stars) : that.inv_stars != null)
            return false;
        return user_stars != null ? user_stars.equals(that.user_stars) : that.user_stars == null;
    }

    @Override
    public int hashCode() {
        int result = NAME != null ? NAME.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (makers != null ? makers.hashCode() : 0);
        result = 31 * result + (inv_stars != null ? inv_stars.hashCode() : 0);
        result = 31 * result + (user_stars != null ? user_stars.hashCode() : 0);
        return result;
    }

    public ProductData(String NAME, String details, String link, String image, String category, ArrayList<String> makers, ArrayList<String> inv_stars, ArrayList<String> user_stars) {
        this.NAME = NAME;
        this.details = details;
        this.link = link;
        this.image = image;
        this.category = category;
        this.makers = makers;
        this.inv_stars = inv_stars;
        this.user_stars = user_stars;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getMakers() {
        return makers;
    }

    public void setMakers(ArrayList<String> makers) {
        this.makers = makers;
    }

    public ArrayList<String> getInv_stars() {
        return inv_stars;
    }

    public void setInv_stars(ArrayList<String> inv_stars) {
        this.inv_stars = inv_stars;
    }

    public ArrayList<String> getUser_stars() {
        return user_stars;
    }

    public void setUser_stars(ArrayList<String> user_stars) {
        this.user_stars = user_stars;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
