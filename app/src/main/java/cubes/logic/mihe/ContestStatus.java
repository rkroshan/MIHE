package cubes.logic.mihe;

/**
 * Created by cogito on 3/30/18.
 */

public class ContestStatus {
    private String startTime;
    private String endTime;
    private String rules;
    private String details;
    private boolean open;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public ContestStatus(String startTime, String endTime, String rules, String details, boolean open) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.rules = rules;
        this.details = details;
        this.open = open;
    }
}
