package cubes.logic.mihe;

/**
 * Created by cogito on 3/25/18.
 */

public class EventsData {
    String title, details, image, link, time, venue, ecellcode, institute_name;

    public EventsData() {

    }

    public EventsData(String title, String details, String image, String link, String time, String venue, String ecellcode,String institute_name) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.link = link;
        this.time = time;
        this.venue = venue;
        this.ecellcode = ecellcode;
        this.institute_name=institute_name;
    }

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEcellcode() {
        return ecellcode;
    }

    public void setEcellcode(String ecellcode) {
        this.ecellcode = ecellcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsData that = (EventsData) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (venue != null ? !venue.equals(that.venue) : that.venue != null) return false;
        return ecellcode != null ? ecellcode.equals(that.ecellcode) : that.ecellcode == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (venue != null ? venue.hashCode() : 0);
        result = 31 * result + (ecellcode != null ? ecellcode.hashCode() : 0);
        return result;
    }
}
