package chsj.chanyouji.fragments.traveldiary.model;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/9
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 定义游记首页条目的实体类
 * url:https://chanyouji.com/api/trips/featured.json?page=1
 */
public class Trips implements Serializable {

    private double id;
    private String name;
    private int photos_count;
    private String start_date;
    private int days;
    private String front_cover_photo_url;
    private int user_id;
    private String user_name;
    private String user_image;


    /**
     * 0:代表正常的trip实体
     * 1:代表用户详情中的trip实体
     */
    private int flag;

    public Trips(int flag) {
        this.flag = flag;
    }

    /**
     * 创建对象的时候直接完成解析
     *
     * @param jsonObject
     */
    public void parseJSON(JSONObject jsonObject) throws JSONException {

        if (jsonObject != null) {

            id = jsonObject.getDouble("id");
            name = jsonObject.getString("name");
            photos_count = jsonObject.getInt("photos_count");
            start_date = jsonObject.getString("start_date");
            days = jsonObject.getInt("days");
            front_cover_photo_url = jsonObject.getString("front_cover_photo_url");

            if (flag == 0) {
                JSONObject user = jsonObject.getJSONObject("user");
                user_id = user.getInt("id");
                user_name = user.getString("name");
                user_image = user.getString("image");
            }


        }

    }


    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
