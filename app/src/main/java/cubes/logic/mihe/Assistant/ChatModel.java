package cubes.logic.mihe.Assistant;

import java.util.HashMap;

/**
 * Created by CREATOR on 3/28/2018.
 */

public class ChatModel {
    
    private String IMAGEDATA, QUESTION, OPTION_YES, OPTION_NO, OPTION1, OPTION2, OPTION3, OPTION4;
    private int id;


    public ChatModel(String IMAGEDATA, String QUESTION, String OPTION_YES, String OPTION_NO, String OPTION1, String OPTION2, String OPTION3, String OPTION4, int id) {
        this.IMAGEDATA = IMAGEDATA;
        this.QUESTION = QUESTION;
        this.OPTION_YES = OPTION_YES;
        this.OPTION_NO = OPTION_NO;
        this.OPTION1 = OPTION1;
        this.OPTION2 = OPTION2;
        this.OPTION3 = OPTION3;
        this.OPTION4 = OPTION4;
        this.id = id;
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        public static HashMap<Integer,ChatModel> map = new HashMap<>();

        static {
            map.put(1,(new ChatModel(null, "Do you have an idea ? ", "YES", "NO", null, null, null, null, 1)));
            map.put(2,(new ChatModel(null, "Do you know how to implement that idea ? ", "YES", "NO", null, null, null, null, 2)));
            map.put(3,(new ChatModel(null, "Do you have ambition to become a leader", "YES, Absolutely", "No, I want somthing else", null, null, null, null, 3)));
            map.put(4,(new ChatModel(null, "Do you have a team ?", "YES", "NO, but i want it", null, null, null, null, 4)));
            map.put(5,(new ChatModel(null, "We provide resources required to accomplish your idea", null, null, null, null, null, null, 5)));
            map.put(6,(new ChatModel(null, "We provide a variety of resources", null, null, null, null, null, null, 6)));
            map.put(7,(new ChatModel(null, "Well you can get hire, create your team or invest in a product through our app", null, null, null, null, null, null, 7)));
            map.put(8,(new ChatModel(null, "our app gives you a opportunity to get your idea to incubation center, Do you want to enter in it?", "Yes, Of Course", "No", null, null, null, null, 8)));
            map.put(9,(new ChatModel(null, "You can find people of your interest in our E-NETWORK", null, null, null, null, null, null, 9)));
            map.put(16,(new ChatModel(null, "Enter contest here", null, null, null, null, null, null, 16)));
            map.put(17,(new ChatModel(null, "Well we can help you in these ways", null, null, null, null, null, null, 17)));
        }
        public static HashMap<Integer,ChatModel> getValues() {
            return map;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatModel chatModel = (ChatModel) o;

        if (id != chatModel.id) return false;
        if (IMAGEDATA != null ? !IMAGEDATA.equals(chatModel.IMAGEDATA) : chatModel.IMAGEDATA != null)
            return false;
        if (QUESTION != null ? !QUESTION.equals(chatModel.QUESTION) : chatModel.QUESTION != null)
            return false;
        if (OPTION_YES != null ? !OPTION_YES.equals(chatModel.OPTION_YES) : chatModel.OPTION_YES != null)
            return false;
        if (OPTION_NO != null ? !OPTION_NO.equals(chatModel.OPTION_NO) : chatModel.OPTION_NO != null)
            return false;
        if (OPTION1 != null ? !OPTION1.equals(chatModel.OPTION1) : chatModel.OPTION1 != null)
            return false;
        if (OPTION2 != null ? !OPTION2.equals(chatModel.OPTION2) : chatModel.OPTION2 != null)
            return false;
        if (OPTION3 != null ? !OPTION3.equals(chatModel.OPTION3) : chatModel.OPTION3 != null)
            return false;
        return OPTION4 != null ? OPTION4.equals(chatModel.OPTION4) : chatModel.OPTION4 == null;
    }

    @Override
    public int hashCode() {
        int result = IMAGEDATA != null ? IMAGEDATA.hashCode() : 0;
        result = 31 * result + (QUESTION != null ? QUESTION.hashCode() : 0);
        result = 31 * result + (OPTION_YES != null ? OPTION_YES.hashCode() : 0);
        result = 31 * result + (OPTION_NO != null ? OPTION_NO.hashCode() : 0);
        result = 31 * result + (OPTION1 != null ? OPTION1.hashCode() : 0);
        result = 31 * result + (OPTION2 != null ? OPTION2.hashCode() : 0);
        result = 31 * result + (OPTION3 != null ? OPTION3.hashCode() : 0);
        result = 31 * result + (OPTION4 != null ? OPTION4.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
