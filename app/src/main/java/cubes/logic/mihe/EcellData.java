package cubes.logic.mihe;

import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */

public class EcellData {


    public EcellData(String institute_name, String email, String phone, String website, String fb, String twitter, String gplus, String linkedin, String about, ArrayList<String> events, ArrayList<String> followers, ArrayList<String> submissions) {
        this.institute_name = institute_name;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.fb = fb;
        this.twitter = twitter;
        this.gplus = gplus;
        this.linkedin = linkedin;
        this.about = about;
        this.events = events;
        this.followers = followers;
        this.submissions = submissions;
    }

    private String institute_name;

    public String getInstituteName() {
        return this.institute_name;
    }

    public void setInstituteName(String institute_name) {
        this.institute_name = institute_name;
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

    public void setAbout(String about) {
        this.about = about;
    }


    private ArrayList<String> events;

    public ArrayList<String> getEvents() {
        return this.events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }

    private ArrayList<String> followers;

    public ArrayList<String> getFollowers() {
        return this.followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    private ArrayList<String> submissions;

    public ArrayList<String> getSubmissions() {
        return this.submissions;
    }

    public void setSubmissions(ArrayList<String> submissions) {
        this.submissions = submissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EcellData ecellData = (EcellData) o;

        if (institute_name != null ? !institute_name.equals(ecellData.institute_name) : ecellData.institute_name != null)
            return false;
        if (email != null ? !email.equals(ecellData.email) : ecellData.email != null) return false;
        if (phone != null ? !phone.equals(ecellData.phone) : ecellData.phone != null) return false;
        if (website != null ? !website.equals(ecellData.website) : ecellData.website != null)
            return false;
        if (fb != null ? !fb.equals(ecellData.fb) : ecellData.fb != null) return false;
        if (twitter != null ? !twitter.equals(ecellData.twitter) : ecellData.twitter != null)
            return false;
        if (gplus != null ? !gplus.equals(ecellData.gplus) : ecellData.gplus != null) return false;
        if (linkedin != null ? !linkedin.equals(ecellData.linkedin) : ecellData.linkedin != null)
            return false;
        if (about != null ? !about.equals(ecellData.about) : ecellData.about != null) return false;
        if (events != null ? !events.equals(ecellData.events) : ecellData.events != null)
            return false;
        if (followers != null ? !followers.equals(ecellData.followers) : ecellData.followers != null)
            return false;
        return submissions != null ? submissions.equals(ecellData.submissions) : ecellData.submissions == null;
    }

    @Override
    public int hashCode() {
        int result = institute_name != null ? institute_name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (fb != null ? fb.hashCode() : 0);
        result = 31 * result + (twitter != null ? twitter.hashCode() : 0);
        result = 31 * result + (gplus != null ? gplus.hashCode() : 0);
        result = 31 * result + (linkedin != null ? linkedin.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
        result = 31 * result + (followers != null ? followers.hashCode() : 0);
        result = 31 * result + (submissions != null ? submissions.hashCode() : 0);
        return result;
    }
}
