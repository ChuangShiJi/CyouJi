package chsj.chanyouji.fragments.traveldiary.model.TripDetails;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/11
 */
public class Notes {

    private String description;
    private String photo_url;

    /**
     * 标识是第几天的笔记
     */
    private int day;

    public Notes(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException {


        if (jsonObject != null) {
            String jsonStr = jsonObject.toString();

            if (jsonStr.contains("description")) {
                description = jsonObject.getString("description");
            }

            if (jsonStr.contains("photo")) {
                JSONObject photo = jsonObject.getJSONObject("photo");
                photo_url = photo.getString("url");
            }
        }

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "description='" + description + '\'' +
                ", photo_url='" + photo_url + '\'' +
                '}';
    }
}
