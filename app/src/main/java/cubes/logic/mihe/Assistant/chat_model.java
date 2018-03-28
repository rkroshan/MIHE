package cubes.logic.mihe.Assistant;

import java.util.ArrayList;

/**
 * Created by CREATOR on 3/28/2018.
 */

public class chat_model {
    private String IMAGEDATA, QUESTION, OPTION_YES, OPTION_NO, OPTION1, OPTION2, OPTION3, OPTION4;

    public chat_model(String IMAGEDATA, String QUESTION, String OPTION_YES, String OPTION_NO, String OPTION1, String OPTION2, String OPTION3, String OPTION4) {
        this.IMAGEDATA = IMAGEDATA;
        this.QUESTION = QUESTION;
        this.OPTION_YES = OPTION_YES;
        this.OPTION_NO = OPTION_NO;
        this.OPTION1 = OPTION1;
        this.OPTION2 = OPTION2;
        this.OPTION3 = OPTION3;
        this.OPTION4 = OPTION4;
    }

    public String getOPTION_YES() {
        return OPTION_YES;
    }

    public void setOPTION_YES(String OPTION_YES) {
        this.OPTION_YES = OPTION_YES;
    }

    public String getOPTION_NO() {
        return OPTION_NO;
    }

    public void setOPTION_NO(String OPTION_NO) {
        this.OPTION_NO = OPTION_NO;
    }

    public String getIMAGEDATA() {
        return IMAGEDATA;
    }

    public void setIMAGEDATA(String IMAGEDATA) {
        this.IMAGEDATA = IMAGEDATA;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTION1() {
        return OPTION1;
    }

    public void setOPTION1(String OPTION1) {
        this.OPTION1 = OPTION1;
    }

    public String getOPTION2() {
        return OPTION2;
    }

    public void setOPTION2(String OPTION2) {
        this.OPTION2 = OPTION2;
    }

    public String getOPTION3() {
        return OPTION3;
    }

    public void setOPTION3(String OPTION3) {
        this.OPTION3 = OPTION3;
    }

    public String getOPTION4() {
        return OPTION4;
    }

    public void setOPTION4(String OPTION4) {
        this.OPTION4 = OPTION4;
    }

    public static final class CreateData {

        public static ArrayList<chat_model> mlist = new ArrayList<>();

        static {
            mlist.add(new chat_model(null, "Do you have an idea ? ", "YES", "NO", null, null, null, null));
            mlist.add(new chat_model(null, "Do you know how to implement that idea ? ", "YES", "NO", null, null, null, null));
            mlist.add(new chat_model(null, "Do you have ambition to become a leader", "YES, Absolutely", "No, I want somthing else", null, null, null, null));
            mlist.add(new chat_model(null, "Do you have a team ?", "YES", "NO, but i want it", null, null, null, null));
            mlist.add(new chat_model(null, "We provide resources required to accomplish your idea", null, null, null, null, null, null));
            mlist.add(new chat_model(null, "We provide a variety of resources", null, null, null, null, null, null));
            mlist.add(new chat_model(null, "Well you can get hire, create your team or invest in a product through our app", null, null, null, null, null, null));
            mlist.add(new chat_model(null, "our app gives you a opportunity to get your idea to incubation center, Do you want to enter in it?", "Yes, Of Course", "No", null, null, null, null));
            mlist.add(new chat_model(null, "You can find people of your interest in our E-NETWORK", null, null, null, null, null, null));
            mlist.add(new chat_model(null, null, null, null, null, null, null, null));
            mlist.add(new chat_model(null, "Well we can help you in these ways", null, null, null, null, null, null));
        }

        public static ArrayList<chat_model> getlist(){
            return mlist;
        }
    }
}
