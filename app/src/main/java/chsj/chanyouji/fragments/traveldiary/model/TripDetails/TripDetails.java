package chsj.chanyouji.fragments.traveldiary.model.TripDetails;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/11
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 游记详情
 */
public class TripDetails {

    private String name;
    private int photos_count;
    private String start_date;
    private String user_image;
    private List<TripDays> tripDayses;


    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            name = jsonObject.getString("name");
            photos_count = jsonObject.getInt("photos_count");
            start_date = jsonObject.getString("start_date");
            JSONObject user = jsonObject.getJSONObject("user");
            user_image = user.getString("image");


            //解析Trip_Days
            tripDayses = new ArrayList<TripDays>();
            JSONArray tripDays = jsonObject.getJSONArray("trip_days");
            int len = tripDays.length();
            for (int i = 0; i < len; i++) {
                TripDays t = new TripDays();
                t.parseJSON(tripDays.getJSONObject(i));
                tripDayses.add(t);
            }

        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public List<TripDays> getTripDayses() {
        return tripDayses;
    }

    public void setTripDayses(List<TripDays> tripDayses) {
        this.tripDayses = tripDayses;
    }
}
