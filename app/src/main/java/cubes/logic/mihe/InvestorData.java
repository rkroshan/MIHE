package cubes.logic.mihe;

import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */

public class InvestorData {

    public InvestorData(String name, String email, String phone, String website, String fb, String twitter, String gplus, String linkedin, String about, ArrayList<String> starred_products, ArrayList<String> starred_ideas, ArrayList<String> followed, ArrayList<String> followers) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.fb = fb;
        this.twitter = twitter;
        this.gplus = gplus;
        this.linkedin = linkedin;
        this.about = about;
        this.starred_products = starred_products;
        this.starred_ideas = starred_ideas;
        this.followed = followed;
        this.followers = followers;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String phone;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String website;

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    private String fb;

    public String getFb() {
        return this.fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    private String twitter;

    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    private String gplus;

    public String getGplus() {
        return this.gplus;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    private String linkedin;

    public String getLinkedin() {
        return this.linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    private String about;

    public String getAbout() {
        return this.about;
    }
    private ArrayList<String> starred_products;

    private ArrayList<String> starred_ideas;

    private ArrayList<String> followed;

    private ArrayList<String> followers;

    public ArrayList<String> getStarred_products() {
        return starred_products;
    }

    public void setStarred_products(ArrayList<String> starred_products) {
        this.starred_products = starred_products;
    }

    public ArrayList<String> getStarred_ideas() {
        return starred_ideas;
    }

    public void setStarred_ideas(ArrayList<String> starred_ideas) {
        this.starred_ideas = starred_ideas;
    }

    public ArrayList<String> getFollowed() {
        return followed;
    }

    public void setFollowed(ArrayList<String> followed) {
        this.followed = followed;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }


}
