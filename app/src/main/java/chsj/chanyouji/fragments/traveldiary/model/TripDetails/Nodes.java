package chsj.chanyouji.fragments.traveldiary.model.TripDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/11
 */
public class Nodes {

    private String entry_name;
    private List<Notes> notes;

    /**
     * 标识是第几天的
     */
    private int day;

    public Nodes(int day) {
        this.day = day;
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException {

        if (jsonObject != null) {

            entry_name = jsonObject.getString("entry_name");

            //解析Notes
            notes = new ArrayList<Notes>();
            JSONArray notesArray = jsonObject.getJSONArray("notes");
            int len = notesArray.length();
            for (int i = 0; i < len; i++) {

                Notes n = new Notes(day);
                n.parseJSON(notesArray.getJSONObject(i));
                notes.add(n);
            }

        }


    }


    public String getEntry_name() {
        return entry_name;
    }

    public void setEntry_name(String entry_name) {
        this.entry_name = entry_name;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }
}
