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

     public ArrayList<String> getSkills() {
        return skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;

        UserData userData = (UserData) o;

        if (getSkills() != null ? !getSkills().equals(userData.getSkills()) : userData.getSkills() != null)
            return false;
        if (getIdeas() != null ? !getIdeas().equals(userData.getIdeas()) : userData.getIdeas() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(userData.getPhone()) : userData.getPhone() != null)
            return false;
        if (getInst_code() != null ? !getInst_code().equals(userData.getInst_code()) : userData.getInst_code() != null)
            return false;
        if (getWebsite() != null ? !getWebsite().equals(userData.getWebsite()) : userData.getWebsite() != null)
            return false;
        if (getLocation() != null ? !getLocation().equals(userData.getLocation()) : userData.getLocation() != null)
            return false;
        if (getLinkedin() != null ? !getLinkedin().equals(userData.getLinkedin()) : userData.getLinkedin() != null)
            return false;
        if (getAbout() != null ? !getAbout().equals(userData.getAbout()) : userData.getAbout() != null)
            return false;
        if (getSubmissions() != null ? !getSubmissions().equals(userData.getSubmissions()) : userData.getSubmissions() != null)
            return false;
        if (getGplus() != null ? !getGplus().equals(userData.getGplus()) : userData.getGplus() != null)
            return false;
        if (getFollowed() != null ? !getFollowed().equals(userData.getFollowed()) : userData.getFollowed() != null)
            return false;
        if (getFb() != null ? !getFb().equals(userData.getFb()) : userData.getFb() != null)
            return false;
        if (getStarred_ideas() != null ? !getStarred_ideas().equals(userData.getStarred_ideas()) : userData.getStarred_ideas() != null)
            return false;
        if (getInstitution_name() != null ? !getInstitution_name().equals(userData.getInstitution_name()) : userData.getInstitution_name() != null)
            return false;
        if (getTwitter() != null ? !getTwitter().equals(userData.getTwitter()) : userData.getTwitter() != null)
            return false;
        if (getSpecialisation() != null ? !getSpecialisation().equals(userData.getSpecialisation()) : userData.getSpecialisation() != null)
            return false;
        if (getFollowers() != null ? !getFollowers().equals(userData.getFollowers()) : userData.getFollowers() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(userData.getEmail()) : userData.getEmail() != null)
            return false;
        if (getName() != null ? !getName().equals(userData.getName()) : userData.getName() != null)
            return false;
        if (getStarred_products() != null ? !getStarred_products().equals(userData.getStarred_products()) : userData.getStarred_products() != null)
            return false;
        if (getBranch() != null ? !getBranch().equals(userData.getBranch()) : userData.getBranch() != null)
            return false;
        if (getProducts() != null ? !getProducts().equals(userData.getProducts()) : userData.getProducts() != null)
            return false;
        if (getGithub() != null ? !getGithub().equals(userData.getGithub()) : userData.getGithub() != null)
            return false;
        if (getImg_url() != null ? !getImg_url().equals(userData.getImg_url()) : userData.getImg_url() != null)
            return false;
        return getHandle() != null ? getHandle().equals(userData.getHandle()) : userData.getHandle() == null;
    }

    @Override
    public int hashCode() {
        int result = getSkills() != null ? getSkills().hashCode() : 0;
        result = 31 * result + (getIdeas() != null ? getIdeas().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getInst_code() != null ? getInst_code().hashCode() : 0);
        result = 31 * result + (getWebsite() != null ? getWebsite().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (getLinkedin() != null ? getLinkedin().hashCode() : 0);
        result = 31 * result + (getAbout() != null ? getAbout().hashCode() : 0);
        result = 31 * result + (getSubmissions() != null ? getSubmissions().hashCode() : 0);
        result = 31 * result + (getGplus() != null ? getGplus().hashCode() : 0);
        result = 31 * result + (getFollowed() != null ? getFollowed().hashCode() : 0);
        result = 31 * result + (getFb() != null ? getFb().hashCode() : 0);
        result = 31 * result + (getStarred_ideas() != null ? getStarred_ideas().hashCode() : 0);
        result = 31 * result + (getInstitution_name() != null ? getInstitution_name().hashCode() : 0);
        result = 31 * result + (getTwitter() != null ? getTwitter().hashCode() : 0);
        result = 31 * result + (getSpecialisation() != null ? getSpecialisation().hashCode() : 0);
        result = 31 * result + (getFollowers() != null ? getFollowers().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getStarred_products() != null ? getStarred_products().hashCode() : 0);
        result = 31 * result + (getBranch() != null ? getBranch().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        result = 31 * result + (getGithub() != null ? getGithub().hashCode() : 0);
        result = 31 * result + (getImg_url() != null ? getImg_url().hashCode() : 0);
        result = 31 * result + (getHandle() != null ? getHandle().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name+" "+specialisation+" "+ ((skills==null)?"":skills.toString());
    }
}