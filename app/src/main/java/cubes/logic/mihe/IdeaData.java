package cubes.logic.mihe;

import java.util.ArrayList;

/**
 * Created by cogito on 3/25/18.
 */

public class IdeaData {
    String name,IDEA_DESCRIPTION,link,thinker;
    int upvotes;

    public IdeaData() {

    }

    public String getIDEA_DESCRIPTION() {
        return IDEA_DESCRIPTION;
    }

    public void setIDEA_DESCRIPTION(String IDEA_DESCRIPTION) {
        this.IDEA_DESCRIPTION = IDEA_DESCRIPTION;
    }

    public IdeaData(String name, String details, String link, String thinker, ArrayList<String> inv_stars, ArrayList<String> user_stars) {
        this.name = name;
        this.IDEA_DESCRIPTION = details;
        this.link = link;
        this.thinker = thinker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdeaData ideaData = (IdeaData) o;

        if (name != null ? !name.equals(ideaData.name) : ideaData.name != null) return false;
        if (IDEA_DESCRIPTION != null ? !IDEA_DESCRIPTION.equals(ideaData.IDEA_DESCRIPTION) : ideaData.IDEA_DESCRIPTION != null)
            return false;
        if (link != null ? !link.equals(ideaData.link) : ideaData.link != null) return false;
        if (thinker != null ? !thinker.equals(ideaData.thinker) : ideaData.thinker != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (IDEA_DESCRIPTION != null ? IDEA_DESCRIPTION.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (thinker != null ? thinker.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
