package cubes.logic.mihe;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */


public class UserData implements Serializable {

    UserData() {

    }

    public ArrayList<String> getIdeas() {
        return ideas;
    }

    public String getPhone() {
        return phone;
    }

    public String getInst_code() {
        return inst_code;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getAbout() {
        return about;
    }

    public ArrayList<String> getSubmissions() {
        return submissions;
    }

    public String getGplus() {
        return gplus;
    }

    public ArrayList<String> getFollowed() {
        return followed;
    }

    public String getFb() {
        return fb;
    }

    public ArrayList<String> getStarred_ideas() {
        return starred_ideas;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getStarred_products() {
        return starred_products;
    }

    public String getBranch() {
        return branch;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public String getGithub() {
        return github;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getHandle() {
        return handle;
    }

    UserData(ArrayList<String> skills, ArrayList<String> ideas, String phone, String inst_code, String website, String location, String linkedin, String about, ArrayList<String> submissions, String gplus, ArrayList<String> followed, String fb, ArrayList<String> starred_ideas, String institution_name, String twitter, String specialisation, ArrayList<String> followers, String email, String name, ArrayList<String> starred_products, String branch, ArrayList<String> products, String github, String img_url, String handle) {
        this.skills = skills;
        this.ideas = ideas;
        this.phone = phone;
        this.inst_code = inst_code;
        this.website = website;
        this.location = location;
        this.linkedin = linkedin;
        this.about = about;
        this.submissions = submissions;
        this.gplus = gplus;
        this.followed = followed;
        this.fb = fb;
        this.starred_ideas = starred_ideas;
        this.institution_name = institution_name;
        this.twitter = twitter;
        this.specialisation = specialisation;
        this.followers = followers;
        this.email = email;
        this.name = name;
        this.starred_products = starred_products;
        this.branch = branch;
        this.products = products;
        this.github = github;
        this.img_url=img_url;
        this.handle=handle;
    }

     ArrayList<String> skills;

     ArrayList<String> ideas;

     String phone;

     String inst_code;

     String website;

     String location;

     String linkedin;

     String about;

     ArrayList<String> submissions;

     String gplus;

     ArrayList<String> followed;

     String fb;

     ArrayList<String> starred_ideas;

     String institution_name;

     String twitter;

     String specialisation;

     ArrayList<String> followers;

     String email;

     String name;

     ArrayList<String> starred_products;

     String branch;

     ArrayList<String> products;

     String github;

     String img_url;

     String handle;

     ArrayList<String> getSkills() {
        return skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (skills != null ? !skills.equals(userData.skills) : userData.skills != null)
            return false;
        if (ideas != null ? !ideas.equals(userData.ideas) : userData.ideas != null) return false;
        if (phone != null ? !phone.equals(userData.phone) : userData.phone != null) return false;
        if (inst_code != null ? !inst_code.equals(userData.inst_code) : userData.inst_code != null)
            return false;
        if (website != null ? !website.equals(userData.website) : userData.website != null)
            return false;
        if (location != null ? !location.equals(userData.location) : userData.location != null)
            return false;
        if (linkedin != null ? !linkedin.equals(userData.linkedin) : userData.linkedin != null)
            return false;
        if (about != null ? !about.equals(userData.about) : userData.about != null) return false;
        if (submissions != null ? !submissions.equals(userData.submissions) : userData.submissions != null)
            return false;
        if (gplus != null ? !gplus.equals(userData.gplus) : userData.gplus != null) return false;
        if (followed != null ? !followed.equals(userData.followed) : userData.followed != null)
            return false;
        if (fb != null ? !fb.equals(userData.fb) : userData.fb != null) return false;
        if (starred_ideas != null ? !starred_ideas.equals(userData.starred_ideas) : userData.starred_ideas != null)
            return false;
        if (institution_name != null ? !institution_name.equals(userData.institution_name) : userData.institution_name != null)
            return false;
        if (twitter != null ? !twitter.equals(userData.twitter) : userData.twitter != null)
            return false;
        if (specialisation != null ? !specialisation.equals(userData.specialisation) : userData.specialisation != null)
            return false;
        if (followers != null ? !followers.equals(userData.followers) : userData.followers != null)
            return false;
        if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
        if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
        if (starred_products != null ? !starred_products.equals(userData.starred_products) : userData.starred_products != null)
            return false;
        if (branch != null ? !branch.equals(userData.branch) : userData.branch != null)
            return false;
        if (products != null ? !products.equals(userData.products) : userData.products != null)
            return false;
        if (github != null ? !github.equals(userData.github) : userData.github != null)
            return false;
        if (img_url != null ? !img_url.equals(userData.img_url) : userData.img_url != null)
            return false;
        return handle != null ? handle.equals(userData.handle) : userData.handle == null;
    }

    @Override
    public int hashCode() {
        int result = skills != null ? skills.hashCode() : 0;
        result = 31 * result + (ideas != null ? ideas.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (inst_code != null ? inst_code.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (linkedin != null ? linkedin.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (submissions != null ? submissions.hashCode() : 0);
        result = 31 * result + (gplus != null ? gplus.hashCode() : 0);
        result = 31 * result + (followed != null ? followed.hashCode() : 0);
        result = 31 * result + (fb != null ? fb.hashCode() : 0);
        result = 31 * result + (starred_ideas != null ? starred_ideas.hashCode() : 0);
        result = 31 * result + (institution_name != null ? institution_name.hashCode() : 0);
        result = 31 * result + (twitter != null ? twitter.hashCode() : 0);
        result = 31 * result + (specialisation != null ? specialisation.hashCode() : 0);
        result = 31 * result + (followers != null ? followers.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (starred_products != null ? starred_products.hashCode() : 0);
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (github != null ? github.hashCode() : 0);
        result = 31 * result + (img_url != null ? img_url.hashCode() : 0);
        result = 31 * result + (handle != null ? handle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassPojo [skills = " + skills + ", ideas = " + ideas + ", phone = " + phone + ", inst_code = " + inst_code + ", website = " + website + ", location = " + location + ", linkedin = " + linkedin + ", about = " + about + ", submissions = " + submissions + ", gplus = " + gplus + ", followed = " + followed + ", fb = " + fb + ", starred_ideas = " + starred_ideas + ", institution_name = " + institution_name + ", twitter = " + twitter + ", specialisation = " + specialisation + ", followers = " + followers + ", email = " + email + ", name = " + name + ", starred_products = " + starred_products + ", branch = " + branch + ", products = " + products + ", github = " + github + "]";
    }
}