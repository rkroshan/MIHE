package cubes.logic.mihe;

import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */

public class SubmissionData {

    String title,ecellcode,details,link,status,review;
    ArrayList<String> submitters;

    public  SubmissionData() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubmissionData that = (SubmissionData) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (ecellcode != null ? !ecellcode.equals(that.ecellcode) : that.ecellcode != null)
            return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (review != null ? !review.equals(that.review) : that.review != null) return false;
        return submitters != null ? submitters.equals(that.submitters) : that.submitters == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (ecellcode != null ? ecellcode.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (submitters != null ? submitters.hashCode() : 0);
        return result;
    }

    public SubmissionData(String title, String ecellcode, String details, String link, String status, String review, ArrayList<String> submitters) {
        this.title = title;
        this.ecellcode = ecellcode;
        this.details = details;
        this.link = link;
        this.status = status;
        this.review = review;
        this.submitters = submitters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEcellcode() {
        return ecellcode;
    }

    public void setEcellcode(String ecellcode) {
        this.ecellcode = ecellcode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ArrayList<String> getSubmitters() {
        return submitters;
    }

    public void setSubmitters(ArrayList<String> submitters) {
        this.submitters = submitters;
    }
}
